package org.walletmanagementsystem.service.payment;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.enums.PaymentMode;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentMode getPaymentMode() {
        return PaymentMode.CREDIT_CARD;
    }

    @Override
    public Boolean initiateTopUp(UUID userId, BigDecimal amount) {
        return true;
    }
}
