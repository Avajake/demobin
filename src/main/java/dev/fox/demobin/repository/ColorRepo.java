package dev.fox.demobin.repository;

import dev.fox.demobin.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ColorRepo extends JpaRepository<Color,Long> {
    Optional<Color> findByName(String name);
    Optional<Color> findById(Long id);

    boolean existsByNameIgnoreCase(String name);
}
