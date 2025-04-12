package com.buffetpos.backend.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
