package com.interviewace.service;

import com.interviewace.constants.FileConstants;
import com.interviewace.dto.ResumeResponse;
import com.interviewace.entity.Resume;
import com.interviewace.entity.User;
import com.interviewace.exception.ResourceNotFoundException;
import com.interviewace.repository.ResumeRepository;
import com.interviewace.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeService(ResumeRepository resumeRepository,
                         UserRepository userRepository) {

        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    // Upload Resume
    public ResumeResponse uploadResume(Long userId, MultipartFile file) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + userId));

        Resume resume = resumeRepository.findByUser(user)
                .orElse(new Resume());

        if (resume.getId() != null) {
            deleteExistingFile(resume.getFilePath());
        }

        String filePath = saveFile(file);

        resume.setFileName(file.getOriginalFilename());
        resume.setFileType(file.getContentType());
        resume.setFilePath(filePath);
        resume.setUser(user);

        Resume savedResume = resumeRepository.save(resume);

        return mapToResumeResponse(savedResume);
    }

    // Get Resume
    public ResumeResponse getResumeByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + userId));

        Resume resume = resumeRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume not found for user ID: " + userId));

        return mapToResumeResponse(resume);
    }

    // Delete Resume
    public void deleteResume(Long userId) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with ID: " + userId));

        Resume resume = resumeRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Resume not found for user ID: " + userId));

        deleteExistingFile(resume.getFilePath());

        resumeRepository.delete(resume);
    }

    // Helper Method - Resume Response
    private ResumeResponse mapToResumeResponse(Resume resume) {

        return new ResumeResponse(
                resume.getId(),
                resume.getFileName(),
                resume.getFileType(),
                resume.getUploadedAt()
        );

    }

    // Helper Method - Save Files
    private String saveFile(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(FileConstants.UPLOAD_DIRECTORY);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path filePath = uploadPath.resolve(uniqueFileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }

    // Helper Method - Delete Existing Files
    private void deleteExistingFile(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        Files.deleteIfExists(path);

    }

}