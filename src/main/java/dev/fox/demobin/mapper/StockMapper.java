package dev.fox.demobin.mapper;

import dev.fox.demobin.dto.StockDto;
import dev.fox.demobin.model.Stock;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockMapper {
    @Mapping(source = "colorName", target = "color.name")
    @Mapping(source = "productTypeName", target = "productType.name")
    Stock toEntity(StockDto stockDto);

    @InheritInverseConfiguration(name = "toEntity")
    StockDto toDto(Stock stock);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @InheritConfiguration(name = "toEntity")
    Stock partialUpdate(StockDto stockDto, @MappingTarget Stock stock);

    Stock updateWithNull(StockDto stockDto, @MappingTarget Stock stock);
}