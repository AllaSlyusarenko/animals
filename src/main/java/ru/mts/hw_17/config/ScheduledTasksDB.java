package ru.mts.hw_17.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_17.entity.Creature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasksDB {

    @Scheduled(fixedDelayString = "${application.scheduledDB.time}")
    public void doTasks() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/animals",
                "postgres",
                "iamroot")) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from animals.creature"
            );
            ResultSet resultSet = statement.executeQuery();
            List<Creature> creatures = new ArrayList<>();
            while (resultSet.next()) {
                creatures.add(new Creature(
                        resultSet.getInt("id_creature"),
                        resultSet.getString("name"),
                        resultSet.getInt("type_id"),
                        resultSet.getShort("age"),
                        resultSet.getDate("created").toLocalDate(),
                        resultSet.getDate("updated").toLocalDate()
                ));
            }
            log.info("Creatures in DB: {}", creatures);
        } catch (SQLException e) {
            log.error("Error connecting to database", e);
            throw new RuntimeException(e);
        }
    }
}