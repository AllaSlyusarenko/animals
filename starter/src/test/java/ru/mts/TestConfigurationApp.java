package ru.mts;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.entity.AnimalType;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;
@TestConfiguration
@TestComponent
public class TestConfigurationApp {
    @Bean
    public CreateAnimalService createAnimalService() {
        System.out.println("made changes to the bean starter");
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.setAnimalType(AnimalType.getRandomAnimalType());
        return createAnimalService;
    }
}