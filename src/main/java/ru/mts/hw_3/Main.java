package ru.mts.hw_3;

import ru.mts.hw_3.service.CreateAnimalService;
import ru.mts.hw_3.service.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalService() {
        };
        createAnimalService.createAnimals();
        System.out.println("---------------------------------------------------------------------------------------");
        CreateAnimalService createAnimalServiceImpl1 = new CreateAnimalServiceImpl();
        createAnimalServiceImpl1.createAnimals();
        System.out.println("---------------------------------------------------------------------------------------");
        CreateAnimalServiceImpl createAnimalServiceImpl2 = new CreateAnimalServiceImpl();
        createAnimalServiceImpl2.createAnimals(15);
    }
}
