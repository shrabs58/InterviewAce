package com.interviewace.controller;

import com.interviewace.dto.UpdateProfileRequest;
import com.interviewace.dto.UserResponse;
import com.interviewace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get All Users
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User By ID
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update Profile
    @PutMapping("/{id}")
    public UserResponse updateProfile(@PathVariable Long id,
                                      @Valid @RequestBody UpdateProfileRequest request) {
        return userService.updateProfile(id, request);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}