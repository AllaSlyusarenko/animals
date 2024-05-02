package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.entity.Creature;
import ru.mts.hw_3.repository.AnimalTypeRepository;
import ru.mts.hw_3.repository.CreatureRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class CreatureServiceImpl implements CreatureService {
    Random random = new Random();
    @Value("${creature.names}")
    private String[] namesCreatures;
    @Value("${creature.age}")
    private String[] ageCreatures;
    @Value("${animaltype}")
    private String[] animalTypes;
    @Value("${breed}")
    private String[] breeds;
    @Value("${isWild}")
    private String[] isWild;

    @Autowired
    private CreatureRepository creatureRepository;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;


    @Override
    public Map<String, List<Creature>> createCreatures(int N) {
        if (N <= 0) {
            System.out.print("The number of animals must be greater than 0");
            throw new IllegalArgumentException("The number of animals must be greater than 0");
        }
        Map<String, List<Creature>> creaturesMap = new HashMap<>();
        AnimalType animalType = createAnimalType();
        List<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Creature creature = new Creature();
            creature.setName(namesCreatures[random.nextInt(namesCreatures.length)]);
            creature.setAge(Short.parseShort(ageCreatures[random.nextInt(ageCreatures.length)]));
            creature.setCreated(LocalDate.now());
            creature.setUpdated(LocalDate.now());
            creature.setTypeId(animalType);
            creature.setIdBreed(createBreed());
            Creature creatureInBD = creatureRepository.create(creature);
            creatures.add(creature);
        }
        creaturesMap.put(animalType.getType(), creatures);
        return creaturesMap;
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
        animalTypeRepository.create(animalType);
        return animalType;
    }
}