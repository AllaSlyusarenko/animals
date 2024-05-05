package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.repository.AnimalRepository;
import ru.mts.hw_3.repository.AnimalTypeRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class AnimalServiceImpl implements AnimalService {
    Random random = new Random();
    @Autowired
    private AnimalProperties animalProperties;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;

    @Override
    public Map<String, List<Animal>> createAnimals(int N) {
        if (N <= 0) {
            System.out.print("The number of animals must be greater than 0");
            throw new IllegalArgumentException("The number of animals must be greater than 0");
        }
        Map<String, List<Animal>> animalsMap = new HashMap<>();
        AnimalType animalType = createAnimalType();
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Animal animal = new Animal();
            animal.setName(animalProperties.getNames()[random.nextInt(animalProperties.getNames().length)]);
            animal.setAge(Integer.parseInt(animalProperties.getAge()[random.nextInt(animalProperties.getAge().length)]));
            animal.setCreated(LocalDate.now());
            animal.setUpdated(LocalDate.now());
            animal.setAnimalType(animalType);
            animal.setBreed(createBreed());
            animal.setCost(animalProperties.getCost()[random.nextInt(animalProperties.getCost().length)]);
            animal.setBirthDate(LocalDate.parse(animalProperties.getDates()[random.nextInt(animalProperties.getDates().length)]));
            Animal animalInBD = animalRepository.create(animal);
            animals.add(animal);
        }
        animalsMap.put(animalType.getType(), animals);
        return animalsMap;
    }

    private Breed createBreed() {
        Breed breed = new Breed();
        breed.setName(animalProperties.getBreed()[random.nextInt(animalProperties.getBreed().length)]);
        breed.setCreated(LocalDate.now());
        breed.setUpdated(LocalDate.now());
        return breed;
    }

    private AnimalType createAnimalType() {
        AnimalType animalType = new AnimalType();
        animalType.setType(animalProperties.getAnimalType()[random.nextInt(animalProperties.getAnimalType().length)]);
        animalType.setCreated(LocalDate.now());
        animalType.setUpdated(LocalDate.now());
        animalType.setWild(Boolean.valueOf(animalProperties.getIsWild()[random.nextInt(animalProperties.getIsWild().length)]));
        AnimalType animalTypeOut = animalTypeRepository.create(animalType);
        return animalTypeOut;
    }
}