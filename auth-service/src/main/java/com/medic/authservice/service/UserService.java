package com.medic.authservice.service;

import com.medic.authservice.model.User;
import com.medic.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        // This method should interact with the user repository to find a user by email.
        return userRepository.findByEmail(email);
    }
}
