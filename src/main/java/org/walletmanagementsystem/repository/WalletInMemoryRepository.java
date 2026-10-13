package org.walletmanagementsystem.repository;

import org.springframework.stereotype.Repository;
import org.walletmanagementsystem.entity.Wallet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

@Repository
public class WalletInMemoryRepository implements WalletRepository {
    HashMap<UUID, Wallet> wallets = new HashMap<>();

    @Override
    public Wallet createWallet(UUID userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setWalletId(UUID.randomUUID());
        wallet.setBalance(BigDecimal.ZERO);
        wallets.put(userId, wallet);
        return wallet;
    }

    @Override
    public Boolean existsByUserId(UUID userId) {
        return wallets.containsKey(userId);
    }

    @Override
    public Wallet getWalletByUserId(UUID userId) {
        return wallets.get(userId);
    }
}
