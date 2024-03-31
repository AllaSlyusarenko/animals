package ru.mts.hw_3.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.mts.entity.AbstractAnimal;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.exception.IncorrectParameterException;
import ru.mts.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private ConcurrentHashMap<String, List<AbstractAnimal>> animals;
    private final CreateAnimalService createAnimalService;
    private String firstPartOfPath = "src\\main\\resources\\results\\";
    @Qualifier("animalMapper")
    private final ObjectMapper mapper;

    @Autowired
    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService, ObjectMapper mapper) {
        this.createAnimalService = createAnimalService;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() throws IOException {
        animals = new ConcurrentHashMap<>(createAnimalService.createAnimals(30));
    }

    /**
     * Метод - производит поиск имен животных, которые родились в високосный год
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() throws IOException {
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        Map<String, LocalDate> animalsMap = prepareListAnimals().stream()
                .filter(animal -> isLeapYear(animal.getBirthDate()))
                .collect(Collectors.toMap(k -> k.getClass().getSimpleName().toUpperCase() + " " + k.getName(), v -> v.getBirthDate(), (k1, k2) -> k1));
        String jacksonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(animalsMap);
        Files.write(Paths.get(firstPartOfPath, "findLeapYearNames.txt"), jacksonData.getBytes(), StandardOpenOption.APPEND);
        return new ConcurrentHashMap<>(animalsMap);
    }

    /**
     * Метод - производит поиск животных, которые старше указанного возраста, иначе выводит старшего
     */
    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int N) throws IOException {
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        if (N <= 0) {
            throw new IncorrectParameterException("The number of years must be greater than 0");
        }
        Map<AbstractAnimal, Integer> animalsMap = prepareListAnimals().stream()
                .filter(animal -> animal.getBirthDate().isBefore(LocalDate.now().minusYears(N)))
                .collect(Collectors.toMap(k -> k, v -> countYears(v.getBirthDate()), (k1, k2) -> k1));
        if (animalsMap.isEmpty()) {
            AbstractAnimal oldestAnimal = animals.entrySet().stream()
                    .flatMap(entry -> entry.getValue().stream())
                    .min(Comparator.comparing(AbstractAnimal::getBirthDate))
                    .orElse(null);
            animalsMap.put(oldestAnimal, countYears(oldestAnimal.getBirthDate()));
        }

//        AbstractAnimal qwe = new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8));
//        AbstractAnimal qwe2 = new Dog("breed2", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8));
//        List<AbstractAnimal> ls = new ArrayList<>();
//        ls.add(qwe);
//        ls.add(qwe2);
//        Set<AbstractAnimal> st = new HashSet<>();
//        st.add(qwe);
//        st.add(qwe2);
//        Map<AbstractAnimal, Integer> an = new HashMap<>();
//        an.put(qwe, 1);
//        an.put(qwe2, 2);

        Map<String, Integer> result = new HashMap<>();
        for (AbstractAnimal key : animalsMap.keySet()) {
            String jacksonKey = mapper.writeValueAsString(key);
            result.put(jacksonKey, animalsMap.get(key));
        }
        String jacksonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result).replace("\\", "");
        Files.write(Paths.get(firstPartOfPath, "findOlderAnimal.txt"), jacksonData.getBytes(), StandardOpenOption.APPEND);
        return new ConcurrentHashMap<>(animalsMap);
    }

    /**
     * Метод - производит поиск дубликатов животных
     */
    @Override
    public Map<String, List<AbstractAnimal>> findDuplicate() {
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        Set<AbstractAnimal> elements = new CopyOnWriteArraySet<>();
        return prepareListAnimals().stream()
                .filter(e -> !elements.add(e))
                .collect(Collectors.groupingBy(a -> a.getClass().getSimpleName().toUpperCase(), Collectors.toList()));
    }

    /**
     * Метод - производит печать дубликатов животных
     */
    @Override
    public void printDuplicate() {
        Map<String, List<AbstractAnimal>> map = new ConcurrentHashMap<>(findDuplicate());
        List<AbstractAnimal> list = map.entrySet().stream()
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
    public void findAverageAge(List<AbstractAnimal> animalList) throws CollectionEmptyException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        double averageAge = animalList.stream()
                .mapToInt(animal -> countYears(animal.getBirthDate()))
                .filter(x -> x > 0)
                .average()
                .orElse(-1.0);
        log.info(Double.toString(Math.round(averageAge * 100.0) / 100.0));
    }

    /**
     * Метод - для нахождения животных в списке старше 5 лет и стоимость больше средней стоимости,
     * отсортированный по дате рождения(по возрастанию)
     */
    @Override
    public List<AbstractAnimal> findOldAndExpensive(List<AbstractAnimal> animalList) throws CollectionEmptyException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        BigDecimal averageCost = animalList.stream()
                .map(AbstractAnimal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(animalList.size()), RoundingMode.CEILING);
        return animalList.stream()
                .filter(x -> countYears(x.getBirthDate()) > 5)
                .filter(t -> t.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(AbstractAnimal::getBirthDate)).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    /**
     * Метод - для нахождения 3 животных с самой низкой ценой, вывод - список имен в обратном порядке
     */
    @Override
    public List<String> findMinConstAnimals(List<AbstractAnimal> animalList) throws CollectionEmptyException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        return animalList.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getCost))
                .limit(3)
                .map(AbstractAnimal::getName)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    /**
     * Метод - для подготовки списка всех животных из мапы
     */
    public List<AbstractAnimal> prepareListAnimals() {
        return animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    private boolean isEmptyMap(Map<String, List<AbstractAnimal>> animals) {
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