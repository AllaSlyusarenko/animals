package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.service.AnimalService;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private AnimalService animalService;


    @Scheduled(fixedDelayString = "${application.scheduled.time}")
    public void doRepositoryTasks() {

        log.info("findAllAnimals-------------------------------------------------------------------------------------");
        log.info(animalService.getAllAnimals().toString() + "\n");

    }
}