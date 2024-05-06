package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.config.AnimalProperties;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.exception.CollectionEmptyException;
import ru.mts.hw_3.repository.AnimalRepositoryBD;
import ru.mts.hw_3.repository.AnimalTypeRepository;
import ru.mts.hw_3.repository.AnimalsRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class AnimalServiceImpl implements AnimalService {
    Random random = new Random();
    @Autowired
    private AnimalProperties animalProperties;
    @Autowired
    private AnimalRepositoryBD animalRepositoryBD;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    @Autowired
    private AnimalsRepository animalsRepository;
    @Autowired
    DeserializationService deserializationService;


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
            animal.setCost(animalProperties.getCost()[random.nextInt(animalProperties.getCost().length)]);
            animal.setBirthDate(LocalDate.parse(animalProperties.getDates()[random.nextInt(animalProperties.getDates().length)]));
            Animal animalInBD = animalRepositoryBD.create(animal);
            animals.add(animal);
        }
        animalsMap.put(animalType.getType(), animals);
        return animalsMap;
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() throws IOException {
        animalsRepository.findLeapYearNames();
        return deserializationService.deserializationFindLeapYearNames();
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) throws IOException {
        animalsRepository.findOlderAnimal(N);
        return deserializationService.deserializationFindOlderAnimal();
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() throws IOException {
        animalsRepository.findDuplicate();
        return deserializationService.deserializationFindDuplicate();
    }

    @Override
    public List<Animal> prepareListAnimals() {
        return animalsRepository.prepareListAnimals();
    }

    @Override
    public Double findAverageAge(List<Animal> animalList) throws CollectionEmptyException, IOException {
        animalsRepository.findAverageAge(animalList);
        return deserializationService.deserializationFindAverageAge();
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalList) throws CollectionEmptyException, IOException {
        animalsRepository.findOldAndExpensive(animalList);
        return deserializationService.deserializationFindOldAndExpensive();
    }

    @Override
    public List<String> findMinConstAnimals(List<Animal> animalList) throws CollectionEmptyException, IOException {
        animalsRepository.findMinConstAnimals(animalList);
        return deserializationService.deserializationFindMinConstAnimals();
    }

    private AnimalType createAnimalType() {
        AnimalType animalType = new AnimalType();
        animalType.setType(animalProperties.getAnimalType()[random.nextInt(animalProperties.getAnimalType().length)]);
        animalType.setCreated(LocalDate.now());
        animalType.setUpdated(LocalDate.now());
        animalType.setIsWild(Boolean.valueOf(animalProperties.getIsWild()[random.nextInt(animalProperties.getIsWild().length)]));
        AnimalType animalTypeOut = animalTypeRepository.create(animalType);
        return animalTypeOut;
    }
}