package com.interviewace.controller;

import com.interviewace.dto.ResumeResponse;
import com.interviewace.service.ResumeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    // Upload Resume
    @PostMapping("/upload/{userId}")
    public ResumeResponse uploadResume(@PathVariable Long userId,
                                       @RequestParam("file") MultipartFile file) throws IOException {

        return resumeService.uploadResume(userId, file);
    }

    // Get Resume Details
    @GetMapping("/user/{userId}")
    public ResumeResponse getResumeByUser(@PathVariable Long userId) {

        return resumeService.getResumeByUser(userId);
    }

    // Delete Resume
    @DeleteMapping("/{userId}")
    public void deleteResume(@PathVariable Long userId) throws IOException {

        resumeService.deleteResume(userId);
    }

}