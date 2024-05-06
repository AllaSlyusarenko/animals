package ru.mts.hw_3.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.hw_3.Main;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.service.AnimalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Main.class)
@DisplayName(value = "Database tests")
class AnimalRepositoryTest {
    @Autowired
    AnimalRepositoryBD animalRepository;
    @Autowired
    AnimalTypeRepository animalTypeRepository;
    @Autowired
    AnimalService animalService;

    @Test
    @DisplayName(value = "Creature object creation list test")
    public void createCreatures() {
        Map<String, List<Animal>> animals = animalService.createAnimals(10);
        List<Animal> animalList = animals.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        assertNotNull(animals);
        assertEquals(1, animals.size());
        assertEquals(10, animalList.size());
    }

    @Test
    @DisplayName(value = "Empty creature object creation test")
    public void addEmptyCreature() {
        List<Animal> animals = animalRepository.findAll();
        assertNotNull(animals);
        Animal animalEmptyIn = new Animal();
        Animal animalOut = animalRepository.create(animalEmptyIn);
        assertNotNull(animalOut);
        assertNull(animalOut.getName());
        assertEquals(null, animalOut.getAge());
        assertNotEquals(0, animalOut.getIdAnimal());
        assertNull(animalOut.getAnimalType());
    }

    @Test
    @DisplayName(value = "Creature object creation test")
    public void addNotEmptyCreature() {
        AnimalType animalType = new AnimalType();
        AnimalType animalTypeOut = animalTypeRepository.create(animalType);
        Breed breed = new Breed();
        Animal animalIn = new Animal(0, "Puwistik", animalTypeOut,  5, LocalDate.now(), LocalDate.now());
        Animal animalOut = animalRepository.create(animalIn);
        assertNotNull(animalOut);
        assertEquals("Puwistik", animalOut.getName());
        assertEquals(5, animalOut.getAge());
        assertNotEquals(0, animalOut.getIdAnimal());
    }
}