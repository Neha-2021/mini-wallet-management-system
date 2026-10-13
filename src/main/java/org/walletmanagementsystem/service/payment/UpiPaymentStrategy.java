package org.walletmanagementsystem.service.payment;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.enums.PaymentMode;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UpiPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentMode getPaymentMode() {
        return PaymentMode.UPI;
    }

    @Override
    public Boolean initiateTopUp(UUID userId, BigDecimal amount) {
        // payment mode is out of scope
        return true;
    }
}
