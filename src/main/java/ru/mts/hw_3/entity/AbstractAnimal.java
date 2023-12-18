package ru.mts.hw_3.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbstractAnimal implements Animal {
    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине, должна быть округлена до 2 знаков после запятой
    protected String character; // характер

    public AbstractAnimal(String breed, String name, BigDecimal cost, String character) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.CEILING);
        this.character = character;
    }
    @Override
    public String getBreed() {
        return this.breed;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BigDecimal getCost() {
        return this.cost;
    }

    @Override
    public String getCharacter() {
        return this.character;
    }
}