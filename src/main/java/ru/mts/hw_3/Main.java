package ru.mts.hw_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.mts.hw_3.repository.AnimalsRepository;
import ru.mtsbank.entity.Animal;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class Main {
    public static AnimalsRepository animalsRepository;
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        animalsRepository = context.getBean(AnimalsRepository.class);
    }
    @Scheduled(initialDelay = 3000, fixedRate = 6000)
    public void doRepositoryTasks() {
        System.out.println("findLeapYearNames-------------------------------------------------------------------------------------");
        System.out.println(Arrays.toString(animalsRepository.findLeapYearNames()) + "\n");

        System.out.println("findOlderAnimal---------------------------------------------------------------------------------------");
        int age = 15;
        System.out.println(Arrays.toString(animalsRepository.findOlderAnimal(age)) + "\n");

        System.out.println("findDuplicate-----------------------------------------------------------------------------------------");
        Set<Animal> animalsDuplicate = animalsRepository.findDuplicate();
        animalsRepository.printDuplicate();
    }
}