package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<Breed> getBreeds() {
        return breedRepository.findAll();
    }
}