package dev.fox.demobin.repository;

import dev.fox.demobin.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType, Long> {
    boolean existsById(Long id);
}
