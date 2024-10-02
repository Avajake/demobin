package dev.fox.demobin.service.impl;

import dev.fox.demobin.common.ValidationDependenciesUtils;
import dev.fox.demobin.dto.Stockfa;
import dev.fox.demobin.model.Stock;
import dev.fox.demobin.repository.StockRepo;
import dev.fox.demobin.response.Stock.StockNotFoundException;
import dev.fox.demobin.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {


    private final StockRepo stockRepo;
    private final ValidationDependenciesUtils dependenciesUtils;

    public StockServiceImpl(StockRepo stockRepo, ValidationDependenciesUtils dependenciesUtils) {
        this.stockRepo = stockRepo;
        this.dependenciesUtils = dependenciesUtils;
    }

    @Override
    public Stock createOrUpdateStock(Stock stock) throws StockNotFoundException {
        //Валидность зависимостей
        dependenciesUtils.validationStockDependencies(stock);

        Optional<Stock> existingStock  = stockRepo.findByProductTypeAndColor(stock.getProductType(),stock.getColor());

        if (existingStock .isPresent()) {
            Stock stockUpdate = existingStock.get();
            stockUpdate.setQuantity(stockUpdate.getQuantity() + stock.getQuantity());
            return stockRepo.save(stockUpdate);
        }
        else {
            return stockRepo.save(stock);
        }

    }

    @Override
    public Stockfa getStockId(Long id) {
        Optional<Stock> stockId = stockRepo.findById(id);

        if (!stockId.isPresent()) {
            throw new StockNotFoundException("Stock с id " + id + " не найден");
        }

        return Stockfa.toModel(stockId.get());
    }

    @Override
    public List<Stockfa> getStockAll() {
        List<Stock> stocks = stockRepo.findAll();
        return stocks.stream()
                .map(Stockfa::toModel)
                .collect(Collectors.toList());
    }
}
