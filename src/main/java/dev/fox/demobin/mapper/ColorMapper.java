package dev.fox.demobin.mapper;

import dev.fox.demobin.dto.ColorDto;
import dev.fox.demobin.model.Color;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColorMapper {
    Color toEntity(ColorDto colorDto);

    ColorDto toDto(Color color);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Color partialUpdate(ColorDto colorDto, @MappingTarget Color color);

    Color updateWithNull(ColorDto colorDto, @MappingTarget Color color);
}