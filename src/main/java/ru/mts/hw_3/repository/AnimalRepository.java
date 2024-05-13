package ru.mts.hw_3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mts.hw_3.entity.Animal;

import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    @Override
    List<Animal> findAll();
}