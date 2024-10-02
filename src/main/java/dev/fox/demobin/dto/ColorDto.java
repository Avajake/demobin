package dev.fox.demobin.dto;

import lombok.Value;

/**
 * DTO for {@link dev.fox.demobin.model.Color}
 */
@Value
public class ColorDto {
    Long id;
    String name;
}