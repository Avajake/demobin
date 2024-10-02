package dev.fox.demobin.service;

import dev.fox.demobin.dto.Stockfa;
import dev.fox.demobin.model.Stock;
import dev.fox.demobin.response.Stock.StockNotFoundException;

import java.util.List;

public interface StockService {
    Stock createOrUpdateStock(Stock stock) throws StockNotFoundException;
    Stockfa getStockId(Long id);
    List<Stockfa> getStockAll();
}
