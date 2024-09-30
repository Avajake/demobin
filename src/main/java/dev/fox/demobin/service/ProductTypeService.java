package dev.fox.demobin.service;

import dev.fox.demobin.model.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService {
    ProductType createProduct(ProductType productType);
    Optional<ProductType> getProductId(Long id);
    List<ProductType> getProductAll();
}
