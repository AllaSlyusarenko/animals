package ru.mts.hw_3.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.hw_3.repository.AnimalsRepository;

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
        Map<String, Integer> animalsDuplicate = animalsRepository.findDuplicate();
        animalsRepository.printDuplicate();
    }
}