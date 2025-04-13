package com.buffetpos.backend.requests;

import com.buffetpos.backend.models.Category;
import com.buffetpos.backend.models.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuRequest {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty or whitespace")
    private String name;

    @NotNull(message = "Image cannot be null")
    private MultipartFile image;

    @NotNull(message = "Category cannot be null")
    private Long categoryId;
}

