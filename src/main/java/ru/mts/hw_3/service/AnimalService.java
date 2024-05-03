package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Animal;

import java.util.List;
import java.util.Map;

public interface AnimalService {
    Map<String, List<Animal>> createAnimals(int N);
}