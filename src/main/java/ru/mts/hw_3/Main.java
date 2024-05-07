package ru.mts.hw_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.mts.hw_3.config.ConfigPropertiesForDB;

@EnableScheduling
@SpringBootApplication
//@EnableJpaRepositories("ru.mts.hw_3.repository")
//@EntityScan("ru.mts.hw_3")
//@EnableConfigurationProperties(ConfigPropertiesForDB.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}