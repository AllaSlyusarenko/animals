package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    final int numberOfNewAnimals = 10;
    final int numberOfAnimalsImpl = CreateAnimalService.numberOfAnimals + numberOfNewAnimals;
    private final AnimalFactory animalFactory;

    public CreateAnimalServiceImpl(AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
    }

    /**
     * Метод - создает массив животных фиксированного количества (numberOfNewAnimals = 10)
     */
    @Override
    public Animal[] createAnimals() {
        int startNumber = CreateAnimalService.numberOfAnimals + 1;
        Animal[] animals = new AbstractAnimal[numberOfNewAnimals];
        int index = 0;
        do {
            BigDecimal randomCost = CreateAnimalService.randomCost(1, 5000);
            LocalDate randomBirthDay = CreateAnimalService.randomBirthDay();
            Animal animal = animalFactory.createAnimal(getRandomAnimalType(), "breed" + startNumber, "name" + startNumber, randomCost,
                    "character" + startNumber, randomBirthDay);
            animals[index] = animal;
            startNumber++;
            index++;
        } while (startNumber <= numberOfAnimalsImpl);
        return animals;
    }

    /**
     * Метод - создает массив животных необходимого количества(N)
     */
    public Animal[] createAnimals(int N) {
        if (N <= 0) {
            System.out.print("Количество животных должно быть больше 0 - ");
            return new Animal[0];
        }
        Animal[] animals = new AbstractAnimal[N];
        int index = 0;
        for (int i = numberOfAnimalsImpl + 1; i <= numberOfAnimalsImpl + N; i++) {
            BigDecimal randomCost = CreateAnimalService.randomCost(1, 5000);
            LocalDate randomBirthDay = CreateAnimalService.randomBirthDay();
            Animal animal = animalFactory.createAnimal(getRandomAnimalType(), "breed" + i, "name" + i, randomCost,
                    "character" + i, randomBirthDay);
            animals[index] = animal;
            index++;
        }
        return animals;
    }

    private AnimalType getRandomAnimalType() {
        return AnimalType.values()[new Random().nextInt(AnimalType.values().length)];
    }
}