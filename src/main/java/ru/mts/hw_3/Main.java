package ru.mts.hw_3;

import ru.mts.hw_3.service.CreateAnimalService;
import ru.mts.hw_3.service.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService createAnimalService = new CreateAnimalService() {
        };
        createAnimalService.createAnimals();
        System.out.println("---------------------------------------------------------------------------------------");
        CreateAnimalServiceImpl service = new CreateAnimalServiceImpl();
        service.createAnimals();
        System.out.println("---------------------------------------------------------------------------------------");
        service.createAnimals(15);
        service.createAnimals(-5);
    }
}