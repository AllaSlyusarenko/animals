package ru.mts.hw_3.repository;

import ru.mts.entity.Animal;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
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
        return prepareListAnimals().stream()
                .filter(animal -> isLeapYear(animal.getBirthDate()))
                .collect(Collectors.toMap(k -> k.getClass().getSimpleName().toUpperCase() + " " + k.getName(), v -> v.getBirthDate()));
    }

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
        Map<Animal, Integer> animalsMap = prepareListAnimals().stream()
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
    public Map<String, List<Animal>> findDuplicate() {
        if (isEmptyArray(animals)) {
            return new HashMap<>();
        }
        return animals.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().stream()
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet().stream()
                        .filter(e -> e.getValue() > 1)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())));
    }

    /**
     * Метод - производит печать дубликатов животных
     */
    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> map = findDuplicate();
        System.out.println(map);
    }

    /**
     * Метод - для нахождения среднего возраста животных в списке
     */
    @Override
    public Double findAverageAge(List<Animal> animalList) {
        if (animalList == null || animalList.size() == 0) {
            return -1.0;
        }
        return animalList.stream()
                .mapToInt(animal -> countYears(animal.getBirthDate()))
                .filter(x -> x > 0)
                .average()
                .orElse(-1.0);
    }

    /**
     * Метод - для нахождения животных в списке старше 5 лет и стоимость больше средней стоимости,
     * отсортированный по дате рождения(по возрастанию)
     */
    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalList) {
        if (animalList == null || animalList.size() == 0) {
            return new ArrayList<>();
        }
        BigDecimal sum = animalList.stream()
                .map(Animal::getCost)
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageCost = sum.divide(new BigDecimal(animalList.size()), RoundingMode.CEILING);

        return animalList.stream()
                .filter(x -> countYears(x.getBirthDate()) > 5)
                .filter(t -> t.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }

    /**
     * Метод - для нахождения 3 животных с самой низкой ценой, вывод - список имен в обратном порядке
     */
    @Override
    public List<String> findMinConstAnimals(List<Animal> animalList) {
        if (animalList == null || animalList.size() == 0) {
            return new ArrayList<>();
        }
        List<String> names = animalList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(x -> x.getName())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return names;
    }

    /**
     * Метод - для подготовки списка всех животных из мапы
     */
    @Override
    public List<Animal> prepareListAnimals() {
        return animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
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