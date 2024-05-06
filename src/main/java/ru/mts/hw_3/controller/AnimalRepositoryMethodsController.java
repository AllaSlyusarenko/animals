package ru.mts.hw_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.AnimalsRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/animals")
public class AnimalRepositoryMethodsController {
    private final AnimalsRepository animalsRepository;

    @Autowired
    public AnimalRepositoryMethodsController(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @ResponseBody
    @GetMapping("/leap")
    public ResponseEntity<Map<String, LocalDate>> getLeapYearNames() throws IOException {
        log.info("findLeapYearNames-------------------------------------------------------------------------------------");
        Map<String, LocalDate> names = animalsRepository.findLeapYearNames();
        return ResponseEntity.ok(names);
    }

    @ResponseBody
    @GetMapping("/old/{age}")
    public ResponseEntity<Map<Animal, Integer>> getOlderAnimal(@PathVariable(name = "age") Integer age) throws IOException {
        log.info("findOlderAnimal---------------------------------------------------------------------------------------");
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(age);
        return ResponseEntity.ok(olderAnimals);
    }

    @ResponseBody
    @GetMapping("/dupl")
    public ResponseEntity<Map<String, List<Animal>>> getDuplicate() throws IOException {
        log.info("findDuplicate-----------------------------------------------------------------------------------------");
        Map<String, List<Animal>> animalsDuplicate = animalsRepository.findDuplicate();
        return ResponseEntity.ok(animalsDuplicate);
    }
}