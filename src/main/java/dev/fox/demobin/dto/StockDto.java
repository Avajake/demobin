package dev.fox.demobin.dto;

import dev.fox.demobin.model.Color;
import dev.fox.demobin.model.ProductType;
import dev.fox.demobin.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private Long id;
    private int quantity;
    private String productType;
    private String color;

    public static StockDto toModel(Stock stock) {
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setProductType(stock.getProductType().getName());
        stockDto.setColor(stock.getColor().getName());

        return stockDto;
    }
}
