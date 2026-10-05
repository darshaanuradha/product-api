package com.uovt.productapi.controller;

import com.uovt.productapi.model.User;
import com.uovt.productapi.service.AuthService;
import com.uovt.productapi.service.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthService authService,
            JwtService jwtService,
            PasswordEncoder passwordEncoder) {

        this.authService = authService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {

        try {

            User user = authService.register(
                    request.username(),
                    request.password()
            );

            return ResponseEntity.ok(
                    "User registered successfully"
            );

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        try {

            User user = authService.findByUsername(
                    request.username()
            );

            boolean passwordMatches =
                    passwordEncoder.matches(
                            request.password(),
                            user.getPassword()
                    );

            if (!passwordMatches) {

                return ResponseEntity
                        .status(401)
                        .body("Invalid username or password");
            }

            String token =
                    jwtService.generateToken(
                            user.getUsername()
                    );

            return ResponseEntity.ok(
                    new LoginResponse(token)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }
    }

    // =========================
    // REQUEST RECORDS
    // =========================

    public record RegisterRequest(
            String username,
            String password
    ) {}

    public record LoginRequest(
            String username,
            String password
    ) {}

    // =========================
    // RESPONSE RECORD
    // =========================

    public record LoginResponse(
            String token
    ) {}
}
