package ru.mtsbank.service;

import org.springframework.beans.factory.annotation.Value;
import ru.mtsbank.entity.AbstractAnimal;
import ru.mtsbank.entity.Animal;
import ru.mtsbank.entity.AnimalFactory;
import ru.mtsbank.entity.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    final int numberOfNewAnimals = 10;
    private final AnimalFactory animalFactory = new AnimalFactory();
    private AnimalType animalType;
    @Value("${names}")
    private String[] names;

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Метод - создает массив животных фиксированного количества (numberOfNewAnimals = 10)
     */
    @Override
    public Animal[] createAnimals() {
        int startNumber = 1;
        Animal[] animals = new AbstractAnimal[numberOfNewAnimals];
        int index = 0;
        do {
            BigDecimal randomCost = CreateAnimalService.randomCost(1, 5000);
            LocalDate randomBirthDay = CreateAnimalService.randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + startNumber, getRandomName(), randomCost,
                    "character" + startNumber, randomBirthDay);
            animals[index] = animal;
            startNumber++;
            index++;
        } while (startNumber <= numberOfNewAnimals);
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
        for (int i = 1; i <= N; i++) {
            BigDecimal randomCost = CreateAnimalService.randomCost(1, 5000);
            LocalDate randomBirthDay = CreateAnimalService.randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + i, getRandomName(), randomCost,
                    "character" + i, randomBirthDay);
            animals[index] = animal;
            index++;
        }
        return animals;
    }

    /**
     * Метод - получает случайное имя животного
     */
    private String getRandomName() {
        return names[new Random().nextInt(names.length)];
    }
}