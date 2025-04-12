package com.buffetpos.backend.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {
    @Operation(
            summary = "Home Page",
            description = "Returns a welcome message when accessing the root endpoint of BuffetPOS API",
            tags = { "General" }
    )
    @GetMapping("/")
    public String index() {
        return "Welcome to BuffetPOS Backend!";
    }
}
