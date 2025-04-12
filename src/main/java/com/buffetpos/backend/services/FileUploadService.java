package com.buffetpos.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("No file uploaded.");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("image/png") || contentType.equals("image/jpeg"))) {
            throw new IllegalArgumentException("Invalid file type. Only PNG and JPEG are allowed.");
        }

        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        Path uploadPath = Paths.get("uploads/images");
        Files.createDirectories(uploadPath);
        file.transferTo(uploadPath.resolve(fileName));

        return "/uploads/images/" + fileName;
    }
}