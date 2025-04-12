package com.buffetpos.backend.repositories;

import com.buffetpos.backend.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    boolean existsByName(String name);
}
