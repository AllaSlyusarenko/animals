package ru.mts.hw_3.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class AnimalMapper extends ObjectMapper {
    public AnimalMapper() {
        this.registerModule(new JavaTimeModule());
        this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}