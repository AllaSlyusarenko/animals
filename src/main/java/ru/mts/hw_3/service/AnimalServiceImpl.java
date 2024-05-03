package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.AnimalTypeRepository;
import ru.mts.hw_3.repository.AnimalRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class AnimalServiceImpl implements AnimalService {
    Random random = new Random();
    @Value("${animal.names}")
    private String[] namesAnimals;
    @Value("${animal.age}")
    private String[] ageAnimals;
    @Value("${animaltype}")
    private String[] animalTypes;
    @Value("${breed}")
    private String[] breeds;
    @Value("${isWild}")
    private String[] isWild;

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
            animal.setName(namesAnimals[random.nextInt(namesAnimals.length)]);
            animal.setAge(Short.parseShort(ageAnimals[random.nextInt(ageAnimals.length)]));
            animal.setCreated(LocalDate.now());
            animal.setUpdated(LocalDate.now());
            animal.setTypeId(animalType);
            animal.setIdBreed(createBreed());
            Animal animalInBD = animalRepository.create(animal);
            animals.add(animal);
        }
        animalsMap.put(animalType.getType(), animals);
        return animalsMap;
    }

    private Breed createBreed() {
        Breed breed = new Breed();
        breed.setName(breeds[random.nextInt(breeds.length)]);
        breed.setCreated(LocalDate.now());
        breed.setUpdated(LocalDate.now());
        return breed;
    }

    private AnimalType createAnimalType() {
        AnimalType animalType = new AnimalType();
        animalType.setType(animalTypes[random.nextInt(animalTypes.length)]);
        animalType.setCreated(LocalDate.now());
        animalType.setUpdated(LocalDate.now());
        animalType.setWild(Boolean.valueOf(isWild[random.nextInt(isWild.length)]));
        AnimalType animalTypeOut = animalTypeRepository.create(animalType);
        return animalTypeOut;
    }
}