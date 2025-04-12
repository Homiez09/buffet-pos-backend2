package com.buffetpos.backend.controllers;

import com.buffetpos.backend.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> createCategory(String name) {
        categoryService.createCategory(name);
        return ResponseEntity.created(null).body("Category created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(Long id) {
        categoryService.removeCategory(id);
        return ResponseEntity.ok("Category removed successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, String name) {
        categoryService.updateCategory(id, name);
        return ResponseEntity.ok("Category updated successfully");
    }
}
