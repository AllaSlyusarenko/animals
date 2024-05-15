package ru.mts.hw_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.service.AnimalService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/animals")
public class AnimalMethodsController {
    private final AnimalService animalService;

    @Autowired
    public AnimalMethodsController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return ResponseEntity.ok(animals);
    }

    @PostMapping("/add")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        Animal animalOut = animalService.saveAnimal(animal);
        return ResponseEntity.ok(animalOut);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimal(id);
    }
}