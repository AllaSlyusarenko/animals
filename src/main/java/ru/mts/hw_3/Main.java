package ru.mts.hw_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.mts.hw_3.repository.AnimalsRepository;
import ru.mtsbank.entity.Animal;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class, args);
        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);
        System.out.println("findLeapYearNames-------------------------------------------------------------------------------------");
        System.out.println(Arrays.toString(animalsRepository.findLeapYearNames()) + "\n");

        System.out.println("findOlderAnimal---------------------------------------------------------------------------------------");
        int age = 15;
        System.out.println(Arrays.toString(animalsRepository.findOlderAnimal(age)) + "\n");

        System.out.println("findDuplicate-----------------------------------------------------------------------------------------");
        Set<Animal> animalsDuplicate = animalsRepository.findDuplicate();
        animalsRepository.printDuplicate();
    }

//    @Autowired
//    private CreateAnimalService createAnimalService;
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