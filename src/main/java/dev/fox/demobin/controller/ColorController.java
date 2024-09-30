package dev.fox.demobin.controller;

import dev.fox.demobin.model.Color;
import dev.fox.demobin.response.color.ColorNotFoundException;
import dev.fox.demobin.response.color.ColorYesFoundException;
import dev.fox.demobin.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity getColorALl() {
        return ResponseEntity.ok(colorService.getColorAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity getColorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(colorService.getColorId(id));
        }
        catch (ColorNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }

    @PostMapping()
    public ResponseEntity createColor(@RequestBody Color color) throws ColorYesFoundException {
        try {
            return ResponseEntity.ok(colorService.createColor(color));
        }
        catch (ColorYesFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка!");
        }
    }



}
