package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.ProjectListener;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

import java.io.IOException;

@Configuration
@ComponentScan
public class StarterConfiguration {
    @Bean
    public ProjectListener projectListener() {
        return new ProjectListener();
    }

    @Bean
    @Scope(value = "prototype")
    public CreateAnimalService createAnimalService() throws IOException {
        return new CreateAnimalServiceImpl();
    }
}