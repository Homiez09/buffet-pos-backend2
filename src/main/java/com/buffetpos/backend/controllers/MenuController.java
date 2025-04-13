package com.buffetpos.backend.controllers;

import com.buffetpos.backend.models.Menu;
import com.buffetpos.backend.requests.MenuRequest;
import com.buffetpos.backend.requests.MenuUpdateRequest;
import com.buffetpos.backend.services.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag( name="Menu Manager" )
public class MenuController {
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

    @Operation(
            summary = "Create Menu",
            description = "Creates a new menu"
    )
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createMenu(@Valid @ModelAttribute MenuRequest menuRequest) {

        menuService.createMenu(menuRequest);
        return ResponseEntity.created(null).body("Menu created successfully");
    }

    @Operation(
            summary = "Remove Menu",
            description = "Removes a menu"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeMenu(@PathVariable Long id) {
        menuService.removeMenu(id);
        return ResponseEntity.ok("Menu removed successfully");
    }

    @Operation(
            summary = "Update Menu",
            description = "Updates a menu"
    )
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMenu(@PathVariable Long id, @Valid @RequestBody MenuUpdateRequest menuUpdateRequest) {
        menuService.updateMenu(id, menuUpdateRequest);
        return ResponseEntity.ok("Menu updated successfully");
    }
}
