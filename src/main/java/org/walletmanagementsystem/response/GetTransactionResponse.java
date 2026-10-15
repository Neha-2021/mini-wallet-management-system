package org.walletmanagementsystem.response;

import org.walletmanagementsystem.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetTransactionResponse(
        BigDecimal amount,
        TransactionType transactionType,
        LocalDateTime timestamp
) {

}


