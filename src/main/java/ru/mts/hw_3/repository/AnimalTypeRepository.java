package ru.mts.hw_3.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mts.hw_3.entity.AnimalType;

public interface AnimalTypeRepository extends CrudRepository<AnimalType, Integer> {
}
