package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.entity.Animal;
//import ru.mts.hw_3.repository.AnimalRepository;
import ru.mts.hw_3.repository.AnimalRepository;
//import ru.mts.hw_3.repository.AnimalTypeRepository;

import java.util.List;
import java.util.Random;

@Service
public class AnimalServiceImpl implements AnimalService {
    Random random = new Random();

//    @Autowired
//    private AnimalTypeRepository animalTypeRepository;

    private final AnimalRepository animalsRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalsRepository.findAll();
    }
}