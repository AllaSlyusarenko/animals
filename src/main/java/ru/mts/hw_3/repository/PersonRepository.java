package ru.mts.hw_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.hw_3.entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByUsername(String username);
}