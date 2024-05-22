package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.AnimalType;

import java.util.List;

public interface AnimalTypeService {
    List<AnimalType> getAllAnimalTypes();
    AnimalType saveAnimalType(AnimalType animalType);
}