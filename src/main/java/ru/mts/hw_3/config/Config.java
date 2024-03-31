package ru.mts.hw_3.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ru.mts.entity.AbstractAnimal;

import java.time.LocalDate;
import java.util.Set;


@Configuration
public class Config {
    @Bean("animalMapper")
    @Primary
    public ObjectMapper getAnimalMapper() {
        return new AnimalMapper();
//        ObjectMapper objectMapper = new AnimalMapper();
////        objectMapper.registerModule(new JavaTimeModule());
//
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer());
//        simpleModule.addSerializer(AbstractAnimal.class, new AnimalSerializer());
//        objectMapper.registerModule(simpleModule);
//        Set<Object> obj = objectMapper.getRegisteredModuleIds();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
////        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
////        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        return objectMapper;
    }
}