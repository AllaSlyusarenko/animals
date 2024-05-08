package ru.mts.hw_3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.hw_3.config.ConfigPropertiesForDB;
import ru.mts.hw_3.repository.AnimalRepository;

@Service
public class ServiceFoDB {
    private final ConfigPropertiesForDB configPropertiesForDB;

    public ServiceFoDB(ConfigPropertiesForDB configPropertiesForDB) {
        this.configPropertiesForDB = configPropertiesForDB;
    }
}
