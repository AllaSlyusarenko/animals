package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchServiceImpl implements SearchService {
    /**
     * Метод - производит поиск животных, которые родились в високосный год, и формирует массив их имён
     */
    @Override
    public String[] findLeapYearNames(Animal[] animals) {
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
    public Animal[] findOlderAnimal(Animal[] animalsIn, int N) {
        if (isEmptyArray(animalsIn)) {
            return animalsIn;
        }
        if (N <= 0) {
            System.out.print("Количество лет должно быть больше 0 - ");
            return new Animal[0];
        }
        List<Animal> animalsList = new ArrayList<>();
        for (Animal animal : animalsIn) {
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
    public Animal[] findDuplicate(Animal[] animalsIn) {
        if (isEmptyArray(animalsIn) || animalsIn.length == 1) {
            return new Animal[0];
        }
        Set<Animal> animalsList = new HashSet<>();
        for (int i = 0; i < animalsIn.length; i++) {
            if (animalsIn[i] == null) {
                continue;
            }
            for (int j = i + 1; j < animalsIn.length; j++) {
                if (animalsIn[j] == null) {
                    continue;
                }
                if (animalsIn[i].equals(animalsIn[j])) {
                    animalsList.add(animalsIn[i]);
                }
            }
        }
        Animal[] animalsOut = new Animal[animalsList.size()];
        return animalsList.toArray(animalsOut);
    }

    private boolean isEmptyArray(Animal[] animals) {
        return animals == null || animals.length == 0;
    }

    private boolean isLeapYear(LocalDate localDate) {
        return (localDate.getYear() % 4 == 0 && localDate.getYear() % 100 != 0) ||
                (localDate.getYear() % 400 == 0);
    }
}