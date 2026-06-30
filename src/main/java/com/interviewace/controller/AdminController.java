package com.interviewace.controller;

import com.interviewace.dto.AdminAnalyticsResponse;
import com.interviewace.service.AdminAnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminAnalyticsService adminAnalyticsService;

    public AdminController(AdminAnalyticsService adminAnalyticsService) {
        this.adminAnalyticsService = adminAnalyticsService;
    }

    // Get Admin Analytics
    @GetMapping("/analytics")
    public AdminAnalyticsResponse getAnalytics() {
        return adminAnalyticsService.getAnalytics();
    }

}