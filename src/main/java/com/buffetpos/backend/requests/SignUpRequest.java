package com.buffetpos.backend.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
}