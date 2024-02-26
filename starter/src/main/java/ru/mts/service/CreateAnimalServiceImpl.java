package ru.mts.service;

import org.springframework.beans.factory.annotation.Value;
import ru.mts.entity.Animal;
import ru.mts.entity.AnimalFactory;
import ru.mts.entity.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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
    public Map<String, List<Animal>> createAnimals() {
        int startNumber = 1;
        int numberOfNewAnimals = 10;
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        do {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + startNumber, getRandomNameByTypeAnimal(animalType), randomCost,
                    "character" + startNumber, randomBirthDay);
            animals.add(animal);
            startNumber++;
        } while (startNumber <= numberOfNewAnimals);
        animalsMap.put(animalType.name(), animals);
        return animalsMap;
    }

    /**
     * Метод - создает массив животных необходимого количества(N)
     */
    @Override
    public Map<String, List<Animal>> createAnimals(int N) {
        if (N <= 0) {
            System.out.print("The number of animals must be greater than 0");
            throw new IllegalArgumentException("The number of animals must be greater than 0");
        }
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        List<Animal> animals = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            Animal animal = animalFactory.createAnimal(animalType, "breed" + i, getRandomNameByTypeAnimal(animalType), randomCost,
                    "character" + i, randomBirthDay);
            animals.add(animal);
        }
        animalsMap.put(animalType.name(), animals);
        return animalsMap;
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