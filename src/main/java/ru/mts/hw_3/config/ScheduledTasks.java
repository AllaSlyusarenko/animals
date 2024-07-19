//package ru.mts.hw_3.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import ru.mts.hw_3.entity.Animal;
//import ru.mts.hw_3.entity.AnimalType;
//import ru.mts.hw_3.entity.Breed;
//import ru.mts.hw_3.service.AnimalService;
//import ru.mts.hw_3.service.AnimalTypeService;
//import ru.mts.hw_3.service.BreedService;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//@Slf4j
//@Component
//public class ScheduledTasks {
//
//    @Autowired
//    private AnimalService animalService;
//    @Autowired
//    private BreedService breedService;
//    @Autowired
//    private AnimalTypeService animalTypeService;
//
//    @Scheduled(fixedDelayString = "${application.scheduled.time}")
//    public void doRepositoryTasks() {
//        log.info(animalService.getAllAnimals().toString() + "\n");
//
//        Breed breedIn = new Breed();
//        breedIn.setName("breed name");
//        Breed breedOut = breedService.saveBreed(breedIn);
//
//        AnimalType animalTypeIn = new AnimalType();
//        animalTypeIn.setType("type");
//        animalTypeIn.setIsWild(true);
//        AnimalType animalTypeOut = animalTypeService.saveAnimalType(animalTypeIn);
//
//        Animal animalIn = new Animal();
//        animalIn.setName("animal name");
//        animalIn.setAnimalType(animalTypeOut);
//        animalIn.setAge(15);
//        animalIn.setBreed(breedOut);
//        animalIn.setCost(BigDecimal.valueOf(1500));
//        animalIn.setBirthDate(LocalDate.of(1987, 5, 18));
//        Animal animalOut = animalService.saveAnimal(animalIn);
//        log.info(animalOut.toString() + "\n");
//
//        Integer id = animalOut.getIdAnimal();
//        animalService.deleteAnimal(id);
//    }
//}