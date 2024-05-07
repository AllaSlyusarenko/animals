package ru.mts.hw_3.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mts.hw_3.entity.Animal;

@Repository
//@Qualifier("animalrepository")
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}