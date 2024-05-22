package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.annotations.Logging;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.repository.AnimalTypeRepository;

import java.util.List;

@Service
public class AnimalTypeServiceImpl implements AnimalTypeService {

    private final AnimalTypeRepository animalTypeRepository;

    @Autowired
    public AnimalTypeServiceImpl(AnimalTypeRepository animalTypeRepository) {
        this.animalTypeRepository = animalTypeRepository;
    }

    @Override
    @Logging(entering = true, exiting = true)
    public List<AnimalType> getAllAnimalTypes() {
        return animalTypeRepository.findAll();
    }

    @Override
    @Logging(value = "save AnimalType", entering = true, exiting = true, level = "warn", logArgs = true, logResult = true)
    public AnimalType saveAnimalType(AnimalType animalType) {
        return animalTypeRepository.save(animalType);
    }
}
