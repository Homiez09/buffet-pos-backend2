package com.buffetpos.backend.controllers;

import com.buffetpos.backend.models.Menu;
import com.buffetpos.backend.services.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer/menus")
@RequiredArgsConstructor
@SecurityRequirement(name = "accessCode")
@Tag( name="Menu Customer" )
public class MenuCustomerController {
    private final MenuService menuService;

    @Operation(
            summary = "Get Menus",
            description = "Gets all menus"
    )
    @GetMapping("/")
    public ResponseEntity<List<Menu>> getMenus() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @Operation(
            summary = "Get Menu",
            description = "Gets a menu by ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenu(@PathVariable Long id) {
        return menuService.getMenu(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
