package ru.mts.hw_3.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.mts.hw_3.entity.AnimalType;

@Repository
public class AnimalTypeRepository extends AbstractHibernateDao<AnimalType> {

    private final SessionFactory sessionFactory;

    public AnimalTypeRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
        setClazz(AnimalType.class);
    }
}
