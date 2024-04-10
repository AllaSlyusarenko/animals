package ru.mts;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import ru.mts.entity.AnimalType;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

import java.io.IOException;

@ContextConfiguration
public class TestConfigurationApp {
    @Bean
    public CreateAnimalService createAnimalService() throws IOException {
        System.out.println("made changes to the bean starter");
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();
        createAnimalService.setAnimalType(AnimalType.getRandomAnimalType());
        return createAnimalService;
    }
}