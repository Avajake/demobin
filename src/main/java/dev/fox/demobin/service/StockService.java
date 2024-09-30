package dev.fox.demobin.service;

import dev.fox.demobin.dto.StockDto;
import dev.fox.demobin.model.Stock;
import dev.fox.demobin.response.Stock.StockNotFoundException;

import java.util.List;

public interface StockService {
    Stock createOrUpdateStock(Stock stock) throws StockNotFoundException;
    StockDto  getStockId(Long id);
    List<StockDto> getStockAll();
}
