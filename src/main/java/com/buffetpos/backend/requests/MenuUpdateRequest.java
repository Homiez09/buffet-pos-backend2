package com.buffetpos.backend.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuUpdateRequest {
    private String name;
    private MultipartFile image;
    private Boolean isActive;
    private Long categoryId;
}
