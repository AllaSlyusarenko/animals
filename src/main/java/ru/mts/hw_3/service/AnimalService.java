package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> getAllAnimals();
    Animal saveAnimal(Animal animal);
    Animal getAnimalById(Integer id);
    void deleteAnimal(Integer id);
    Animal addAnimal(Animal animal);
}