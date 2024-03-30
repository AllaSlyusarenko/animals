package ru.mts.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.*;
import ru.mts.ProjectListener;
import ru.mts.entity.AbstractAnimal;
import ru.mts.service.CreateAnimalService;
import ru.mts.service.CreateAnimalServiceImpl;

import java.util.Set;

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

//    @Bean("animalMapper")
////    @Primary
//    public ObjectMapper getAnimalMapper() {
//        ObjectMapper objectMapper = new AnimalMapper();
//
//        objectMapper.registerModule(new JavaTimeModule());
//
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(AbstractAnimal.class, new AnimalSerializer());
//        objectMapper.registerModule(simpleModule);
//        Set<Object> obj = objectMapper.getRegisteredModuleIds();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
////        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        return objectMapper;
//    }
}