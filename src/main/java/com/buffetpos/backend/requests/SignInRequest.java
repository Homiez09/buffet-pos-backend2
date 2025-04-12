package com.buffetpos.backend.requests;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
