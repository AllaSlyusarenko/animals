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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

//    @PostConstruct
//    public void startThreads() {
//        Thread thread1 = new Thread(new PrintDuplicateRunnable(duplicateTime));
//        thread1.setName("PrintDuplicateTread");
//        Thread thread2 = new Thread(new FindAverageAgeRunnable(averageAgeTime));
//        thread2.setName("FindAverageAgeTread");
//        thread1.start();
//        thread2.start();
//    }

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
                    animalsRepository.printDuplicate();
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

    private String deserializationFindLeapYearNames() {
        String fileName = "src\\main\\resources\\results\\findLeapYearNames.txt";
        StringBuilder dataFindLeapYearNames = new StringBuilder();
        try (FileReader fis = new FileReader(fileName);
             BufferedReader bfr = new BufferedReader(fis)) {
            String line;
            while ((line = bfr.readLine()) != null) {
                dataFindLeapYearNames.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = dataFindLeapYearNames.toString().replace("\"", "").replace(":", "=")
                .replace(",  ", ", ");
        return result;
    }

    private List<String> deserializationFindOlderAnimal() {
        String fileName = "src/main/resources/results/findOlderAnimal.txt";
        List<String> dataFindOlderAnimal = new ArrayList<>();
        try (FileReader fis = new FileReader(fileName);
             BufferedReader bfr = new BufferedReader(fis)) {
            String line;
            List<String> linesFindOlderAnimal = bfr.lines().collect(Collectors.toList());
            if (!linesFindOlderAnimal.isEmpty()) {
                AnimalType animalType = AnimalType.valueOf(linesFindOlderAnimal.get(0).toUpperCase());
                for (int i = 2; i < linesFindOlderAnimal.size() - 2; i++) {
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
                    String stringResult = animal + "=" + workLine.substring(indexEnd + 5, workLine.length() - 1);
                    dataFindOlderAnimal.add(stringResult);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataFindOlderAnimal;
    }
}
