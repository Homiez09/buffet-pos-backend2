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
@SecurityRequirement(name = "bearerAuth")
@Tag( name="Category Manager" )
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(
            summary = "Get Categories",
            description = "Gets all categories"
    )
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(
            summary = "Get Category",
            description = "Gets a category by ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Category",
            description = "Creates a new category"
    )
    @PostMapping("/")
    public ResponseEntity<?> createCategory(String name) {
        categoryService.createCategory(name);
        return ResponseEntity.created(null).body("Category created successfully");
    }

    @Operation(
            summary = "Remove Category",
            description = "Removes a category"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(Long id) {
        categoryService.removeCategory(id);
        return ResponseEntity.ok("Category removed successfully");
    }

    @Operation(
            summary = "Update Category",
            description = "Updates a category"
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, String name) {
        categoryService.updateCategory(id, name);
        return ResponseEntity.ok("Category updated successfully");
    }
}
