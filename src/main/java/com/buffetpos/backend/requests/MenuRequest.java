package com.buffetpos.backend.requests;

import com.buffetpos.backend.models.Category;
import com.buffetpos.backend.models.Menu;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuRequest {
    private String name;
    private MultipartFile image;
    private Long categoryId;
}

