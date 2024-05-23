package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.annotations.Logging;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.AnimalRepository;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalsRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Override
    @Logging(value = "get All Animals", entering = true, exiting = true)
    public List<Animal> getAllAnimals() {
        return animalsRepository.findAll();
    }

    @Override
    @Logging(value = "save Animal", entering = true, exiting = true, logArgs = true, logResult = true)
    public Animal saveAnimal(Animal animal) {
        return animalsRepository.save(animal);
    }

    @Override
    @Logging(entering = true, logArgs = true)
    public void deleteAnimal(Integer id) {
        animalsRepository.deleteById(id);
    }
}