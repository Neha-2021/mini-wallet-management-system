package org.walletmanagementsystem.service.payment;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.enums.PaymentMode;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class DebitCardPaymentStrategy implements PaymentStrategy {
    @Override
    public PaymentMode getPaymentMode() {
        return PaymentMode.DEBIT_CARD;
    }

    @Override
    public Boolean initiateTopUp(UUID userId, BigDecimal amount) {
        return true;
    }
}
