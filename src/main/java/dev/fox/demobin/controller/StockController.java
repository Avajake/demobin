package dev.fox.demobin.controller;

import dev.fox.demobin.model.Stock;
import dev.fox.demobin.response.Stock.StockNotFoundException;
import dev.fox.demobin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public ResponseEntity getStockAll() {
        return ResponseEntity.ok(stockService.getStockAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity getStockId(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.getStockId(id));
    }

    @PostMapping("/add")
    public ResponseEntity createOrUpdateStock(@RequestBody Stock stock) {
        try {
            return ResponseEntity.ok(stockService.createOrUpdateStock(stock));
        }
        catch (StockNotFoundException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

}
