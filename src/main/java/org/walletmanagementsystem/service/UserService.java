package org.walletmanagementsystem.service;

import org.springframework.stereotype.Service;
import org.walletmanagementsystem.entity.User;
import org.walletmanagementsystem.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletService walletService;

    public UserService(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    public User register(String username) {
        Boolean userExists = userRepository.existsByUsername(username);
        if(userExists){
            throw new IllegalArgumentException(username + " already exists.");
        }
        User user = userRepository.onboard(username, UUID.randomUUID());
        walletService.onboard(user.getUserId());
        return user;
    }

    public UUID getUserId(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new IllegalArgumentException(username + " does not exist.");
        }
        return user.getUserId();
    }
}
