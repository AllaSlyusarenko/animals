package ru.mtsbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mtsbank.ProjectListener;
import ru.mtsbank.service.CreateAnimalService;
import ru.mtsbank.service.CreateAnimalServiceImpl;

@Configuration
@ComponentScan
public class StarterConfiguration {
    @Bean
    public ProjectListener projectListener() {
        return new ProjectListener();
    }

    @Bean
    @Scope(value = "prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
}