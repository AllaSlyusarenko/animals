package ru.mts.hw_3.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.exception.IncorrectParameterException;
import ru.mts.hw_3.util.HibernateUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @Autowired
    private ObjectMapper mapper;

    /**
     * Метод - формирует мапу животных из бд
     */
    public ConcurrentHashMap<String, List<Animal>> getAnimalsMap() {
        ConcurrentHashMap<String, List<Animal>> animalsMap = new ConcurrentHashMap<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Animal> animals = new ArrayList<>();
        try {
            animals = session.createQuery("from Animal", Animal.class).list();
            for (Animal animal : animals) {
                if (!animalsMap.containsKey(animal.getAnimalType().toString())) {
                    animalsMap.put(animal.getAnimalType().toString(), new ArrayList<>());
                }
                animalsMap.get(animal.getAnimalType().getType()).add(animal);
            }
        } catch (Exception e) {
            log.error("Exception bd", e);
        } finally {
            session.close();
        }
        return animalsMap;
    }

    /**
     * Метод - производит поиск имен животных, которые родились в високосный год
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() throws IOException {
        Map<String, List<Animal>> animals = getAnimalsMap();
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        Map<String, LocalDate> animalsMap = prepareListAnimals().stream()
                .filter(animal -> isLeapYear(animal.getBirthDate()))
                .collect(Collectors.toMap(k -> k.getClass().getSimpleName().toUpperCase() + " " + k.getName(), v -> v.getBirthDate(), (k1, k2) -> k1));
        String jacksonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(animalsMap);
        Path path = new ClassPathResource("results/findLeapYearNames.json", this.getClass().getClassLoader()).getFile().toPath();
        Files.write(path, jacksonData.getBytes());
        return new ConcurrentHashMap<>(animalsMap);
    }

    /**
     * Метод - производит поиск животных, которые старше указанного возраста, иначе выводит старшего
     */
    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) throws IOException {
        Map<String, List<Animal>> animals = getAnimalsMap();
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
        Map<String, Integer> result = new HashMap<>();
        for (Animal key : animalsMap.keySet()) {
            String jacksonKey = mapper.writeValueAsString(key);
            result.put(jacksonKey, animalsMap.get(key));
        }
        String jacksonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        Path path = new ClassPathResource("results/findOlderAnimal.json", this.getClass().getClassLoader()).getFile().toPath();
        Files.write(path, jacksonData.getBytes());
        return new ConcurrentHashMap<>(animalsMap);
    }

    /**
     * Метод - производит поиск дубликатов животных
     */
    @Override
    public Map<String, List<Animal>> findDuplicate() throws IOException {
        Map<String, List<Animal>> animals = getAnimalsMap();
        if (isEmptyMap(animals)) {
            throw new IllegalArgumentException("data collection cannot be empty");
        }
        Set<Animal> elements = new CopyOnWriteArraySet<>();
        Map<String, List<Animal>> animalsMap = prepareListAnimals().stream()
                .filter(e -> !elements.add(e))
                .collect(Collectors.groupingBy(a -> a.getClass().getSimpleName().toUpperCase(), Collectors.toList()));
        String jacksonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(animalsMap);
        Path path = new ClassPathResource("results/findDuplicate.json", this.getClass().getClassLoader()).getFile().toPath();
        Files.write(path, jacksonData.getBytes());
        return animalsMap;
    }

    /**
     * Метод - производит печать дубликатов животных
     */
    @Override
    public void printDuplicate() throws IOException {
        Map<String, List<Animal>> map = findDuplicate();
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
     *
     * @return
     */
    @Override
    public Double findAverageAge(List<Animal> animalList) throws CollectionEmptyException, IOException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        double averageAge = animalList.stream()
                .mapToInt(animal -> countYears(animal.getBirthDate()))
                .filter(x -> x > 0)
                .average()
                .orElse(-1.0);
        String averageAgeString = Double.toString(Math.round(averageAge * 100.0) / 100.0);
        Double averageAgeRounding = Double.parseDouble(averageAgeString);
        String jacksonData = mapper.writeValueAsString(averageAgeRounding);
        Path path = new ClassPathResource("results/findAverageAge.json", this.getClass().getClassLoader()).getFile().toPath();
        Files.write(path, jacksonData.getBytes());
        return averageAgeRounding;
    }

    /**
     * Метод - для нахождения животных в списке старше 5 лет и стоимость больше средней стоимости,
     * отсортированный по дате рождения(по возрастанию)
     */
    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalList) throws CollectionEmptyException, IOException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        BigDecimal averageCost = animalList.stream()
                .map(Animal::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(new BigDecimal(animalList.size()), RoundingMode.CEILING);
        List<Animal> result = animalList.stream()
                .filter(x -> countYears(x.getBirthDate()) > 5)
                .filter(t -> t.getCost().compareTo(averageCost) > 0)
                .sorted(Comparator.comparing(Animal::getBirthDate)).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        String jacksonData = mapper.writeValueAsString(result);
        Path path = new ClassPathResource("results/findOldAndExpensive.json", this.getClass().getClassLoader()).getFile().toPath();
        Files.write(path, jacksonData.getBytes());
        return result;
    }

    /**
     * Метод - для нахождения 3 животных с самой низкой ценой, вывод - список имен в обратном порядке
     */
    @Override
    public List<String> findMinConstAnimals(List<Animal> animalList) throws CollectionEmptyException, IOException {
        if (animalList == null || animalList.size() == 0) {
            throw new CollectionEmptyException("data collection cannot be empty");
        }
        List<String> result = animalList.stream()
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        String jacksonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        Path path = new ClassPathResource("results/findMinConstAnimals.json", this.getClass().getClassLoader()).getFile().toPath();
        Files.write(path, jacksonData.getBytes());
        return result;
    }

    /**
     * Метод - для подготовки списка всех животных из мапы
     */
    public List<Animal> prepareListAnimals() {
        ConcurrentHashMap<String, List<Animal>> animals = getAnimalsMap();
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