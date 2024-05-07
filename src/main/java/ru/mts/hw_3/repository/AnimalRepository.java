package ru.mts.hw_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.mts.hw_3.entity.Animal;

@Repository
@EnableTransactionManagement
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}