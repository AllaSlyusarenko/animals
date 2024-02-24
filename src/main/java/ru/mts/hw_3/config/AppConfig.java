package ru.mts.hw_3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mts.hw_3.repository.AnimalsRepository;
import ru.mts.hw_3.repository.AnimalsRepositoryImpl;
import ru.mts.service.CreateAnimalService;

@Configuration
@ComponentScan(basePackages = "ru.mts")
public class AppConfig {
    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}