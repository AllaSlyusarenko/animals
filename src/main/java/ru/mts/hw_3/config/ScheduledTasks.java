//package ru.mts.hw_3.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import ru.mts.hw_3.Main;
//import ru.mts.hw_3.repository.AnimalsRepository;
//import ru.mts.hw_3.repository.AnimalsRepositoryImpl;
//import ru.mtsbank.entity.Animal;
//
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Set;
//
//import static ru.mts.hw_3.Main.animalsRepository;
//
//@Component
//public class ScheduledTasks {
//    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
//
//    //@Scheduled(fixedRate = 60000)
//
////    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//
////    @Scheduled(fixedRate = 1000)
////    public void doTime() {
////        log.info("The time is now {}");
////    }
////    @Scheduled(fixedRate = 6000)
////    public void doRepositoryTasks() {
////        System.out.println("findLeapYearNames-------------------------------------------------------------------------------------");
////        System.out.println(Arrays.toString(animalsRepository.findLeapYearNames()) + "\n");
////
////        System.out.println("findOlderAnimal---------------------------------------------------------------------------------------");
////        int age = 15;
////        System.out.println(Arrays.toString(animalsRepository.findOlderAnimal(age)) + "\n");
////
////        System.out.println("findDuplicate-----------------------------------------------------------------------------------------");
////        Set<Animal> animalsDuplicate = animalsRepository.findDuplicate();
////        animalsRepository.printDuplicate();
////    }
//
//}