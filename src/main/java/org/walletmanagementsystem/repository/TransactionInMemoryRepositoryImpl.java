package org.walletmanagementsystem.repository;

import org.springframework.stereotype.Repository;
import org.walletmanagementsystem.entity.Transaction;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class TransactionInMemoryRepositoryImpl implements TransactionRepository {
    HashMap<UUID, Transaction> transactionMap = new HashMap<>();
    HashMap<UUID, List<Transaction>> senderTransactionMap = new HashMap<>();
    HashMap<UUID, List<Transaction>> receiverTransactionMap = new HashMap<>();

    @Override
    public Transaction save(Transaction transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);

        if(transaction.getSenderId() != null) {
            senderTransactionMap.computeIfAbsent(transaction.getSenderId(), k -> new ArrayList<>())
                    .add(transaction);
        }

        if(transaction.getReceiverId() != null) {
            receiverTransactionMap.computeIfAbsent(transaction.getReceiverId(), k -> new ArrayList<>())
                    .add(transaction);
        }

        return transaction;
    }

    @Override
    public List<Transaction> findBySenderId(UUID senderId) {
        List<Transaction> transactions = senderTransactionMap.get(senderId);
        if(transactions == null) {
            return Collections.emptyList();
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByReceiverId(UUID receiverId) {
        List<Transaction> transactions = receiverTransactionMap.get(receiverId);
        if(transactions == null) {
            return Collections.emptyList();
        }
        return transactions;
    }

    @Override
    public List<Transaction> findByDate(Date date) {
        return List.of();
    }

    @Override
    public List<Transaction> findByAmount(BigDecimal amount) {
        return List.of();
    }
}
