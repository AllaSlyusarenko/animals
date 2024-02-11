package ru.mtsbank.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mtsbank.NamesProperties;
import ru.mtsbank.ProjectListener;
import ru.mtsbank.service.CreateAnimalService;
import ru.mtsbank.service.CreateAnimalServiceImpl;

@Configuration
//@EnableConfigurationProperties(NamesProperties.class)
public class StarterConfiguration {

    @Bean
//    @ConditionalOnProperty(value = "name.names")
    public ProjectListener projectListener(){
        return new ProjectListener();
    }

    @Bean
    @Scope(value = "prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }
}