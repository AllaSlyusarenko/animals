package ru.mts.hw_3.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.mts.hw_3.entity.Creature;

@Repository
public class CreatureRepository extends AbstractHibernateDao<Creature> {
    private final SessionFactory sessionFactory;

    public CreatureRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
        setClazz(Creature.class);
    }
}