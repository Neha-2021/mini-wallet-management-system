package org.walletmanagementsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.walletmanagementsystem.entity.Wallet;
import org.walletmanagementsystem.service.TransactionService;
import org.walletmanagementsystem.service.UserService;
import org.walletmanagementsystem.service.WalletService;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;
    private final UserService userService;

    public WalletController(WalletService walletService,  UserService userService) {
        this.walletService = walletService;
        this.userService = userService;
    }

    @PostMapping("/v1/fetchBalance")
    public String fetchBalance(@RequestParam("username") String username) {
        UUID userId = userService.getUserId(username);
        return (username+ "'s wallet has Rs."+walletService.getBalance(userId));
    }
}
