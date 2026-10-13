package org.walletmanagementsystem.repository;

import org.walletmanagementsystem.entity.User;

import java.util.UUID;

public interface UserRepository {
    User findByUsername(String username);

    Boolean existsByUsername(String username);

    User onboard(String username, UUID userId);
}
