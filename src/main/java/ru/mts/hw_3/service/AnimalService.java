package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.exception.CollectionEmptyException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalService {
    Map<String, List<Animal>> createAnimals(int N);
    Map<String, LocalDate> findLeapYearNames() throws IOException;
    Map<Animal, Integer> findOlderAnimal(int N) throws IOException;
    Map<String, List<Animal>> findDuplicate() throws IOException;
    List<Animal> prepareListAnimals();
    Double findAverageAge(List<Animal> animalList) throws CollectionEmptyException, IOException;
    List<Animal> findOldAndExpensive(List<Animal> animalList) throws CollectionEmptyException, IOException;
    List<String> findMinConstAnimals(List<Animal> animalList) throws CollectionEmptyException, IOException;
}