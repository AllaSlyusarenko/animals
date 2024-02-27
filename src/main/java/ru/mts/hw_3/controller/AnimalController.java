package ru.mts.hw_3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mts.hw_3.repository.AnimalsRepository;

import java.util.Map;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalsRepository animalsRepository;
    private static final org.slf4j.Logger log
            = org.slf4j.LoggerFactory.getLogger(AnimalController.class);

    public AnimalController(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @GetMapping
    public void getRepositoryMethods() {
        log.info("Вызов всех методов репозитория");
        log.info("findLeapYearNames-------------------------------------------------------------------------------------");
        log.info(animalsRepository.findLeapYearNames() + "\n");

        log.info("findOlderAnimal---------------------------------------------------------------------------------------");
        int age = 15;
        log.info(animalsRepository.findOlderAnimal(age) + "\n");

        log.info("findDuplicate-----------------------------------------------------------------------------------------");
        Map<String, Integer> animalsDuplicate = animalsRepository.findDuplicate();
        animalsRepository.printDuplicate();
    }
}