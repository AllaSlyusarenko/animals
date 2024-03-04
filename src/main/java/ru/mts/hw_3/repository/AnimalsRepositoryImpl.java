package ru.mts.hw_3.repository;

import ru.mts.entity.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     * Метод - производит поиск имен животных, которые родились в високосный год
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        if (isEmptyArray(animals)) {
            return new HashMap<>();
        }
        return animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .filter(animal -> isLeapYear(animal.getBirthDate()))
                .collect(Collectors.toMap(k -> k.getClass().getSimpleName().toUpperCase() + " " + k.getName(), v -> v.getBirthDate()));

//        return animals.entrySet().stream()
//                .flatMap(entry -> mapToList(entry.getKey(), entry.getValue()).stream())
//                .collect(Collectors.toMap(entry -> entry.getKey() + " " + entry.getValue().getName(), entry -> entry.getValue().getBirthDate()));

//        namesDateInString1;
    }

//    private List<AbstractMap.SimpleEntry<String, Animal>> mapToList(String key, List<Animal> animalList) {
//        return animalList.stream()
//                .filter(animal -> isLeapYear(animal.getBirthDate()))
//                .map(animal -> new AbstractMap.SimpleEntry<String, Animal>(key, animal))
//                .collect(Collectors.toList());
//
//    }

    /**
     * Метод - производит поиск животных, которые старше указанного возраста, иначе выводит старшего
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
        Map<Animal, Integer> animalsMap = animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .filter(animal -> animal.getBirthDate().isBefore(LocalDate.now().minusYears(N)))
                .collect(Collectors.toMap(k -> k, v -> countYears(v.getBirthDate()), (k1, k2) -> k1));
        if (animalsMap.isEmpty()) {
            Animal oldestAnimal = animals.entrySet().stream()
                    .flatMap(entry -> entry.getValue().stream())
                    .min(Comparator.comparing(Animal::getBirthDate))
                    .orElse(null);
            animalsMap.put(oldestAnimal, countYears(oldestAnimal.getBirthDate()));
        }
        return animalsMap;
    }

    /**
     * Метод - производит поиск дубликатов животных
     */
    @Override
    public Map<String, Integer> findDuplicate() {
        if (isEmptyArray(animals)) {
            return new HashMap<>();
        }
        Map<String, Integer> animalsMapFinal = new HashMap<>();
        for (String key : animals.keySet()) {
            Map<Animal, Integer> animalsMap = new HashMap<>(); //промежуточная, чтобы собрать все дубликаты
            // и не запутаться, что учтено, а что нет, и не допустить двойного учета одного и того же элемента
            List<Animal> animalList = animals.get(key);
            for (int i = 0; i < animalList.size(); i++) {
                if (animalsMap.containsKey(animalList.get(i))) {
                    continue;
                }
                for (int j = i + 1; j < animalList.size(); j++) {
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
            if (total != 0) {
                animalsMapFinal.put(key, total);
            }
        }
        return animalsMapFinal;
    }

    /**
     * Метод - производит печать дубликатов животных
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