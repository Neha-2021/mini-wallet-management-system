package org.walletmanagementsystem.repository;

import org.walletmanagementsystem.entity.Transaction;
import org.walletmanagementsystem.enums.PaymentMode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    List<Transaction> findBySenderId(UUID senderId);

    List<Transaction> findByReceiverId(UUID receiverId);
}
