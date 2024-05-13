package ru.mts.hw_3.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.hw_3.Main;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.repository.config.TestDatabaseConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestDatabaseConfig.class, Main.class})
@ActiveProfiles("test")
@DisplayName(value = "Animal repository tests")
class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    @DisplayName(value = "get all animals test")
    public void getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        assertNotNull(animals);
        assertEquals(10, animals.size());
    }

    @Test
    @DisplayName(value = "get animal by id test")
    public void getAnimalById() {
        Optional<Animal> animal = animalRepository.findById(1);
        assertNotNull(animal);
        assertEquals(1, animal.get().getIdAnimal());
        assertThat(animal.get().getAnimalType(), instanceOf(AnimalType.class));
        assertThat(animal.get().getBreed(), instanceOf(Breed.class));
        assertThat(animal.get().getCost(), instanceOf(BigDecimal.class));
    }

    @Test
    @DisplayName(value = "get animal by wrong id test")
    public void getAnimalByWrongId() {
        Optional<Animal> animal = animalRepository.findById(0);
        assertEquals(animal, Optional.empty());
    }

    @Test
    @DisplayName(value = "delete all animals test")
    @DirtiesContext
    public void deleteAllAnimals() {
        animalRepository.deleteAll();
        List<Animal> animals = animalRepository.findAll();
        assertEquals(0, animals.size());
    }

    @Test
    @DisplayName(value = "update animal by id test")
    @DirtiesContext
    public void updateAnimalById() {
        Optional<Animal> animal = animalRepository.findById(1);
        assertNotNull(animal);
        Animal animalFromDB = animal.get();

        animalFromDB.setName("new name animal 1");
        animalRepository.save(animalFromDB);

        Animal animalUpdate = animalRepository.findById(1).get();
        assertNotNull(animalUpdate);
        assertEquals(1, animalUpdate.getIdAnimal());
        assertEquals("new name animal 1", animalUpdate.getName());
    }

    @Test
    @DisplayName(value = "save animal test")
    @DirtiesContext
    public void saveAnimal() {
        List<Animal> animals = animalRepository.findAll();
        int size = animals.size();
        assertEquals(10, size);

        Optional<Animal> animal1 = animalRepository.findById(1);
        assertNotNull(animal1);

        Animal animalFromDB = animal1.get();
        animalFromDB.setIdAnimal(size + 1);
        animalRepository.save(animalFromDB);

        List<Animal> animalsNew = animalRepository.findAll();
        assertEquals(size + 1, animalsNew.size());
        Optional<Animal> animal11 = animalRepository.findById(size + 1);
        assertNotNull(animal11);
        assertEquals(animal1.get().getName(), animal11.get().getName());
    }
}