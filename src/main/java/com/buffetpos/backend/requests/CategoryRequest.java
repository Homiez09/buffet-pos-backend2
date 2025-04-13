package com.buffetpos.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty or whitespace")
    private String name;
}
