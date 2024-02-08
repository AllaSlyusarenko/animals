package ru.mts.hw_3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.hw_3.repository.AnimalsRepository;
import ru.mts.hw_3.repository.AnimalsRepositoryImpl;
import ru.mts.hw_3.service.CreateAnimalService;
import ru.mts.hw_3.service.CreateAnimalServiceImpl;

@Configuration
@ComponentScan(basePackages = "ru.mts")
public class AppConfig {
    @Bean
    @Scope(value = "prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}