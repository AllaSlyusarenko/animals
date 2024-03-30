package ru.mts.hw_3.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.entity.AbstractAnimal;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.exception.IncorrectParameterException;
import ru.mts.hw_3.repository.AnimalsRepositoryImpl;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ScheduledTasks {
    @Value("${duplicate.time}")
    private String duplicateTime;
    @Value("${average.time}")
    private String averageAgeTime;
    private final AnimalsRepositoryImpl animalsRepository;

    public ScheduledTasks(AnimalsRepositoryImpl animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @PostConstruct
    public void startThreads() {
        Thread thread1 = new Thread(new PrintDuplicateRunnable(duplicateTime));
        thread1.setName("PrintDuplicateTread");
        Thread thread2 = new Thread(new FindAverageAgeRunnable(averageAgeTime));
        thread2.setName("FindAverageAgeTread");
        thread1.start();
        thread2.start();
    }

    @Scheduled(fixedDelayString = "${application.scheduled.time}")
    public void doRepositoryTasks() {
        try {
            log.info("findLeapYearNames-------------------------------------------------------------------------------------");
            log.info(animalsRepository.findLeapYearNames() + "\n");

            log.info("findOlderAnimal---------------------------------------------------------------------------------------");
            int age = 15;
            log.info(animalsRepository.findOlderAnimal(age) + "\n");

            List<AbstractAnimal> animalList = animalsRepository.prepareListAnimals();

            log.info("findOldAndExpensive------------------------------------------------------------------------------------");
            log.info(animalsRepository.findOldAndExpensive(animalList) + "\n");

            log.info("findMinConstAnimals------------------------------------------------------------------------------------");
            log.info(animalsRepository.findMinConstAnimals(animalList) + "\n");
        } catch (IncorrectParameterException ex) {
            log.error("Incorrect parameter value", ex);
        } catch (CollectionEmptyException e) {
            log.error("Data collection does not meet the required conditions", e);
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
                } catch (InterruptedException e) {
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
                } catch (InterruptedException | CollectionEmptyException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}