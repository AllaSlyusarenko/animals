package ru.mts.hw_3.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AnimalFactory {
    public Animal createAnimal(AnimalType animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        Animal animal = null;
        switch (animalType) {
            case DOG:
                animal = new Dog(breed, name, cost, character, birthDate);
                break;
            case WOLF:
                animal = new Wolf(breed, name, cost, character, birthDate);
                break;
        }
        return animal;
    }
}