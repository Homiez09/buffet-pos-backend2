package com.buffetpos.backend.controllers;

import com.buffetpos.backend.requests.SignInRequest;
import com.buffetpos.backend.requests.SignUpRequest;
import com.buffetpos.backend.responses.AuthResponse;
import com.buffetpos.backend.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication" )
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Sign Up",
            description = "Registers a new user"
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        authService.signUp(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @Operation(
            summary = "Sign In",
            description = "Authenticates a user and returns a JWT token"
    )
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signIn(request));
    }
}