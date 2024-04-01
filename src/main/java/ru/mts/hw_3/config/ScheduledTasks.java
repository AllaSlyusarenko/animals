package ru.mts.hw_3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.entity.AbstractAnimal;
import ru.mts.entity.AnimalType;
import ru.mts.entity.Dog;
import ru.mts.entity.Wolf;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.exception.IncorrectParameterException;
import ru.mts.hw_3.repository.AnimalsRepositoryImpl;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class ScheduledTasks {
    @Value("${duplicate.time}")
    private String duplicateTime;
    @Value("${average.time}")
    private String averageAgeTime;
    private final AnimalsRepositoryImpl animalsRepository;
    @Qualifier("animalMapper")
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    public ScheduledTasks(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @PostConstruct
    public void startThreads() {
        Thread thread1 = new Thread(new PrintDuplicateRunnable(duplicateTime));
        thread1.setName("PrintDuplicateTread");
//        Thread thread2 = new Thread(new FindAverageAgeRunnable(averageAgeTime));
//        thread2.setName("FindAverageAgeTread");
        thread1.start();
//        thread2.start();
    }

    @Scheduled(fixedDelayString = "${application.scheduled.time}")
    public void doRepositoryTasks() {
        try {
            log.info("findLeapYearNames-------------------------------------------------------------------------------------");
            animalsRepository.findLeapYearNames();
            log.info(deserializationFindLeapYearNames() + "\n");

            log.info("findOlderAnimal---------------------------------------------------------------------------------------");
            int age = 15;
            animalsRepository.findOlderAnimal(age);
            log.info(deserializationFindOlderAnimal() + "\n");

//
//            List<AbstractAnimal> animalList = animalsRepository.prepareListAnimals();
//
//            log.info("findOldAndExpensive------------------------------------------------------------------------------------");
//            log.info(animalsRepository.findOldAndExpensive(animalList) + "\n");
//
//            log.info("findMinConstAnimals------------------------------------------------------------------------------------");
//            log.info(animalsRepository.findMinConstAnimals(animalList) + "\n");
        } catch (IncorrectParameterException ex) {
            log.error("Incorrect parameter value", ex);
//        } catch (CollectionEmptyException e) {
//            log.error("Data collection does not meet the required conditions", e);
        } catch (IOException e) {
            log.error("Required file is missing", e);
        }
    }

    class PrintDuplicateRunnable implements Runnable {
        private final String duplicateTime;

        public PrintDuplicateRunnable(String printDuplicateTime) {
            this.duplicateTime = printDuplicateTime;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    log.info("Name of printDuplicateThread = " + Thread.currentThread().getName());
                    log.info("findDuplicate-----------------------------------------------------------------------------------------");
                    animalsRepository.findDuplicate();
                    log.info(deserializationFindDuplicate() + "\n");
                    Thread.sleep(Long.parseLong(duplicateTime));
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class FindAverageAgeRunnable implements Runnable {
        private final String averageAgeTime;

        public FindAverageAgeRunnable(String averageAgeTime) {
            this.averageAgeTime = averageAgeTime;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    log.info("Name of findAverageAgeThread = " + Thread.currentThread().getName());
                    log.info("findAverageAge-----------------------------------------------------------------------------------------");
                    List<AbstractAnimal> animalList = animalsRepository.prepareListAnimals();
                    animalsRepository.findAverageAge(animalList);
                    Thread.sleep(Long.parseLong(averageAgeTime));
                } catch (InterruptedException | CollectionEmptyException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Map<String, LocalDate> deserializationFindLeapYearNames() {
        String fileName = "src\\main\\resources\\results\\findLeapYearNames.txt";
        Map<String, LocalDate> dataFindLeapYearNames = new HashMap<>();
        try (FileReader fis = new FileReader(fileName);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindOlderAnimal = bfr.lines().collect(Collectors.toList());
            if (!linesFindOlderAnimal.isEmpty()) {
                for (int i = 1; i < linesFindOlderAnimal.size() - 2; i++) {
                    String workLine = linesFindOlderAnimal.get(i);
                    int index = workLine.indexOf(":");
                    String key = workLine.substring(3, index - 2);
                    LocalDate value = LocalDate.parse(workLine.substring(index + 3, workLine.length() - 2));
                    dataFindLeapYearNames.put(key, value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataFindLeapYearNames;
    }

    private Map<AbstractAnimal, Integer> deserializationFindOlderAnimal() {
        String fileName = "src/main/resources/results/findOlderAnimal.txt";
        Map<AbstractAnimal, Integer> dataFindOlderAnimal = new HashMap<>();
        try (FileReader fis = new FileReader(fileName);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindOlderAnimal = bfr.lines().collect(Collectors.toList());
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
            throw new RuntimeException(e);
        }
        return dataFindOlderAnimal;
    }

    private Map<String, List<AbstractAnimal>> deserializationFindDuplicate() {
        String fileName = "src/main/resources/results/findDuplicate.txt";
        Map<String, List<AbstractAnimal>> dataFindDuplicate = new HashMap<>();
        try (FileReader fis = new FileReader(fileName);
             BufferedReader bfr = new BufferedReader(fis)) {
            List<String> linesFindOlderAnimal = bfr.lines().collect(Collectors.toList());
            if (linesFindOlderAnimal.get(0).equals("{}")) {
                log.info("no duplicates found");
                return dataFindDuplicate;
            }
            if (!linesFindOlderAnimal.isEmpty()) {
                int index = linesFindOlderAnimal.get(0).indexOf(":");
                AnimalType animalType = AnimalType.valueOf(linesFindOlderAnimal.get(0).substring(2, index - 1));
                int indexStart = linesFindOlderAnimal.get(0).indexOf("[");
                int indexEnd = linesFindOlderAnimal.get(0).indexOf("]");
                String workLine = linesFindOlderAnimal.get(0).substring(indexStart, indexEnd + 1);
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
            throw new RuntimeException(e);
        }
        return dataFindDuplicate;
    }
}
