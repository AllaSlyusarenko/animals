package ru.mts.hw_3.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.entity.Animal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        String content = getResourceFileAsString("results/findLeapYearNames.json");
        Pattern pattern = Pattern.compile("\\s{2}(\"[^\"]+\")\\s:\\s(\"\\d{4}-\\d{2}-\\d{2}\"),?");
        Matcher matcher = pattern.matcher(content);
        Map<String, LocalDate> resultMap = new HashMap<>();
        while (matcher.find()) {
            String name = matcher.group(1);
            String dateB = matcher.group(2);
            String typeName = mapper.readValue(name, String.class);
            LocalDate localDate = mapper.readValue(dateB, LocalDate.class);
            resultMap.put(typeName, localDate);
        }
        return resultMap;
    }

    @Override
    public Map<Animal, Integer> deserializationFindOlderAnimal() throws IOException {
        String content = getResourceFileAsString("results/findOlderAnimal.json");
        Map<Animal, Integer> dataFindOlderAnimal = new HashMap<>();
        TypeReference<HashMap<String, Integer>> typeRef = new TypeReference<>() {
        };
        Map<String, Integer> mapFindOlder = mapper.readValue(content, typeRef);
        for (String key : mapFindOlder.keySet()) {
            Animal abstractAnimal = mapper.readValue(key, Animal.class);
            dataFindOlderAnimal.put(abstractAnimal, mapFindOlder.get(key));
        }
        return dataFindOlderAnimal;
    }

    @Override
    public Map<String, List<Animal>> deserializationFindDuplicate() throws IOException {
        String content = getResourceFileAsString("results/findDuplicate.json");
        TypeReference<HashMap<String, List<Animal>>> typeRef = new TypeReference<>() {
        };
        HashMap<String, List<Animal>> mapFindDuplicate = mapper.readValue(content, typeRef);
        return mapFindDuplicate;
    }

    @Override
    public List<Animal> deserializationFindOldAndExpensive() throws IOException {
        String content = getResourceFileAsString("results/findOldAndExpensive.json");
        TypeReference<List<Animal>> typeRef = new TypeReference<>() {
        };
        List<Animal> listOldAndExpensive = mapper.readValue(content, typeRef);
        return listOldAndExpensive;
    }

    @Override
    public List<String> deserializationFindMinConstAnimals() throws IOException {
        String content = getResourceFileAsString("results/findMinConstAnimals.json");
        TypeReference<List<String>> typeRef = new TypeReference<>() {
        };
        List<String> listMinConstAnimals = mapper.readValue(content, typeRef);
        return listMinConstAnimals;
    }

    @Override
    public Double deserializationFindAverageAge() throws IOException {
        String content = getResourceFileAsString("results/findAverageAge.json");
        Double age = mapper.readValue(content, Double.class);
        return age;
    }

    protected String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    protected InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}