package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.service.AnimalService;
import ru.mts.hw_3.service.DeserializationService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private AnimalService animalService;
    @Autowired
    DeserializationService deserializationService;

    @Scheduled(fixedDelayString = "${application.scheduled.time}")
    public void doRepositoryTasks() {
        try {
            animalService.createAnimals(5);
            log.info("findLeapYearNames-------------------------------------------------------------------------------------");
            log.info(animalService.findLeapYearNames().toString() + "\n");

            log.info("findOlderAnimal---------------------------------------------------------------------------------------");
            int age = 15;
            log.info(animalService.findOlderAnimal(age).toString() + "\n");

            log.info("findDuplicate-----------------------------------------------------------------------------------------");
            log.info(animalService.findDuplicate().toString() + "\n");

            List<Animal> animalList = animalService.prepareListAnimals();
            log.info("findAverageAge-----------------------------------------------------------------------------------------");
            log.info(animalService.findAverageAge(animalList).toString() + "\n");

            log.info("findOldAndExpensive------------------------------------------------------------------------------------");
            log.info(animalService.findOldAndExpensive(animalList).toString() + "\n");

            log.info("findMinConstAnimals------------------------------------------------------------------------------------");
            log.info(animalService.findMinConstAnimals(animalList).toString() + "\n");

        } catch (CollectionEmptyException e) {
            log.error("Data collection does not meet the required conditions", e);
        } catch (IOException e) {
            log.error("Required file is missing", e);
        }
    }
}