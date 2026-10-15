package org.walletmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.entity.Transaction;
import org.walletmanagementsystem.enums.PaymentMode;
import org.walletmanagementsystem.enums.TransactionDirection;
import org.walletmanagementsystem.enums.TransactionSortField;
import org.walletmanagementsystem.enums.TransactionType;
import org.walletmanagementsystem.repository.TransactionRepository;
import org.walletmanagementsystem.response.GetTransactionResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    Transaction persist(
            UUID senderId,
            UUID receiverId,
            BigDecimal amount,
            PaymentMode paymentMode,
            TransactionType transactionType) {
        if (receiverId == null) {
            throw new IllegalArgumentException("receiverId is mandatory.");
        }
        if (amount == null) {
            throw new IllegalArgumentException("amount is mandatory.");
        }
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setSenderId(senderId);
        transaction.setReceiverId(receiverId);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setPaymentMode(paymentMode);
        transaction.setTransactionType(transactionType);
        return transactionRepository.save(transaction);
    }

    public List<GetTransactionResponse> getTransactionsByUserId(UUID userId, TransactionDirection direction, TransactionSortField sortField) {
        if (userId == null) {
            throw new IllegalArgumentException("UserId is mandatory.");
        }
        if (direction == null) {
            throw new IllegalArgumentException("direction is mandatory.");
        }
        if (sortField == null) {
            throw new IllegalArgumentException("sortField is mandatory.");
        }
        List<Transaction> transactions;
        if (direction == TransactionDirection.RECEIVE) {
            transactions = transactionRepository.findByReceiverId(userId);
            transactions.stream().filter(
                    transaction -> transaction.getTransactionType() == TransactionType.WALLET_TO_WALLET_TRANSFER
            ).toList();
        } else if (direction == TransactionDirection.SEND) {
            transactions = transactionRepository.findBySenderId(userId);
        } else {
            throw new IllegalArgumentException("Invalid direction.");
        }

        List<Transaction> sortedTransactions = sortTransactions(transactions, sortField);
        return sortedTransactions.stream()
                .map(transaction -> new GetTransactionResponse(
                        transaction.getAmount(),
                        transaction.getTransactionType(),
                        transaction.getTimestamp()
                )).toList();
    }

    private List<Transaction> sortTransactions(List<Transaction> transactions, TransactionSortField sortField) {
        Comparator<Transaction> comparator;
        if (sortField == TransactionSortField.TIMESTAMP) {
            comparator = Comparator.comparing(Transaction::getTimestamp);
        } else if (sortField == TransactionSortField.AMOUNT) {
            comparator = Comparator.comparing(Transaction::getAmount);
        } else {
            throw new IllegalArgumentException("Invalid sort parameter.");
        }

        return transactions.stream().sorted(comparator.reversed()).toList();
    }
}
