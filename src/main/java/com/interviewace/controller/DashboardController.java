package com.interviewace.controller;

import com.interviewace.dto.DashboardResponse;
import com.interviewace.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Get Dashboard
    @GetMapping("/{userId}")
    public DashboardResponse getDashboard(@PathVariable Long userId) {

        return dashboardService.getDashboard(userId);
    }

}