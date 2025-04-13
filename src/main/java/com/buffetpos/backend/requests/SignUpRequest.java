package com.buffetpos.backend.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignUpRequest {
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email is not valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be empty or whitespace")
    @Length(min = 8, message = "Password must be at least 8 characters")
    private String password;
}