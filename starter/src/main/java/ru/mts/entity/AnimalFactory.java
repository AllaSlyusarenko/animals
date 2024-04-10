package ru.mts.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AnimalFactory {
    /**
     * Метод - для создания животного по параметрам:
     * тип животного, порода, имя, цена, характер, дата рождения
     */
    public AbstractAnimal createAnimal(AnimalType animalType, String breed, String name, BigDecimal cost, String character,
                                       LocalDate birthDate) {
        AbstractAnimal animal = null;
        switch (animalType) {
            case DOG:
                animal = new Dog(AnimalType.DOG, breed, name, cost, character, birthDate);
                break;
            case WOLF:
                animal = new Wolf(AnimalType.WOLF, breed, name, cost, character, birthDate);
                break;
        }
        return animal;
    }
}