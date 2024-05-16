package ru.mts.hw_3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mts.hw_3.entity.Breed;

import java.util.List;

public interface BreedRepository extends CrudRepository<Breed, Integer> {
    @Override
    List<Breed> findAll();
}
