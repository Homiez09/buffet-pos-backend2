package com.buffetpos.backend.controllers;

import com.buffetpos.backend.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag( name="Category Manager" )
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(
            summary = "Create Category",
            description = "Creates a new category"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/")
    public ResponseEntity<?> createCategory(String name) {
        categoryService.createCategory(name);
        return ResponseEntity.created(null).body("Category created successfully");
    }

    @Operation(
            summary = "Remove Category",
            description = "Removes a category"
    )
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(Long id) {
        categoryService.removeCategory(id);
        return ResponseEntity.ok("Category removed successfully");
    }

    @Operation(
            summary = "Update Category",
            description = "Updates a category"
    )
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, String name) {
        categoryService.updateCategory(id, name);
        return ResponseEntity.ok("Category updated successfully");
    }
}
