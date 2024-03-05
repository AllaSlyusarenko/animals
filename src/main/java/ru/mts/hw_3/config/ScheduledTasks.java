package ru.mts.hw_3.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.entity.Animal;
import ru.mts.hw_3.repository.AnimalsRepository;

import java.util.List;
import java.util.Map;

@Component
public class ScheduledTasks {
    private final AnimalsRepository animalsRepository;

    public ScheduledTasks(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedDelayString = "${application.scheduled.time}")
    public void doRepositoryTasks() {
        System.out.println("findLeapYearNames-------------------------------------------------------------------------------------");
        System.out.println(animalsRepository.findLeapYearNames() + "\n");

        System.out.println("findOlderAnimal---------------------------------------------------------------------------------------");
        int age = 15;
        System.out.println(animalsRepository.findOlderAnimal(age) + "\n");

        System.out.println("findDuplicate-----------------------------------------------------------------------------------------");
        Map<String, List<Animal>> animalsDuplicate = animalsRepository.findDuplicate();
        animalsRepository.printDuplicate();

        System.out.println("findAverageAge-----------------------------------------------------------------------------------------");
        List<Animal> animalList = animalsRepository.prepareListAnimals();
        System.out.println(Math.round(animalsRepository.findAverageAge(animalList) * 100.0) / 100.0 + "\n");

        System.out.println("findOldAndExpensive-----------------------------------------------------------------------------------------");
        System.out.println(animalsRepository.findOldAndExpensive(animalList) + "\n");

        System.out.println("findMinConstAnimals-----------------------------------------------------------------------------------------");
        System.out.println(animalsRepository.findMinConstAnimals(animalList) + "\n");
    }
}