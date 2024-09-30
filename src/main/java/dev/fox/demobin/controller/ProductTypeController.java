package dev.fox.demobin.controller;

import dev.fox.demobin.model.ProductType;
import dev.fox.demobin.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class ProductTypeController {

    @Autowired
    private ProductTypeService typeService;

    @GetMapping
    public ResponseEntity getProductAll() {
        return ResponseEntity.ok(typeService.getProductAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductId(@PathVariable Long id) {
        return ResponseEntity.ok(typeService.getProductId(id));
    }

    @PostMapping()
    public ResponseEntity createProduct(@RequestBody ProductType productType) {
        return ResponseEntity.ok(typeService.createProduct(productType));
    }

}
