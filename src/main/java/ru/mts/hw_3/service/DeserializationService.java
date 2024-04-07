package ru.mts.hw_3.service;

import ru.mts.entity.AbstractAnimal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DeserializationService {
    Map<String, LocalDate> deserializationFindLeapYearNames() throws IOException;
    Map<AbstractAnimal, Integer> deserializationFindOlderAnimal() throws IOException;
    Map<String, List<AbstractAnimal>> deserializationFindDuplicate() throws IOException;
    List<AbstractAnimal> deserializationFindOldAndExpensive() throws IOException;
    List<String> deserializationFindMinConstAnimals() throws IOException;
    Double deserializationFindAverageAge() throws IOException;
}
