package dev.fox.demobin;

import com.fasterxml.jackson.databind.JsonNode;
import dev.fox.demobin.dto.ColorDto;
import dev.fox.demobin.mapper.ColorMapper;
import dev.fox.demobin.model.Color;
import dev.fox.demobin.repository.ColorRepo;
import io.amplicode.rautils.patch.ObjectPatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/admin-ui/colors")
@RequiredArgsConstructor
public class ColorResource {

    private final ColorRepo colorRepo;

    private final ColorMapper colorMapper;

    private final ObjectPatcher objectPatcher;

    @GetMapping
    public Page<ColorDto> getList(Pageable pageable) {
        Page<Color> colors = colorRepo.findAll(pageable);
        return colors.map(colorMapper::toDto);
    }

    @GetMapping("/{id}")
    public ColorDto getOne(@PathVariable Long id) {
        Optional<Color> colorOptional = colorRepo.findById(id);
        return colorMapper.toDto(colorOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    @GetMapping("/by-ids")
    public List<ColorDto> getMany(@RequestParam List<Long> ids) {
        List<Color> colors = colorRepo.findAllById(ids);
        return colors.stream()
                .map(colorMapper::toDto)
                .toList();
    }

    @PostMapping
    public ColorDto create(@RequestBody ColorDto dto) {
        Color color = colorMapper.toEntity(dto);
        Color resultColor = colorRepo.save(color);
        return colorMapper.toDto(resultColor);
    }

    @PatchMapping("/{id}")
    public ColorDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        Color color = colorRepo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        ColorDto colorDto = colorMapper.toDto(color);
        colorDto = objectPatcher.patch(colorDto, patchNode);
        colorMapper.updateWithNull(colorDto, color);

        Color resultColor = colorRepo.save(color);
        return colorMapper.toDto(resultColor);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        Collection<Color> colors = colorRepo.findAllById(ids);

        for (Color color : colors) {
            ColorDto colorDto = colorMapper.toDto(color);
            colorDto = objectPatcher.patch(colorDto, patchNode);
            colorMapper.updateWithNull(colorDto, color);
        }

        List<Color> resultColors = colorRepo.saveAll(colors);
        return Color.getId(resultColors);
    }

    @DeleteMapping("/{id}")
    public ColorDto delete(@PathVariable Long id) {
        Color color = colorRepo.findById(id).orElse(null);
        if (color != null) {
            colorRepo.delete(color);
        }
        return colorMapper.toDto(color);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        colorRepo.deleteAllById(ids);
    }
}
