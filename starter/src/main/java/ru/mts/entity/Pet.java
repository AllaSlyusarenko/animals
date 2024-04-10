package ru.mts.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Pet extends AbstractAnimal {
    public Pet() {
    }

    public Pet(AnimalType animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(animalType, breed, name, cost, character, birthDate);
    }
}