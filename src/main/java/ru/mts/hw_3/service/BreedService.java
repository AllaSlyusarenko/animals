package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Breed;

import java.util.List;

public interface BreedService {
    List<Breed> getBreeds();

    Breed saveBreed(Breed breed);
}
