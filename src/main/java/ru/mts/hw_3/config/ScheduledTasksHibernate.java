package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.AnimalRepository;
import ru.mts.hw_3.service.AnimalService;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ScheduledTasksHibernate {
    @Autowired
    AnimalService creatureService;
    @Autowired
    AnimalRepository creatureRepository;

    @Scheduled(fixedDelayString = "${application.scheduledDB.time}")
    public void doTasks() {
        try {
            Map<String, List<Animal>> createCreatures = creatureService.createAnimals(5);
            log.info(creatureRepository.findAll().toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}