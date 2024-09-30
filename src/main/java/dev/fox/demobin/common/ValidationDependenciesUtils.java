package dev.fox.demobin.common;

import dev.fox.demobin.model.Color;
import dev.fox.demobin.model.ProductType;
import dev.fox.demobin.model.Stock;
import dev.fox.demobin.repository.ColorRepo;
import dev.fox.demobin.repository.ProductTypeRepo;
import dev.fox.demobin.response.Stock.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ValidationDependenciesUtils {

    private final ColorRepo colorRepo;
    private final ProductTypeRepo productTypeRepo;

    public ValidationDependenciesUtils(ColorRepo colorRepo, ProductTypeRepo productTypeRepo) {
        this.colorRepo = colorRepo;
        this.productTypeRepo = productTypeRepo;
    }

    public void validationStockDependencies(Stock stock) {
        // Проверка на null и наличие id у ProductType
        if (stock.getProductType() == null || stock.getProductType().getId() == null) {
            throw new StockNotFoundException("ProductType не указан или ID не передан!");
        }

        // Проверка, существует ли ProductType по указанному ID
        if (!productTypeRepo.existsById(stock.getProductType().getId())) {
            throw new StockNotFoundException("ProductType с id " + stock.getProductType().getId() + " не существует!");
        }

        // Проверка на null и наличие id у Color
        if (stock.getColor() == null || stock.getColor().getId() == null) {
            throw new StockNotFoundException("Color не указан или ID не передан!");
        }

        // Проверка, существует ли Color по указанному ID
        if (!colorRepo.existsById(stock.getColor().getId())) {
            throw new StockNotFoundException("Color с id " + stock.getColor().getId() + " не существует!");
        }
    }
}
