package dev.fox.demobin.mapper;

import dev.fox.demobin.dto.ProductTypeDto;
import dev.fox.demobin.model.ProductType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductTypeMapper {
    @Mapping(source = "productDetailHeight", target = "productDetail.height")
    @Mapping(source = "productDetailWidth", target = "productDetail.width")
    @Mapping(source = "productDetailLength", target = "productDetail.length")
    @Mapping(source = "productDetailWeight", target = "productDetail.weight")
    @Mapping(source = "productDetailId", target = "productDetail.id")
    ProductType toEntity(ProductTypeDto productTypeDto);

    @InheritInverseConfiguration(name = "toEntity")
    ProductTypeDto toDto(ProductType productType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @InheritConfiguration(name = "toEntity")
    ProductType partialUpdate(ProductTypeDto productTypeDto, @MappingTarget ProductType productType);

    ProductType updateWithNull(ProductTypeDto productTypeDto, @MappingTarget ProductType productType);
}