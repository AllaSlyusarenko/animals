package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.service.ServiceDB;

import java.sql.SQLException;

@Slf4j
@Component
public class ScheduledTasksDB {
    @Autowired
    private ServiceDB serviceDB;

    @Scheduled(fixedDelayString = "${application.scheduledDB.time}")
    public void doTasks() {
        try {
            serviceDB.getAllCreatures();
            serviceDB.getAllAnimalTypes();
            serviceDB.getAllHabitats();
            serviceDB.getAllProviders();
        } catch (SQLException e) {
            log.error("Error connecting to database", e);
            throw new RuntimeException(e);
        }
    }
}