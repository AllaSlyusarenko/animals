package ru.mts.hw_3;

import ru.mts.hw_3.entity.AnimalFactory;
import ru.mts.hw_3.service.CreateAnimalService;
import ru.mts.hw_3.service.CreateAnimalServiceImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalService() {
        };
        System.out.println(Arrays.toString(createAnimalService.createAnimals()));
        System.out.println("---------------------------------------------------------------------------------------");

        AnimalFactory animalFactory = new AnimalFactory();
        CreateAnimalServiceImpl service = new CreateAnimalServiceImpl(animalFactory);
        System.out.println(Arrays.toString(service.createAnimals()));
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(Arrays.toString(service.createAnimals(15)));
        System.out.println(Arrays.toString(service.createAnimals(-5)));
//        service.createAnimals(15);
//        service.createAnimals(-5);
    }
}