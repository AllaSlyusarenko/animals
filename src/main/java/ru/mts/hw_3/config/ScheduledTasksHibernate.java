package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.entity.Creature;
import ru.mts.hw_3.repository.CreatureRepository;
import ru.mts.hw_3.service.CreatureService;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ScheduledTasksHibernate {
    @Autowired
    CreatureService creatureService;
    @Autowired
    CreatureRepository creatureRepository;

    @Scheduled(fixedDelayString = "${application.scheduledDB.time}")
    public void doTasks() {
        try {
            Map<String, List<Creature>> createCreatures = creatureService.createCreatures(5);
            log.info(creatureRepository.findAll().toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}