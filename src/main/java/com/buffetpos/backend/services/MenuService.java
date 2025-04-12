package com.buffetpos.backend.services;

import com.buffetpos.backend.models.Menu;
import com.buffetpos.backend.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Optional<Menu> getMenu(Long id) {
        return menuRepository.findById(id);
    }

}
