
package com.uovt.productapi.service;

import com.uovt.productapi.model.User;
import com.uovt.productapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTER USER
    public User register(String username, String password) {

        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        String encodedPassword =
                passwordEncoder.encode(password);

        User user = new User(
                username,
                encodedPassword
        );

        return userRepository.save(user);
    }

    // FIND USER FOR LOGIN
    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}

