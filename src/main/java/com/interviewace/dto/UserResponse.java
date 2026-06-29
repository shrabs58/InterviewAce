package com.interviewace.dto;

import com.interviewace.enums.Role;

import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private Role role;
    private String phone;
    private String aspiringJobRole;
    private String experience;
    private LocalDateTime createdAt;

    public UserResponse() {
    }

    public UserResponse(Long id,
                        String fullName,
                        String email,
                        Role role,
                        String phone,
                        String aspiringJobRole,
                        String experience,
                        LocalDateTime createdAt) {

        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.aspiringJobRole = aspiringJobRole;
        this.experience = experience;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public String getAspiringJobRole() {
        return aspiringJobRole;
    }

    public String getExperience() {
        return experience;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}