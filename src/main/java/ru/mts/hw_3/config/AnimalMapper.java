package ru.mts.hw_3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ru.mts.config.AnimalSerializer;
import ru.mts.entity.AbstractAnimal;

import java.time.LocalDate;

public class AnimalMapper extends ObjectMapper {
    private SimpleModule simpleModule = new SimpleModule();

    public AnimalMapper() {
        prepareModule(simpleModule);
        this.registerModule(simpleModule);
        this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private void prepareModule(SimpleModule simpleModule) {
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        simpleModule.addSerializer(AbstractAnimal.class, new AnimalSerializer());
//        simpleModule.addKeySerializer(AbstractAnimal.class, new AnimalSerializer());
    }
}
