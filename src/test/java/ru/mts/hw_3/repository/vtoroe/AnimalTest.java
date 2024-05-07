package ru.mts.hw_3.repository.vtoroe;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.mts.hw_3.config.ConfigPropertiesForDB;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.AnimalRepository;
import ru.mts.hw_3.service.ServiceFoDB;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestDatabaseConfig.class, ConfigPropertiesForDB.class})
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AnimalTest {

    private final AnimalRepository animalRepository;
    @Autowired
    private ServiceFoDB serviceFoDB;

    @Autowired
    public AnimalTest(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    @Test
    public void getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        System.out.println("");
        assertEquals(10, animals.size());
    }
}