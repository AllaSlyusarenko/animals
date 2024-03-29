package ru.mts.hw_3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  MapperConfig {
    @Bean
    public ObjectMapper animalMapper(){
        ObjectMapper mapper = new ObjectMapper();

        return mapper;
    }
}
