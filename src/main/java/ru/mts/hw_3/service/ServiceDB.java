package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Creature;
import ru.mts.hw_3.entity.Habitat;
import ru.mts.hw_3.entity.Provider;

import java.sql.SQLException;
import java.util.List;

public interface ServiceDB {
    List<Creature> getAllCreatures() throws SQLException;

    List<AnimalType> getAllAnimalTypes() throws SQLException;

    List<Habitat> getAllHabitats() throws SQLException;

    List<Provider> getAllProviders() throws SQLException;
}