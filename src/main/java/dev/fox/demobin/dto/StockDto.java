package dev.fox.demobin.dto;

import lombok.Value;

/**
 * DTO for {@link dev.fox.demobin.model.Stock}
 */
@Value
public class StockDto {
    Long id;
    String productTypeName;
    String colorName;
    int quantity;
}