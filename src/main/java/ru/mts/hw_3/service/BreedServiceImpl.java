package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.annotations.Logging;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.repository.BreedRepository;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

    private final BreedRepository breedRepository;

    @Autowired
    public BreedServiceImpl(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    @Override
    @Logging(entering = true, exiting = true)
    public List<Breed> getBreeds() {
        return breedRepository.findAll();
    }

    @Override
    @Logging(entering = true, exiting = true, logArgs = true, logResult = true)
    public Breed saveBreed(Breed breed) {
        return breedRepository.save(breed);
    }
}