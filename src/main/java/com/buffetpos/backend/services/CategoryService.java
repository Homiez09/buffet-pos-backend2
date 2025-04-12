package com.buffetpos.backend.services;

import com.buffetpos.backend.models.Category;
import com.buffetpos.backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new RuntimeException("Category already exists");
        }

        Category category = Category.builder()
                .name(name).build();

        categoryRepository.save(category);
    }

    public void removeCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (category.isDeleted()) {
            throw new RuntimeException("Category already deleted");
        }

        category.setDeleted(true);
        categoryRepository.save(category);
    }

    public void updateCategory(Long id, String name) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (categoryRepository.existsByName(name)) {
            throw new RuntimeException("Category already exists");
        }

        category.setName(name);
        categoryRepository.save(category);
    }
}
