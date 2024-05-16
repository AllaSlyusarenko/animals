package ru.mts.hw_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        Animal animalOut = animalService.saveAnimal(animal);
        return new ResponseEntity<>(animalOut, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}