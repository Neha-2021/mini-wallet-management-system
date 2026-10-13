package org.walletmanagementsystem.entity;

import java.util.UUID;

public class User {
    private String username;
    private UUID userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
