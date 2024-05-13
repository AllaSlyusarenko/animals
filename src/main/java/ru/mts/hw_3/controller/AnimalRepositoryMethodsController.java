package ru.mts.hw_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.AnimalRepository;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/animals")
public class AnimalRepositoryMethodsController {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalRepositoryMethodsController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @ResponseBody
    @GetMapping("/all")
    public ResponseEntity<List<Animal>> getAllAnimals() {
        log.info("findAllAnimals-------------------------------------------------------------------------------------");
        List<Animal> animals = animalRepository.findAll();
        return ResponseEntity.ok(animals);
    }
}