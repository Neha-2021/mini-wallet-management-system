package org.walletmanagementsystem.request;

import org.walletmanagementsystem.enums.PaymentMode;
import java.math.BigDecimal;

public record TopUpRequest (
    String username,
    BigDecimal amount,
    PaymentMode paymentMode
){}
