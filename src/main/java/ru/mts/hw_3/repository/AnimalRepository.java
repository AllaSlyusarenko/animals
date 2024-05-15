package ru.mts.hw_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.hw_3.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}