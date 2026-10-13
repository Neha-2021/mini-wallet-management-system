package org.walletmanagementsystem.service;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.walletmanagementsystem.entity.Wallet;
import org.walletmanagementsystem.repository.WalletRepository;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    Wallet onboard(UUID userId) {
        Boolean walletExists = walletRepository.existsByUserId(userId);
        if (walletExists) {
            throw new IllegalStateException("Wallet already exists");
        }
        Wallet wallet = walletRepository.createWallet(userId);
        return wallet;
    }

    public BigDecimal getBalance(UUID userId) {
        Wallet wallet = getWallet(userId);
        return wallet.getBalance();
    }

    BigDecimal credit(UUID userId, BigDecimal amount) {
        Wallet wallet = getWallet(userId);
        wallet.setBalance(wallet.getBalance().add(amount));
        return wallet.getBalance();
    }

    BigDecimal debit(UUID userId, BigDecimal amount) {
        Wallet wallet = getWallet(userId);
        BigDecimal balance = wallet.getBalance();
        if(balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalStateException("User doesn't have sufficient amount to transfer " + amount + " Rs");
        }
        wallet.setBalance(balance.subtract(amount));
        return wallet.getBalance();
    }

    private @NonNull Wallet getWallet(UUID userId) {
        Wallet wallet = walletRepository.getWalletByUserId(userId);
        if (wallet == null) {
            throw new IllegalStateException("Wallet does not exist");
        }
        return wallet;
    }

}
