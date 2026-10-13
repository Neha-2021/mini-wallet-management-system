package org.walletmanagementsystem.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {
    private UUID walletId;
    private UUID userId;
    private BigDecimal balance;

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
