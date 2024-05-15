package ru.mts.hw_3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mts.hw_3.entity.Breed;

public interface BreedRepository extends CrudRepository<Breed, Integer> {
}
