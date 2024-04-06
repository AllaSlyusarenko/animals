package ru.mts.hw_3.service;

import ru.mts.entity.AbstractAnimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DeserializationService {
    Map<String, LocalDate> deserializationFindLeapYearNames();
    Map<AbstractAnimal, Integer> deserializationFindOlderAnimal();
    Map<String, List<AbstractAnimal>> deserializationFindDuplicate();
    List<AbstractAnimal> deserializationFindOldAndExpensive();
    List<String> deserializationFindMinConstAnimals();
    Double deserializationFindAverageAge();
}
