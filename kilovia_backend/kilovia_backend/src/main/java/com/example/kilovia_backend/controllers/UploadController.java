package com.example.kilovia_backend.controllers;

import com.example.kilovia_backend.services.ImageUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final ImageUploadService uploadService;

    public UploadController(ImageUploadService uploadService) {
        this.uploadService = uploadService;
    }

    // Upload ảnh kèm tên folder
    @PostMapping
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("folder") String folder) {
        try {
            // Gọi service với folder truyền từ frontend
            String imageUrl = uploadService.uploadImage(file, folder);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }
}


