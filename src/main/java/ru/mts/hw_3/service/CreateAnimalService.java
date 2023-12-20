package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.AbstractAnimal;
import ru.mts.hw_3.entity.Wolf;

import java.math.BigDecimal;
import java.util.Random;

/**
 * интерфейс имеет default метод, который позволяет создавать новые объекты необходимого количества
 *
 * @version 1.1
 * @autor Алла Слюсаренко
 */
public interface CreateAnimalService {
    int numberOfAnimals = 10;

    default void createAnimals() {
        int startNumber = 1;
        while (startNumber <= numberOfAnimals) {
            BigDecimal randomCost = randomCost(1, 5000);
            AbstractAnimal animal = new Wolf("breed" + startNumber, "name" + startNumber, randomCost,
                    "character" + startNumber);
            System.out.println("Создано животное " + animal.getName() + " породы - " + animal.getBreed() + ", ценой - "
                    + animal.getCost() + "р. и характером - " + animal.getCharacter());
            startNumber++;
        }
    }

    static BigDecimal randomCost(int min, int max) {
        Random rn = new Random();
        int randomNum = rn.nextInt(max - min + 1) + min;
        return new BigDecimal(randomNum + ".67896789");
    }
}