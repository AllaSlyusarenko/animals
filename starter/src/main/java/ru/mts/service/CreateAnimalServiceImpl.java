package ru.mts.service;

import org.springframework.beans.factory.annotation.Value;
import ru.mts.entity.AbstractAnimal;
import ru.mts.entity.Animal;
import ru.mts.entity.AnimalFactory;
import ru.mts.entity.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

import static ru.mts.service.CreateAnimalService.randomBirthDay;
import static ru.mts.service.CreateAnimalService.randomCost;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final AnimalFactory animalFactory = new AnimalFactory();
    private AnimalType animalType;
    @Value("${dog.names}")
    private String[] namesDog;

    @Value("${wolf.names}")
    private String[] namesWolf;

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Метод - создает массив животных фиксированного количества (numberOfNewAnimals = 10)
     */
    @Override
    public Animal[] createAnimals() {
        int startNumber = 1;
        int numberOfNewAnimals = 10;
        Animal[] animals = new AbstractAnimal[numberOfNewAnimals];
        int index = 0;
        do {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + startNumber, getRandomNameByTypeAnimal(animalType), randomCost,
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
    @Override
    public Animal[] createAnimals(int N) {
        if (N <= 0) {
            System.out.print("The number of animals must be greater than 0");
            throw new IllegalArgumentException("The number of animals must be greater than 0");
        }
        Animal[] animals = new AbstractAnimal[N];
        int index = 0;
        for (int i = 1; i <= N; i++) {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + i, getRandomNameByTypeAnimal(animalType), randomCost,
                    "character" + i, randomBirthDay);
            animals[index] = animal;
            index++;
        }
        return animals;
    }

    /**
     * Метод - получает случайное имя животного
     */
    private String getRandomNameByTypeAnimal(AnimalType animalType) {
        String name = null;
        switch (animalType) {
            case DOG:
                name = namesDog[new Random().nextInt(namesDog.length)];
                break;
            case WOLF:
                name = namesWolf[new Random().nextInt(namesWolf.length)];
                break;
        }
        return name;
    }
}