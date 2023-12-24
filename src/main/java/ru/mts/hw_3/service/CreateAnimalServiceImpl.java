package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    final int numberOfAnimals = CreateAnimalService.numberOfAnimals + 10;
    private final AnimalFactory animalFactory;

    public CreateAnimalServiceImpl(AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
    }

    @Override
    public Animal[] createAnimals() {
        int startNumber = CreateAnimalService.numberOfAnimals + 1;
        Animal[] animals = new AbstractAnimal[10];
        int index = 0;
        do {
            BigDecimal randomCost = CreateAnimalService.randomCost(1, 5000);
            LocalDate randomBirthDay = CreateAnimalService.randomBirthDay();
            Animal animal = animalFactory.createAnimal(getRandomAnimalType(), "breed" + startNumber, "name" + startNumber, randomCost,
                    "character" + startNumber, randomBirthDay);
            animals[index] = animal;
            startNumber++;
            index++;
        } while (startNumber <= numberOfAnimals);
        return animals;
    }

    public Animal[] createAnimals(int N) {
        if (N <= 0) {
            System.out.print("Количество животных должно быть больше 0  ");
            return new Animal[0];
        }
        Animal[] animals = new AbstractAnimal[N];
        int index = 0;
        for (int i = numberOfAnimals + 1; i <= numberOfAnimals + N; i++) {
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
        Random random = new Random();
        return AnimalType.values()[random.nextInt(AnimalType.values().length)];
    }
}