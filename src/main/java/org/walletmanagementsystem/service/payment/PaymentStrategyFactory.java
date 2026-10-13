package org.walletmanagementsystem.service.payment;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.enums.PaymentMode;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PaymentStrategyFactory {

    private final Map<PaymentMode, PaymentStrategy> paymentStrategies;

    public PaymentStrategyFactory(List<PaymentStrategy> paymentStrategies) {
       this.paymentStrategies = paymentStrategies.stream()
               .collect(Collectors.toMap(
                       PaymentStrategy::getPaymentMode,
               Function.identity()
       ));
    }

    public PaymentStrategy getPaymentStrategy(PaymentMode paymentMode) {
        PaymentStrategy paymentStrategy = paymentStrategies.get(paymentMode);
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Unknown payment mode: " + paymentMode);
        }
        return paymentStrategy;
    }
}
