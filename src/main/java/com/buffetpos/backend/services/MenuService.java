package com.buffetpos.backend.services;

import com.buffetpos.backend.models.Category;
import com.buffetpos.backend.models.Menu;
import com.buffetpos.backend.repositories.CategoryRepository;
import com.buffetpos.backend.repositories.MenuRepository;
import com.buffetpos.backend.requests.MenuRequest;
import com.buffetpos.backend.requests.MenuUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final FileUploadService fileUploadService;
    private final CategoryRepository categoryRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenu(Long id) {
        return menuRepository.findById(id);
    }

    public void createMenu(MenuRequest menuRequest) {
        if (menuRepository.existsByName(menuRequest.getName())) {
            throw new RuntimeException("Menu already exists");
        }

        try {
            String imageUrl = fileUploadService.uploadImage(menuRequest.getImage());

            Menu menu = Menu.builder()
                    .name(menuRequest.getName())
                    .imageUrl(imageUrl)
                    .category(menuRequest.getCategoryId() != null ? categoryRepository.findById(menuRequest.getCategoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found")) : null)
                    .isActive(false)
                    .build();

            menuRepository.save(menu);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Validation failed: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    public void updateMenu(Long id, MenuUpdateRequest menuUpdateRequest) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        if (!menu.getName().equals(menuUpdateRequest.getName()) && menuRepository.existsByName(menuUpdateRequest.getName())) {
            throw new RuntimeException("Menu already exists");
        }

        try {
            String imageUrl = fileUploadService.uploadImage(menuUpdateRequest.getImage());

            menu.setName(menuUpdateRequest.getName());
            menu.setImageUrl(imageUrl);
            menu.setCategory(menuUpdateRequest.getCategoryId() != null ? categoryRepository.findById(menuUpdateRequest.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found")) : null);
            menu.setActive(menuUpdateRequest.getIsActive());

            menuRepository.save(menu);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Validation failed: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    public void removeMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        if (menu.isDeleted()) {
            throw new RuntimeException("Menu already deleted");
        }

        menu.setDeleted(true);
        menuRepository.save(menu);
    }
}
