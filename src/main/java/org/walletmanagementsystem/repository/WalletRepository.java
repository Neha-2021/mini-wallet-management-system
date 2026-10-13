package org.walletmanagementsystem.repository;

import org.walletmanagementsystem.entity.Wallet;

import java.util.UUID;

public interface WalletRepository {
    Wallet createWallet(UUID userId);

    Boolean existsByUserId(UUID userId);

    Wallet getWalletByUserId(UUID userId);
}
