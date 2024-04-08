package ru.mts.hw_3.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import ru.mts.entity.AbstractAnimal;
import ru.mts.entity.AnimalType;
import ru.mts.entity.Dog;
import ru.mts.entity.Wolf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DeserializationServiceImpl implements DeserializationService {
    @Autowired
    private final ObjectMapper mapper;

    public DeserializationServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Map<String, LocalDate> deserializationFindLeapYearNames() throws IOException {
        File load = new ClassPathResource("results/findLeapYearNames.json", this.getClass().getClassLoader()).getFile();
        Map<String, LocalDate> dataFindLeapYearNames = new HashMap<>();
        try (FileReader fis = new FileReader(load);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindLeapYearNames = bfr.lines().collect(Collectors.toList());
            if (linesFindLeapYearNames.get(0).equals("{}")) {
                log.info("There are no animals born on leap years");
                return dataFindLeapYearNames;
            }
            if (!linesFindLeapYearNames.isEmpty()) {
                for (int i = 1; i < linesFindLeapYearNames.size() - 2; i++) {
                    String workLine = linesFindLeapYearNames.get(i);
                    int index = workLine.indexOf(":");
                    String key = workLine.substring(3, index - 2);
                    LocalDate value = LocalDate.parse(workLine.substring(index + 3, workLine.length() - 2));
                    dataFindLeapYearNames.put(key, value);
                }
            }
        } catch (IOException e) {
            log.error("No data, empty file - findLeapYearNames.json", e);
        }
        return dataFindLeapYearNames;
    }

    @Override
    public Map<AbstractAnimal, Integer> deserializationFindOlderAnimal() throws IOException {
        File load = new ClassPathResource("results/findOlderAnimal.json", this.getClass().getClassLoader()).getFile();
        Map<AbstractAnimal, Integer> dataFindOlderAnimal = new HashMap<>();
        try (FileReader fis = new FileReader(load);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindOlderAnimal = bfr.lines().collect(Collectors.toList());
            if (linesFindOlderAnimal.get(0).equals("{}")) {
                log.info("No animals older than a given age");
                return dataFindOlderAnimal;
            }
            if (!linesFindOlderAnimal.isEmpty()) {
                AnimalType animalType = AnimalType.valueOf(linesFindOlderAnimal.get(0).toUpperCase());
                for (int i = 2; i < linesFindOlderAnimal.size() - 1; i++) {
                    String workLine = linesFindOlderAnimal.get(i);
                    int indexStart = workLine.indexOf("{");
                    int indexEnd = workLine.indexOf("}");
                    String sub = "{ " + workLine.substring(indexStart + 1, indexEnd) + "}";
                    AbstractAnimal animal;
                    switch (animalType) {
                        case DOG:
                            animal = mapper.readValue(sub, Dog.class);
                            break;
                        case WOLF:
                            animal = mapper.readValue(sub, Wolf.class);
                            break;
                        default:
                            throw new RuntimeException("Unknown type of animal");
                    }
                    Integer value = Integer.parseInt(workLine.substring(indexEnd + 5, workLine.length() - 1));
                    dataFindOlderAnimal.put(animal, value);
                }
            }
        } catch (IOException e) {
            log.error("No data, empty file - findOlderAnimal.json", e);
        }
        return dataFindOlderAnimal;
    }

    @Override
    public Map<String, List<AbstractAnimal>> deserializationFindDuplicate() throws IOException {
        File load = new ClassPathResource("results/findDuplicate.json", this.getClass().getClassLoader()).getFile();
        Map<String, List<AbstractAnimal>> dataFindDuplicate = new HashMap<>();
        try (FileReader fis = new FileReader(load);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindDuplicate = bfr.lines().collect(Collectors.toList());
            if (linesFindDuplicate.get(0).equals("{}")) {
                log.info("No duplicates found");
                return dataFindDuplicate;
            }
            if (!linesFindDuplicate.isEmpty()) {
                int index = linesFindDuplicate.get(0).indexOf(":");
                AnimalType animalType = AnimalType.valueOf(linesFindDuplicate.get(0).substring(2, index - 1));
                int indexStart = linesFindDuplicate.get(0).indexOf("[");
                int indexEnd = linesFindDuplicate.get(0).indexOf("]");
                String workLine = linesFindDuplicate.get(0).substring(indexStart, indexEnd + 1);
                AbstractAnimal[] animals;
                switch (animalType) {
                    case DOG:
                        animals = mapper.readValue(workLine, Dog[].class);
                        break;
                    case WOLF:
                        animals = mapper.readValue(workLine, Wolf[].class);
                        break;
                    default:
                        throw new RuntimeException("Unknown type of animal");
                }
                dataFindDuplicate.put(animalType.toString(), Arrays.asList(animals));
            }
        } catch (IOException e) {
            log.error("No data, empty file - findDuplicate.json", e);
        }
        return dataFindDuplicate;
    }

    @Override
    public List<AbstractAnimal> deserializationFindOldAndExpensive() throws IOException {
        File load = new ClassPathResource("results/findOldAndExpensive.json", this.getClass().getClassLoader()).getFile();
        List<AbstractAnimal> dataFindOldAndExpensive = new ArrayList<>();
        try (FileReader fis = new FileReader(load);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindOldAndExpensive = bfr.lines().collect(Collectors.toList());
            if (linesFindOldAndExpensive.get(1).equals("[]")) {
                log.info("There are no animals that meet the specified parameters");
                return dataFindOldAndExpensive;
            }
            if (!linesFindOldAndExpensive.isEmpty()) {
                AnimalType animalType = AnimalType.valueOf(linesFindOldAndExpensive.get(0).toUpperCase());
                String workLine = linesFindOldAndExpensive.get(1);
                AbstractAnimal[] animals;
                switch (animalType) {
                    case DOG:
                        animals = mapper.readValue(workLine, Dog[].class);
                        break;
                    case WOLF:
                        animals = mapper.readValue(workLine, Wolf[].class);
                        break;
                    default:
                        throw new RuntimeException("Unknown type of animal");
                }
                dataFindOldAndExpensive.addAll(Arrays.asList(animals));
            }
        } catch (IOException e) {
            log.error("No data, empty file - findOldAndExpensive.json", e);
        }
        return dataFindOldAndExpensive;
    }

    @Override
    public List<String> deserializationFindMinConstAnimals() throws IOException {
        File load = new ClassPathResource("results/findMinConstAnimals.json", this.getClass().getClassLoader()).getFile();
        List<String> dataFindMinConstAnimals = new ArrayList<>();
        try (FileReader fis = new FileReader(load);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindMinConstAnimals = bfr.lines().collect(Collectors.toList());
            if (linesFindMinConstAnimals.get(0).equals("[]")) {
                log.info("There are no animals that meet the specified parameters");
                return dataFindMinConstAnimals;
            }
            if (!linesFindMinConstAnimals.isEmpty()) {
                String workLine = linesFindMinConstAnimals.get(0);
                String[] names = mapper.readValue(workLine, String[].class);
                dataFindMinConstAnimals.addAll(Arrays.asList(names));
            }
        } catch (IOException e) {
            log.error("No data, empty file - findMinConstAnimals.json", e);
        }
        return dataFindMinConstAnimals;
    }

    @Override
    public Double deserializationFindAverageAge() throws IOException {
        File load = new ClassPathResource("results/findAverageAge.json", this.getClass().getClassLoader()).getFile();
        Double age = null;
        try (FileReader fis = new FileReader(load);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindMinConstAnimals = bfr.lines().collect(Collectors.toList());
            if (linesFindMinConstAnimals.isEmpty()) {
                log.info("There are no animals that meet the specified parameters");
                return -1.0;
            }
            String workLine = linesFindMinConstAnimals.get(0);
            age = mapper.readValue(workLine, Double.class);
        } catch (IOException e) {
            log.error("No data, empty file - findMinConstAnimals.json", e);
        }
        return age;
    }
}