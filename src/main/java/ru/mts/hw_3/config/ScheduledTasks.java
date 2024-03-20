package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.entity.Animal;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.exception.IncorrectParameterException;
import ru.mts.hw_3.repository.AnimalsRepositoryImpl;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {
    private final AnimalsRepositoryImpl animalsRepository;

    public ScheduledTasks(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedDelayString = "${application.scheduled.time}")
    public void doRepositoryTasks() {
        try {
            log.info("findLeapYearNames-------------------------------------------------------------------------------------");
            log.info(animalsRepository.findLeapYearNames() + "\n");

            log.info("findOlderAnimal---------------------------------------------------------------------------------------");
            int age = 15;
            log.info(animalsRepository.findOlderAnimal(age) + "\n");

            log.info("findDuplicate-----------------------------------------------------------------------------------------");
            animalsRepository.printDuplicate();
            log.info("");

            log.info("findAverageAge-----------------------------------------------------------------------------------------");
            List<Animal> animalList = animalsRepository.prepareListAnimals();
            animalsRepository.findAverageAge(animalList);

            log.info("findOldAndExpensive------------------------------------------------------------------------------------");
            log.info(animalsRepository.findOldAndExpensive(animalList) + "\n");

            log.info("findMinConstAnimals------------------------------------------------------------------------------------");
            log.info(animalsRepository.findMinConstAnimals(animalList) + "\n");
        } catch (IncorrectParameterException ex) {
            log.error(Arrays.toString(ex.getStackTrace()), ex);
        } catch (CollectionEmptyException e) {
            log.error(Arrays.toString(e.getStackTrace()), e);
        }
    }
}