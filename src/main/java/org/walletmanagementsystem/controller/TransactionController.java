package org.walletmanagementsystem.controller;

import org.springframework.web.bind.annotation.*;
import org.walletmanagementsystem.entity.Transaction;
import org.walletmanagementsystem.enums.PaymentMode;
import org.walletmanagementsystem.enums.TransactionDirection;
import org.walletmanagementsystem.enums.TransactionSortField;
import org.walletmanagementsystem.request.SendMoneyRequest;
import org.walletmanagementsystem.request.TopUpRequest;
import org.walletmanagementsystem.response.GetTransactionResponse;
import org.walletmanagementsystem.service.TransactionOrchestrationService;
import org.walletmanagementsystem.service.TransactionService;
import org.walletmanagementsystem.service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionOrchestrationService transactionOrchestrationService;
    private final UserService userService;
    private final TransactionService transactionService;

    public TransactionController(
            TransactionOrchestrationService transactionOrchestrationService,
            UserService userService,
            TransactionService transactionService
    ) {
        this.transactionOrchestrationService = transactionOrchestrationService;
        this.userService = userService;
        this.transactionService = transactionService;
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

    @GetMapping("/v1/getTransactions")
    public List<GetTransactionResponse> getTransactions(
            @RequestParam String username,
            @RequestParam("filter") TransactionDirection transactionDirection,
            @RequestParam("sorter") TransactionSortField transactionSortField
            ) {
        UUID userId = userService.getUserId(username);
        return transactionService.getTransactionsByUserId(userId, transactionDirection, transactionSortField);
    }
}
