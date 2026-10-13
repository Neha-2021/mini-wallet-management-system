package org.walletmanagementsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.walletmanagementsystem.enums.PaymentMode;
import org.walletmanagementsystem.request.SendMoneyRequest;
import org.walletmanagementsystem.request.TopUpRequest;
import org.walletmanagementsystem.service.TransactionOrchestrationService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionOrchestrationService transactionOrchestrationService;

    public TransactionController(TransactionOrchestrationService transactionOrchestrationService) {
        this.transactionOrchestrationService = transactionOrchestrationService;
    }

    @PostMapping("/v1/topUp")
    public String topUp(@RequestBody TopUpRequest topUpRequest) {
        String username = topUpRequest.username();
        BigDecimal amount = topUpRequest.amount();
        PaymentMode paymentMode = topUpRequest.paymentMode();
        transactionOrchestrationService.topUp(
                username,
                amount,
                paymentMode
        );
        return username+" credited with Rs."+amount.toString()+" via "+paymentMode.toString();
    }

    @PostMapping("/v1/sendMoney")
    public String sendMoney(@RequestBody SendMoneyRequest sendMoneyRequest) {
        String senderName = sendMoneyRequest.senderName();
        String receiverName = sendMoneyRequest.receiverName();
        BigDecimal amount = sendMoneyRequest.amount();
        transactionOrchestrationService.sendMoney(senderName, receiverName, amount);
        return senderName+" has transferred Rs."+amount.toString()+" to "+receiverName;
    }
}
