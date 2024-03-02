package ru.mts.hw_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.entity.Animal;
import ru.mts.hw_3.repository.AnimalsRepository;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalsRepository animalsRepository;
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(AnimalController.class);

    @Autowired
    public AnimalController(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @ResponseBody
    @GetMapping("/leap")
    public ResponseEntity<Object> getLeapYearNames() {
        log.info("Вызов всех методов репозитория");
        log.info("findLeapYearNames-------------------------------------------------------------------------------------");
        Map<String, LocalDate> names = animalsRepository.findLeapYearNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/old")
    public ResponseEntity<Object> getOlderAnimal() {
        log.info("findOlderAnimal---------------------------------------------------------------------------------------");
        int age = 15;
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(age);
        return new ResponseEntity<>(olderAnimals, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/dupl")
    public ResponseEntity<Object> getDuplicate() {
        log.info("findDuplicate-----------------------------------------------------------------------------------------");
        Map<String, Integer> animalsDuplicate = animalsRepository.findDuplicate();
        return new ResponseEntity<>(animalsDuplicate, HttpStatus.OK);
    }
}