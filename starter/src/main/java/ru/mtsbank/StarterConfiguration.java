package ru.mtsbank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mtsbank.ProjectListener;

@Configuration
public class StarterConfiguration {

    @Bean
    public ProjectListener projectListener(){
        return new ProjectListener();
    }
}

