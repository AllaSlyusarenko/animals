package ru.mts.hw_3.entity;

import java.util.Random;

public enum AnimalType {
    DOG,
    WOLF;

    public static AnimalType getRandomAnimalType() {
        return AnimalType.values()[new Random().nextInt(AnimalType.values().length)];
    }
}