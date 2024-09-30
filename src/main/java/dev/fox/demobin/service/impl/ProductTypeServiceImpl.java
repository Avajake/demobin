package dev.fox.demobin.service.impl;

import dev.fox.demobin.model.ProductType;
import dev.fox.demobin.repository.ProductTypeRepo;
import dev.fox.demobin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepo typeRepo;

    @Override
    public ProductType createProduct(ProductType productType) {
        return typeRepo.save(productType);
    }

    @Override
    public Optional<ProductType> getProductId(Long id) {
        return typeRepo.findById(id);
    }

    @Override
    public List<ProductType> getProductAll() {
        return typeRepo.findAll();
    }
}
