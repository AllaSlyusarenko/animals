package ru.mts.hw_3.repository;

import ru.mts.entity.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalsRepositoryImpl implements AnimalsRepository {
    private Map<String, List<Animal>> animals;
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
    public Map<String, LocalDate> findLeapYearNames() {
        if (isEmptyArray(animals)) {
            return new HashMap<>();
        }
        Map<String, LocalDate> animalsMap = new HashMap<>();
        for (String key : animals.keySet()) {
//            if (key == null) {
//                continue;
//            }
            for (Animal animal : animals.get(key)) {
                if (isLeapYear(animal.getBirthDate())) {
                    animalsMap.put("" + key + " " + animal.getName(), animal.getBirthDate());
                }
            }
        }
        return animalsMap;
    }

    /**
     * Метод - производит поиск животных, которые старше указанного возраста, и формирует из них массив
     */
    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) {
        if (isEmptyArray(animals)) {
            return new HashMap<>();
        }
        if (N <= 0) {
            System.out.println("The number of years must be greater than 0");
            return new HashMap<>();
        }
        Map<Animal, Integer> animalsMap = new HashMap<>();
        for (String key : animals.keySet()) {
//            if (key == null) {
//                continue;
//            }
            for (Animal animal : animals.get(key)) {
                if (animal.getBirthDate().isBefore(LocalDate.now().minusYears(N))) {
                    animalsMap.put(animal, countYears(animal.getBirthDate()));
                }
            }
        }
        return animalsMap;
    }

    /**
     * Метод - производит поиск дубликатов животных и формирует из них массив
     */
    @Override
    public Map<String, Integer> findDuplicate() {
        if (isEmptyArray(animals) || animals.size() == 1) {
            return new HashMap<>();
        }
        Map<String, Integer> animalsMapFinal = new HashMap<>();
//        Set<Animal> animalsSet = new HashSet<>();
//        for (int i = 0; i < animals.size(); i++) {
//            if (animals[i] == null) {
//                continue;
//            }
        for (String key : animals.keySet()) {
            Map<Animal, Integer> animalsMap = new HashMap<>(); //промежуточная, чтобы собрать все дубликаты и не запутаться, что учтено, а что нет
            List<Animal> animalList = animals.get(key);
            for (int i = 0; i < animalList.size(); i++) {
                if (animalsMap.containsKey(animalList.get(i))) {
                    continue;
                }
                for (int j = i + 1; j < animalList.size(); j++) {
//                    if (animals[j] == null) {
//                        continue;
//                    }
                    if (animalList.get(i).equals(animalList.get(j))) {
                        if (!animalsMap.containsKey(animalList.get(i))) {
                            animalsMap.put(animalList.get(i), 2);
                        } else {
                            animalsMap.put(animalList.get(i), animalsMap.get(animalList.get(i)) + 1);
                        }
                    }
                }
            }
            int total = 0;
            for (Integer count : animalsMap.values()) {
                total += count;
            }
            animalsMapFinal.put(key, total);
        }
        return animalsMapFinal;
    }

    /**
     * Метод - производит печать массива дубликатов животных
     */
    @Override
    public void printDuplicate() {
        Map<String, Integer> map = findDuplicate();
        System.out.println(map);
    }

    private boolean isEmptyArray(Map<String, List<Animal>> animals) {
        return animals == null || animals.size() == 0;
    }

    private boolean isLeapYear(LocalDate localDate) {
        return (localDate.getYear() % 4 == 0 && localDate.getYear() % 100 != 0) ||
                (localDate.getYear() % 400 == 0);
    }

    private Integer countYears(LocalDate localDate) {
        return Period.between(localDate, LocalDate.now()).getYears();
    }
}