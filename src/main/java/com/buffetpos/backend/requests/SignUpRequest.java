package com.buffetpos.backend.requests;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
}