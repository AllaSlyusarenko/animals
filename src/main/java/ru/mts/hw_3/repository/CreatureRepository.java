package ru.mts.hw_3.repository;

import org.springframework.stereotype.Repository;
import ru.mts.hw_3.entity.Creature;

@Repository
public class CreatureRepository extends AbstractHibernateDao<Creature> {

    public CreatureRepository() {
        setClazz(Creature.class);
    }
}