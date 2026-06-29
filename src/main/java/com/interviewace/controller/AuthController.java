package com.interviewace.controller;

import com.interviewace.dto.LoginRequest;
import com.interviewace.dto.LoginResponse;
import com.interviewace.dto.RegisterRequest;
import com.interviewace.dto.UserResponse;
import com.interviewace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register
    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    // Login
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

}