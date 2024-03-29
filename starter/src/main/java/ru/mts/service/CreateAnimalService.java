package ru.mts.service;

import ru.mts.entity.Animal;
import ru.mts.entity.AnimalFactory;
import ru.mts.entity.AnimalType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static ru.mts.entity.AnimalType.getRandomAnimalType;

/**
 * интерфейс имеет default метод, который позволяет создавать новые объекты необходимого количества
 *
 * @version 1.1
 * @autor Алла Слюсаренко
 */

public interface CreateAnimalService {
    /**
     * Метод - создает новые объекты фиксированного количества
     */
    default Map<String, List<Animal>> createAnimals() throws IOException {
        AnimalType animalType = getRandomAnimalType();
        AnimalFactory animalFactory = new AnimalFactory();
        int numberOfAnimals = 10;
        int startNumber = 1;
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        while (startNumber <= numberOfAnimals) {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + startNumber, "name" + startNumber, randomCost,
                    "character" + startNumber, randomBirthDay);
            animals.add(animal);
            startNumber++;
        }
        animalsMap.put(animalType.name(), animals);
        return animalsMap;
    }

    /**
     * Метод - создает новые объекты необходимого количества
     */
    default Map<String, List<Animal>> createAnimals(int N) throws IOException {
        AnimalType animalType = getRandomAnimalType();
        AnimalFactory animalFactory = new AnimalFactory();
        int startNumber = 1;
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        while (startNumber <= N) {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + startNumber, "name" + startNumber, randomCost,
                    "character" + startNumber, randomBirthDay);
            animals.add(animal);
            startNumber++;
        }
        animalsMap.put(animalType.name(), animals);
        return animalsMap;
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