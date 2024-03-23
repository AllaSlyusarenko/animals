package ru.mts.hw_3.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.mts.entity.Animal;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.exception.IncorrectParameterException;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private ConcurrentHashMap<String, List<Animal>> animals;
    private final CreateAnimalService createAnimalService;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void init() {
        animals = new ConcurrentHashMap<>(createAnimalService.createAnimals());
    }

    /**
     * Метод - производит поиск имен животных, которые родились в високосный год
     */
    @Override
    public ConcurrentHashMap<String, LocalDate> findLeapYearNames() {
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        Map<String, LocalDate> animalsMap = prepareListAnimals().stream()
                .filter(animal -> isLeapYear(animal.getBirthDate()))
                .collect(Collectors.toMap(k -> k.getClass().getSimpleName().toUpperCase() + " " + k.getName(), v -> v.getBirthDate(), (k1, k2) -> k1));
        return new ConcurrentHashMap<>(animalsMap);
    }

    /**
     * Метод - производит поиск животных, которые старше указанного возраста, иначе выводит старшего
     */
    @Override
    public ConcurrentHashMap<Animal, Integer> findOlderAnimal(int N) {
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        if (N <= 0) {
            throw new IncorrectParameterException("The number of years must be greater than 0");
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
        return new ConcurrentHashMap<>(animalsMap);
    }

    /**
     * Метод - производит поиск дубликатов животных
     */
    @Override
    public ConcurrentHashMap<String, List<Animal>> findDuplicate() {
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        Set<Animal> elements = new HashSet<>();
        Map<String, List<Animal>> animalsMap = prepareListAnimals().stream()
                .filter(e -> !elements.add(e))
                .collect(Collectors.groupingBy(a -> a.getClass().getSimpleName().toUpperCase(), Collectors.toList()));
        return new ConcurrentHashMap<>(animalsMap);
    }

    /**
     * Метод - производит печать дубликатов животных
     */
    @Override
    public void printDuplicate() {
        ConcurrentHashMap<String, List<Animal>> map = findDuplicate();
        List<Animal> list = map.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            log.info("no duplicates found");
        } else {
            System.out.println(map);
        }
    }

    /**
     * Метод - для нахождения среднего возраста животных в списке
     */
    @Override
    public void findAverageAge(List<Animal> animalList) throws CollectionEmptyException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        double averageAge = animalList.stream()
                .mapToInt(animal -> countYears(animal.getBirthDate()))
                .filter(x -> x > 0)
                .average()
                .orElse(-1.0);
        log.info(Double.toString((averageAge * 100.0) / 100.0));
    }

    /**
     * Метод - для нахождения животных в списке старше 5 лет и стоимость больше средней стоимости,
     * отсортированный по дате рождения(по возрастанию)
     */
    @Override
    public CopyOnWriteArrayList<Animal> findOldAndExpensive(List<Animal> animalList) throws CollectionEmptyException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        BigDecimal averageCost = animalList.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(animalList.size()), RoundingMode.CEILING);
        return animalList.stream()
                .filter(x -> countYears(x.getBirthDate()) > 5)
                .filter(t -> t.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDate)).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    /**
     * Метод - для нахождения 3 животных с самой низкой ценой, вывод - список имен в обратном порядке
     */
    @Override
    public CopyOnWriteArrayList<String> findMinConstAnimals(List<Animal> animalList) throws CollectionEmptyException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        return animalList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    /**
     * Метод - для подготовки списка всех животных из мапы
     */
    public CopyOnWriteArrayList<Animal> prepareListAnimals() {
        return animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    private boolean isEmptyMap(Map<String, List<Animal>> animals) {
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