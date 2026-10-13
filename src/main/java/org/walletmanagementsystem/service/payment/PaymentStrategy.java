package org.walletmanagementsystem.service.payment;

import org.springframework.stereotype.Component;
import org.walletmanagementsystem.enums.PaymentMode;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public interface PaymentStrategy {
    PaymentMode getPaymentMode();

    Boolean initiateTopUp(UUID userId, BigDecimal amount);
}
