package ru.mts.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal {
    public Predator() {
    }

    public Predator(AnimalType animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(animalType, breed, name, cost, character, birthDate);
    }
}