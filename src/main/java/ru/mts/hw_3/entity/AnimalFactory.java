package ru.mts.hw_3.entity;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class AnimalFactory {
    /**
     * Метод - для создания животного по параметрам:
     * тип животного, порода, имя, цена, характер, дата рождения
     */
    public Animal createAnimal(AnimalType animalType, String breed, String name, BigDecimal cost, String character,
                               LocalDate birthDate) {
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