package com.interviewace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateProfileRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Size(max = 15, message = "Phone number cannot exceed 15 characters")
    private String phone;

    @Size(max = 100, message = "Aspiring job role cannot exceed 100 characters")
    private String aspiringJobRole;

    @Size(max = 50, message = "Experience cannot exceed 50 characters")
    private String experience;

    public UpdateProfileRequest() {
    }

    public String getFullName() {
        return fullName;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAspiringJobRole(String aspiringJobRole) {
        this.aspiringJobRole = aspiringJobRole;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}