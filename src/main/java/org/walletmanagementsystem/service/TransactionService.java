package org.walletmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.entity.Transaction;
import org.walletmanagementsystem.enums.PaymentMode;
import org.walletmanagementsystem.enums.TransactionDirection;
import org.walletmanagementsystem.enums.TransactionType;
import org.walletmanagementsystem.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
            TransactionType transactionType)
    {
        if(receiverId == null){
            throw new IllegalArgumentException("receiverId is mandatory.");
        }
        if(amount == null){
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

    List<Transaction> getTransactionsByUserId(UUID userId, TransactionDirection direction) {
        if(userId == null) {
            throw new IllegalArgumentException("UserId is mandatory.");
        }
        if (direction == TransactionDirection.CREDIT) {
            return transactionRepository.findByReceiverId(userId);
        } else if (direction == TransactionDirection.DEBIT) {
            return transactionRepository.findBySenderId(userId);
        } else {
            throw new IllegalArgumentException("Invalid direction.");
        }
    }



}
