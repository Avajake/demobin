package dev.fox.demobin.dto;

import lombok.Value;

/**
 * DTO for {@link dev.fox.demobin.model.ProductType}
 */
@Value
public class ProductTypeDto {
    Long id;
    String name;
    String description;
    Long productDetailId;
    double productDetailWeight;
    double productDetailLength;
    double productDetailWidth;
    double productDetailHeight;
}