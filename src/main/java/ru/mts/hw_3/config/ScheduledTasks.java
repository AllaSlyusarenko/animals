package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.repository.AnimalsRepositoryImpl;
import ru.mts.hw_3.service.AnimalService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {
    @Value("${duplicate.time}")
    private String duplicateTime;
    @Value("${average.time}")
    private String averageAgeTime;
    private final AnimalsRepositoryImpl animalsRepository;
    @Autowired
    private AnimalService animalService;

    @Autowired
    public ScheduledTasks(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedDelayString = "${application.scheduled.time}")
    public void doRepositoryTasks() {
        try {
            animalService.createAnimals(5);
            log.info("findLeapYearNames-------------------------------------------------------------------------------------");
            log.info(animalsRepository.findLeapYearNames().toString() + "\n");

            log.info("findOlderAnimal---------------------------------------------------------------------------------------");
            int age = 15;
            log.info(animalsRepository.findOlderAnimal(age).toString() + "\n");

            log.info("findDuplicate-----------------------------------------------------------------------------------------");
            log.info(animalsRepository.findDuplicate().toString() + "\n");

            List<Animal> animalList = animalsRepository.prepareListAnimals();
            log.info("findAverageAge-----------------------------------------------------------------------------------------");
            log.info(animalsRepository.findAverageAge(animalList).toString() + "\n");

            log.info("findOldAndExpensive------------------------------------------------------------------------------------");
            log.info(animalsRepository.findOldAndExpensive(animalList).toString() + "\n");

            log.info("findMinConstAnimals------------------------------------------------------------------------------------");
            log.info(animalsRepository.findMinConstAnimals(animalList).toString() + "\n");
        } catch (CollectionEmptyException e) {
            log.error("Data collection does not meet the required conditions", e);
        } catch (IOException e) {
            log.error("Required file is missing", e);
        }
    }
}