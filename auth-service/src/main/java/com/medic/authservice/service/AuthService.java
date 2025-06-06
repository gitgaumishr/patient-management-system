package com.medic.authservice.service;

import com.medic.authservice.dto.LoginRequestDTO;
import com.medic.authservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    // This service would typically interact with a user repository to validate credentials
    // and generate a JWT token. For simplicity, we will return a dummy token.
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        // Here you would implement the logic to validate the user's credentials
        // and generate a JWT token if valid.

        Optional<String> userToken = userService
                .findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(
                        loginRequestDTO.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(),u.getRole()));

        return userToken;
    }

    public Boolean validateToken(String token) {
        // This method should validate the JWT token and return true if valid, false otherwise.
        try {
            jwtUtil.validateToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
