package ru.mts.hw_3.repository;

import org.springframework.stereotype.Repository;
import ru.mts.hw_3.entity.Animal;

@Repository
public class AnimalRepository extends AbstractHibernateDao<Animal> {

    public AnimalRepository() {
        setClazz(Animal.class);
    }
}