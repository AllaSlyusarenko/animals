package ru.mts.hw_3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config {
    @Bean
    @Primary
    public ObjectMapper animalMapper() {
        return new AnimalMapper();
    }
}