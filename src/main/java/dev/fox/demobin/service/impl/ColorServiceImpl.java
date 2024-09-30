package dev.fox.demobin.service.impl;

import dev.fox.demobin.model.Color;
import dev.fox.demobin.repository.ColorRepo;
import dev.fox.demobin.response.color.ColorNotFoundException;
import dev.fox.demobin.response.color.ColorYesFoundException;
import dev.fox.demobin.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepo colorRepo;

    public boolean colorExistsIgnoreCase(String name) {
        // Проверяем наличие слова в базе данных без учета регистра
        return colorRepo.existsByNameIgnoreCase(name);
    }

    @Override
    public Color createColor(Color color) throws ColorYesFoundException {

        if (colorExistsIgnoreCase(color.getName()))
            throw new ColorYesFoundException("Данный цвет уже присуствует!");

        return colorRepo.save(color);
    }

    @Override
    public List<Color> getColorAll() {
        return colorRepo.findAll();
    }

    @Override
    public Optional<Color> getColorId(Long id) throws ColorNotFoundException {
        Color color = colorRepo.findById(id).orElse(null);
        if (color == null)
            throw new ColorNotFoundException("Данного цвета нет!");

        return Optional.of(color);
    }
}
