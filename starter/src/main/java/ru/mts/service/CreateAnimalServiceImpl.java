package ru.mts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import ru.mts.entity.AbstractAnimal;
import ru.mts.entity.AnimalFactory;
import ru.mts.entity.AnimalType;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;

import static ru.mts.service.CreateAnimalService.randomBirthDay;
import static ru.mts.service.CreateAnimalService.randomCost;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    private final AnimalFactory animalFactory = new AnimalFactory();
    private AnimalType animalType;
    Path path = new ClassPathResource("animals/logData.txt", this.getClass().getClassLoader()).getFile().toPath();
    @Value("${dog.names.random}")
    private String[] namesDogRandom;
    @Value("${dog.names.nerandom}")
    private String[] namesDogNeRandom;
    @Value("${dog.breeds}")
    private String[] breedsDog;
    @Value("${dog.prices}")
    private String[] pricesDog;
    @Value("${dog.characters}")
    private String[] charactersDog;
    @Value("${dog.dates}")
    private String[] datesDog;

    @Value("${wolf.names.random}")
    private String[] namesWolfRandom;

    @Value("${wolf.names.nerandom}")
    private String[] namesWolfNeRandom;
    @Value("${wolf.breeds}")
    private String[] breedsWolf;
    @Value("${wolf.prices}")
    private String[] pricesWolf;
    @Value("${wolf.characters}")
    private String[] charactersWolf;
    @Value("${wolf.dates}")
    private String[] datesWolf;

    public CreateAnimalServiceImpl() throws IOException {
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    @Override
    public AnimalType getAnimalType() {
        return animalType;
    }

    /**
     * Метод - создает животных фиксированного количества (numberOfNewAnimals = 10)
     */
    @Override
    public Map<String, List<AbstractAnimal>> createAnimals() throws IOException {
        int startNumber = 1;
        int numberOfNewAnimals = 10;
        Map<String, List<AbstractAnimal>> animalsMap = new HashMap<>();
        List<AbstractAnimal> animals = new ArrayList<>();
        Files.write(path, "".getBytes());
        do {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            AbstractAnimal animal = animalFactory.createAnimal(animalType, "breed" + startNumber, getRandomNameByTypeAnimal(animalType), randomCost,
                    "character" + startNumber, randomBirthDay);
            animals.add(animal);
            String stringForWrite = startNumber + " " + animalType + " " + animal.getBreed() + " " + animal.getName()
                    + " " + animal.getCost() + " " + animal.getBirthDate() + "\n";
            Files.write(path, stringForWrite.getBytes(), StandardOpenOption.APPEND);
            startNumber++;
        } while (startNumber <= numberOfNewAnimals / 2);

        do {
            AbstractAnimal animal = animalFactory.createAnimal(animalType, getRandomBreedByTypeAnimal(animalType),
                    getNameByTypeAnimal(animalType), getRandomPriceByTypeAnimal(animalType),
                    getRandomCharacterByTypeAnimal(animalType), getRandomDateByTypeAnimal(animalType));
            animals.add(animal);
            String stringForWrite = startNumber + " " + animalType + " " + animal.getBreed() + " " + animal.getName()
                    + " " + animal.getCost() + " " + animal.getBirthDate() + "\n";
            Files.write(path, stringForWrite.getBytes(), StandardOpenOption.APPEND);
            startNumber++;
        } while (startNumber <= numberOfNewAnimals);
        animalsMap.put(animalType.name(), animals);
        return animalsMap;
    }

    /**
     * Метод - создает животных необходимого количества(N)
     */
    @Override
    public Map<String, List<AbstractAnimal>> createAnimals(int N) throws IOException {
        if (N <= 0) {
            System.out.print("The number of animals must be greater than 0");
            throw new IllegalArgumentException("The number of animals must be greater than 0");
        }
        Map<String, List<AbstractAnimal>> animalsMap = new HashMap<>();
        List<AbstractAnimal> animals = new ArrayList<>();
        Files.write(path, "".getBytes());
        for (int i = 1; i < N / 2; i++) {
            BigDecimal randomCost = randomCost(1, 5000);
            LocalDate randomBirthDay = randomBirthDay();
            AbstractAnimal animal = animalFactory.createAnimal(animalType, "breed" + i, getRandomNameByTypeAnimal(animalType), randomCost,
                    "character" + i, randomBirthDay);
            animals.add(animal);
            String stringForWrite = i + " " + animalType + " " + animal.getBreed() + " " + animal.getName()
                    + " " + animal.getCost() + " " + animal.getBirthDate() + "\n";
            Files.write(path, stringForWrite.getBytes(), StandardOpenOption.APPEND);
        }

        for (int i = N / 2; i <= N; i++) {
            AbstractAnimal animal = animalFactory.createAnimal(animalType, getRandomBreedByTypeAnimal(animalType),
                    getNameByTypeAnimal(animalType), getRandomPriceByTypeAnimal(animalType),
                    getRandomCharacterByTypeAnimal(animalType), getRandomDateByTypeAnimal(animalType));
            animals.add(animal);
            String stringForWrite = i + " " + animalType + " " + animal.getBreed() + " " + animal.getName()
                    + " " + animal.getCost() + " " + animal.getBirthDate() + "\n";
            Files.write(path, stringForWrite.getBytes(), StandardOpenOption.APPEND);
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
                name = namesDogRandom[new Random().nextInt(namesDogRandom.length)];
                break;
            case WOLF:
                name = namesWolfRandom[new Random().nextInt(namesWolfRandom.length)];
                break;
        }
        return name;
    }

    /**
     * Метод - получает имя животного из списка
     */
    private String getNameByTypeAnimal(AnimalType animalType) {
        String name = null;
        switch (animalType) {
            case DOG:
                name = namesDogNeRandom[new Random().nextInt(namesDogNeRandom.length)];
                break;
            case WOLF:
                name = namesWolfNeRandom[new Random().nextInt(namesWolfNeRandom.length)];
                break;
        }
        return name;
    }

    /**
     * Метод - получает породу животного из списка
     */
    private String getRandomBreedByTypeAnimal(AnimalType animalType) {
        String breed = null;
        switch (animalType) {
            case DOG:
                breed = breedsDog[new Random().nextInt(breedsDog.length)];
                break;
            case WOLF:
                breed = breedsWolf[new Random().nextInt(breedsWolf.length)];
                break;
        }
        return breed;
    }

    /**
     * Метод - получает цену животного из списка
     */
    private BigDecimal getRandomPriceByTypeAnimal(AnimalType animalType) {
        String price = null;
        switch (animalType) {
            case DOG:
                price = pricesDog[new Random().nextInt(pricesDog.length)];
                break;
            case WOLF:
                price = pricesWolf[new Random().nextInt(pricesWolf.length)];
                break;
        }
        return new BigDecimal(price);
    }

    /**
     * Метод - получает характер животного из списка
     */
    private String getRandomCharacterByTypeAnimal(AnimalType animalType) {
        String character = null;
        switch (animalType) {
            case DOG:
                character = charactersDog[new Random().nextInt(charactersDog.length)];
                break;
            case WOLF:
                character = charactersWolf[new Random().nextInt(charactersWolf.length)];
                break;
        }
        return character;
    }

    /**
     * Метод - получает дату рождения животного из списка
     */
    private LocalDate getRandomDateByTypeAnimal(AnimalType animalType) {
        String date = null;
        switch (animalType) {
            case DOG:
                date = datesDog[new Random().nextInt(datesDog.length)];
                break;
            case WOLF:
                date = datesWolf[new Random().nextInt(datesWolf.length)];
                break;
        }
        return LocalDate.parse(date);
    }
}