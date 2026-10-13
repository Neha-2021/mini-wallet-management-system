package org.walletmanagementsystem.repository;

import org.springframework.stereotype.Repository;
import org.walletmanagementsystem.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserInMemoryRepositoryImpl implements UserRepository {
    Map<String, UUID> userMap = new HashMap<>();
    Map<String, User> userNameMap = new HashMap<>();
    @Override
    public User findByUsername(String username) {
        return userNameMap.get(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userNameMap.containsKey(username);
    }

    @Override
    public User onboard(String username, UUID userId) {
        User user = new User();
        user.setUsername(username);
        user.setUserId(userId);
        userMap.put(username, userId);
        userNameMap.put(username, user);
        return user;
    }
}
