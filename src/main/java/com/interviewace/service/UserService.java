package com.interviewace.service;

import com.interviewace.dto.LoginRequest;
import com.interviewace.dto.LoginResponse;
import com.interviewace.dto.RegisterRequest;
import com.interviewace.dto.UpdateProfileRequest;
import com.interviewace.dto.UserResponse;
import com.interviewace.entity.User;
import com.interviewace.enums.Role;
import com.interviewace.exception.ResourceNotFoundException;
import com.interviewace.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register
    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        // Every registered user is a STUDENT
        user.setRole(Role.STUDENT);

        User savedUser = userRepository.save(user);

        return mapToUserResponse(savedUser);
    }

    // Login
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password.");
        }

        return mapToLoginResponse(user);
    }

    // Get All Users
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    // Get User By ID
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + id));

        return mapToUserResponse(user);
    }

    // Update Profile
    public UserResponse updateProfile(Long id, UpdateProfileRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + id));

        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setAspiringJobRole(request.getAspiringJobRole());
        user.setExperience(request.getExperience());

        User updatedUser = userRepository.save(user);

        return mapToUserResponse(updatedUser);
    }

    // Delete User
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + id));

        userRepository.delete(user);
    }

    // Helper Method - User Response
    private UserResponse mapToUserResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole(),
                user.getPhone(),
                user.getAspiringJobRole(),
                user.getExperience(),
                user.getCreatedAt()
        );
    }

    // Helper Method - Login Response
    private LoginResponse mapToLoginResponse(User user) {

        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole()
        );
    }

}