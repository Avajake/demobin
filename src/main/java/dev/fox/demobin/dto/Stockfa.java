package dev.fox.demobin.dto;

import dev.fox.demobin.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stockfa {
    private Long id;
    private int quantity;
    private String productType;
    private String color;

    public static Stockfa toModel(Stock stock) {
        Stockfa stockfa = new Stockfa();
        stockfa.setId(stock.getId());
        stockfa.setQuantity(stock.getQuantity());
        stockfa.setProductType(stock.getProductType().getName());
        stockfa.setColor(stock.getColor().getName());

        return stockfa;
    }
}
