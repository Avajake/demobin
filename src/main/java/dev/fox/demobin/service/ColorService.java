package dev.fox.demobin.service;


import dev.fox.demobin.model.Color;
import dev.fox.demobin.response.color.ColorNotFoundException;
import dev.fox.demobin.response.color.ColorYesFoundException;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    Color createColor(Color color) throws ColorYesFoundException;
    List<Color> getColorAll();
    Optional<Color> getColorId(Long id) throws ColorNotFoundException;
}
