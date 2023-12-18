package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.Wolf;

import java.math.BigDecimal;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    final int numberOfAnimals = CreateAnimalService.numberOfAnimals + 10;

    @Override
    public void createAnimals() {
        int startNumber = CreateAnimalService.numberOfAnimals + 1;
        do {
            BigDecimal randomCost = CreateAnimalService.randomCost(1, 5000);
            Animal animal = new Wolf("breed" + startNumber, "name" + startNumber, randomCost,
                    "character" + startNumber);
            System.out.println("Создано животное " + animal.getName() + " породы - " + animal.getBreed() + ", ценой - "
                    + animal.getCost() + "р. и характером - " + animal.getCharacter());
            startNumber++;
        } while (startNumber <= numberOfAnimals);
    }

    public void createAnimals(int N) {
        for (int i = numberOfAnimals + 1; i <= numberOfAnimals + N; i++) {
            BigDecimal randomCost = CreateAnimalService.randomCost(1, 5000);
            Animal animal = new Wolf("breed" + i, "name" + i, randomCost,
                    "character" + i);
            System.out.println("Создано животное " + animal.getName() + " породы - " + animal.getBreed() + ", ценой - "
                    + animal.getCost() + "р. и характером - " + animal.getCharacter());
        }
    }
}