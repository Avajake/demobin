package dev.fox.demobin.repository;

import dev.fox.demobin.model.Color;
import dev.fox.demobin.model.ProductType;
import dev.fox.demobin.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepo extends JpaRepository<Stock,Long> {
    Optional<Stock> findByProductTypeAndColor(ProductType productType, Color color);
}
