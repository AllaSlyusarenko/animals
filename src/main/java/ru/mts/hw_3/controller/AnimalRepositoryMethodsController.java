package ru.mts.hw_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.entity.Animal;
import ru.mts.hw_3.repository.AnimalsRepository;

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
    public ResponseEntity<Object> getLeapYearNames() {
        log.info("Вызов всех методов репозитория");
        log.info("findLeapYearNames-------------------------------------------------------------------------------------");
        Map<String, LocalDate> names = animalsRepository.findLeapYearNames();
        return ResponseEntity.ok(names);
    }

    @ResponseBody
    @GetMapping("/old")
    public ResponseEntity<Object> getOlderAnimal() {
        log.info("findOlderAnimal---------------------------------------------------------------------------------------");
        int age = 15;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(age);
        return ResponseEntity.ok(olderAnimals);
    }

    @ResponseBody
    @GetMapping("/dupl")
    public ResponseEntity<Object> getDuplicate() {
        log.info("findDuplicate-----------------------------------------------------------------------------------------");
        Map<String, List<Animal>> animalsDuplicate = animalsRepository.findDuplicate();
        return ResponseEntity.ok(animalsDuplicate);
    }
}