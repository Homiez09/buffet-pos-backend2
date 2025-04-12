package com.buffetpos.backend.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Category Manager")
public class FileUploadController {

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "file", required = true) MultipartFile file) {
        System.out.println("Uploading: " + file.getOriginalFilename());

        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("image/png") || contentType.equals("image/jpeg"))) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid file type. Only PNG and JPEG are allowed.");
        }

        try {
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            Path uploadDir = Paths.get("uploads/images");
            Files.createDirectories(uploadDir);

            file.transferTo(uploadDir.resolve(fileName));

            String fileUrl = "/uploads/images/" + fileName;
            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            e.printStackTrace(); // สำหรับ debug log
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }
}
