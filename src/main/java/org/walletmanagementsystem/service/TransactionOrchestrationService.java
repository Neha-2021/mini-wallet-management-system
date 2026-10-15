package org.walletmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.entity.Transaction;
import org.walletmanagementsystem.enums.PaymentMode;
import org.walletmanagementsystem.enums.TransactionType;
import org.walletmanagementsystem.service.payment.PaymentStrategy;
import org.walletmanagementsystem.service.payment.PaymentStrategyFactory;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TransactionOrchestrationService {
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final PaymentStrategyFactory paymentStrategyFactory;

    private final ReentrantLock walletTransactionLock = new ReentrantLock();

    public TransactionOrchestrationService(
            WalletService walletService,
            TransactionService transactionService,
            UserService userService,
            PaymentStrategyFactory paymentStrategyFactory
    ) {
        this.walletService = walletService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    public Transaction topUp(String username, BigDecimal amount, PaymentMode paymentMode) {
        validateAmount(amount);

        UUID userId = userService.getUserId(username);

        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(paymentMode);
        Boolean isSuccess = paymentStrategy.initiateTopUp(userId, amount);
        if(!isSuccess) {
            throw new IllegalStateException("Failed to initiate top up");
        }
        walletTransactionLock.lock();
        try {
            walletService.credit(userId, amount);
            Transaction transaction = transactionService.persist(null, userId, amount, paymentMode, TransactionType.TOP_UP);
            return transaction;
        } catch (Exception e) {
            throw e;
        } finally {
            walletTransactionLock.unlock();
        }

    }

    public Transaction sendMoney(String senderName, String receiverName, BigDecimal amount) {
        validateAmount(amount);

        UUID senderId = userService.getUserId(senderName);
        UUID receiverId = userService.getUserId(receiverName);

        walletTransactionLock.lock();
        try {
            walletService.debit(senderId, amount);
            walletService.credit(receiverId, amount);

            Transaction transaction = transactionService.persist(
                    senderId,
                    receiverId,
                    amount,
                    null,
                    TransactionType.WALLET_TO_WALLET_TRANSFER
            );
            return transaction;
        } catch (Exception e) {
            throw e;
        } finally {
            walletTransactionLock.unlock();
        }
    }

    private static void validateAmount(BigDecimal amount) {
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}
