package org.walletmanagementsystem.request;

import java.math.BigDecimal;

public record SendMoneyRequest(
        String senderName,
        String receiverName,
        BigDecimal amount
) {
}
