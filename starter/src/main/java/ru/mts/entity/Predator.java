package ru.mts.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal {
    public Predator() {
    }

    public Predator(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }
}