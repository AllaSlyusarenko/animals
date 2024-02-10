package ru.mts.hw_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.hw_3.config.AppConfig;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.AnimalsRepository;
import ru.mts.hw_3.repository.AnimalsRepositoryImpl;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        AnimalsRepository animalsRepository = context.getBean(AnimalsRepositoryImpl.class);
//
//        System.out.println("findLeapYearNames-------------------------------------------------------------------------------------");
//        System.out.println(Arrays.toString(animalsRepository.findLeapYearNames()) + "\n");
//
//        System.out.println("findOlderAnimal---------------------------------------------------------------------------------------");
//        int age = 15;
//        System.out.println(Arrays.toString(animalsRepository.findOlderAnimal(age)) + "\n");
//
//        System.out.println("findDuplicate-----------------------------------------------------------------------------------------");
//        Set<Animal> animalsDuplicate = animalsRepository.findDuplicate();
//        animalsRepository.printDuplicate();
//    }
}