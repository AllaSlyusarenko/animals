package ru.mts.hw_3.repository;

import org.springframework.stereotype.Repository;
import ru.mts.hw_3.entity.AnimalType;

@Repository
public class AnimalTypeRepository extends AbstractHibernateDao<AnimalType> {

    public AnimalTypeRepository() {
        setClazz(AnimalType.class);
    }
}