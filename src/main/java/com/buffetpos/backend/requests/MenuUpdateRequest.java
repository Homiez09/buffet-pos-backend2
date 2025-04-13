package com.buffetpos.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuUpdateRequest {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty or whitespace")
    private String name;

    @NotNull(message = "Image cannot be null")
    private MultipartFile image;

    @NotNull(message = "IsActive cannot be null")
    @NotBlank(message = "IsActive cannot be empty or whitespace")
    private Boolean isActive;

    @NotNull(message = "Category cannot be null")
    private Long categoryId;
}