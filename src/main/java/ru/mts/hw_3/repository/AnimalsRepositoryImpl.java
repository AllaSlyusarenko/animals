package ru.mts.hw_3.repository;

import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnimalsRepositoryImpl implements AnimalsRepository {
    private Animal[] animals;
    private final CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void init() {
        animals = createAnimalService.createAnimals();
    }

    /**
     * Метод - производит поиск животных, которые родились в високосный год, и формирует массив их имён
     */
    @Override
    public String[] findLeapYearNames() {
        if (isEmptyArray(animals)) {
            return new String[0];
        }
        List<String> namesList = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal == null) {
                continue;
            }
            if (isLeapYear(animal.getBirthDate())) {
                namesList.add(animal.getName());
            }
        }
        String[] names = new String[namesList.size()];
        return namesList.toArray(names);
    }

    /**
     * Метод - производит поиск животных, которые старше указанного возраста, и формирует из них массив
     */
    @Override
    public Animal[] findOlderAnimal(int N) {
        if (isEmptyArray(animals)) {
            return new Animal[0];
        }
        if (N <= 0) {
            System.out.println("The number of years must be greater than 0");
            return new Animal[0];
        }
        List<Animal> animalsList = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal == null) {
                continue;
            }
            if (animal.getBirthDate().isBefore(LocalDate.now().minusYears(N))) {
                animalsList.add(animal);
            }
        }
        Animal[] animalsOut = new Animal[animalsList.size()];
        return animalsList.toArray(animalsOut);
    }

    /**
     * Метод - производит поиск дубликатов животных и формирует из них массив
     */
    @Override
    public Set<Animal> findDuplicate() {
        if (isEmptyArray(animals) || animals.length == 1) {
            return new HashSet<Animal>();
        }
        Set<Animal> animalsSet = new HashSet<>();
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] == null) {
                continue;
            }
            for (int j = i + 1; j < animals.length; j++) {
                if (animals[j] == null) {
                    continue;
                }
                if (animals[i].equals(animals[j])) {
                    animalsSet.add(animals[i]);
                }
            }
        }
        return animalsSet;
    }

    /**
     * Метод - производит печать массива дубликатов животных
     */
    @Override
    public void printDuplicate() {
        Set<Animal> set = findDuplicate();
        for (Animal animal : set) {
            System.out.println(animal);
        }
    }

    private boolean isEmptyArray(Animal[] animals) {
        return animals == null || animals.length == 0;
    }

    private boolean isLeapYear(LocalDate localDate) {
        return (localDate.getYear() % 4 == 0 && localDate.getYear() % 100 != 0) ||
                (localDate.getYear() % 400 == 0);
    }
}