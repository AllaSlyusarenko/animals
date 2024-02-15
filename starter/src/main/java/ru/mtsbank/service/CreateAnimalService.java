package ru.mtsbank.service;

import ru.mtsbank.entity.Animal;
import ru.mtsbank.entity.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * интерфейс имеет default метод, который позволяет создавать новые объекты необходимого количества
 *
 * @version 1.1
 * @autor Алла Слюсаренко
 */
public interface CreateAnimalService {
    int numberOfAnimals = 10;

    /**
     * Метод - создает новые объекты необходимого количества
     */
    default Animal[] createAnimals() {
        int startNumber = 1;
        Animal[] animals = new Animal[numberOfAnimals];
        int index = 0;
        while (startNumber <= numberOfAnimals) {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            Animal animal = new Wolf("breed" + startNumber, "name" + startNumber, randomCost,
                    "character" + startNumber, randomBirthDay);
            animals[index] = animal;
            startNumber++;
            index++;
        }
        return animals;
    }

    static BigDecimal randomCost(int min, int max) {
        int randomNum = new Random().nextInt(max - min + 1) + min;
        return new BigDecimal(randomNum + ".67896789");
    }

    static LocalDate randomBirthDay() {
        LocalDate start = LocalDate.of(1970, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        return start.plusDays(new Random().nextInt((int) days + 1));
    }
}