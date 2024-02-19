package ru.mts.hw_3.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.entity.Animal;
import ru.mts.entity.Dog;
import ru.mts.hw_3.TestAppConfiguration;
import ru.mts.service.CreateAnimalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestAppConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName(value = "Tests of the AnimalsRepository class")
class AnimalsRepositoryImplTest {
    int N = 10;
    private final Animal[] animals = new Animal[N];
    @Autowired
    private AnimalsRepository animalsRepository;
    private CreateAnimalService createAnimalService;

    protected void initAnimals() {
        animals[0] = new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8));
        animals[1] = new Dog("breed2", "bumburuwka", new BigDecimal("3441.68"), "character2", LocalDate.of(1985, 4, 6));
        animals[2] = new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8));
        animals[3] = new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10));
        animals[4] = new Dog("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1970, 1, 4));
        animals[5] = new Dog("breed6", "persik", new BigDecimal("939.68"), "character6", LocalDate.of(1988, 4, 30));
        animals[6] = new Dog("breed7", "taratuwka", new BigDecimal("4602.68"), "character7", LocalDate.of(2009, 8, 7));
        animals[7] = new Dog("breed8", "mushka", new BigDecimal("3981.68"), "character8", LocalDate.of(2014, 9, 7));
        animals[8] = new Dog("breed9", "barsik", new BigDecimal("1241.68"), "character9", LocalDate.of(2013, 10, 10));
        animals[9] = new Dog("breed10", "persik", new BigDecimal("1388.68"), "character10", LocalDate.of(1994, 6, 20));
    }

    @BeforeAll
    void setUp() {
        initAnimals();
        animalsRepository = new AnimalsRepositoryImpl(createAnimalService);
    }

    @Test
    @DisplayName(value = "Tests of the findLeapYearNames Correct")
    void findLeapYearNamesCorrect() {
        animalsRepository.setAnimals(animals);
        assertThat(animals[0], instanceOf(Animal.class));
        assertEquals("breed1", animals[0].getBreed());

        String[] names = animalsRepository.findLeapYearNames();
        assertEquals(3, names.length);
        assertEquals("tuzik", names[0]);
    }

    @Test
    @DisplayName(value = "Tests of the findLeapYearNames Incorrect - null")
    void findLeapYearNamesIncorrect() {
        animalsRepository.setAnimals(null);
        String[] names = animalsRepository.findLeapYearNames();
        assertEquals(0, names.length);
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal correct")
    void findOlderAnimalCorrect() {
        int N = 15;
        animalsRepository.setAnimals(animals);
        Animal[] names = animalsRepository.findOlderAnimal(N);
        assertEquals(7, names.length);
        assertEquals("tuzik", names[0].getName());
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal incorrect")
    void findOlderAnimalIncorrect() {
        int N = 15;
        animalsRepository.setAnimals(null);
        Animal[] names = animalsRepository.findOlderAnimal(N);
        assertEquals(0, names.length);
    }

    @Test
    @DisplayName(value = "Tests of the findDuplicate correct")
    void findDuplicateCorrect() {
        animalsRepository.setAnimals(animals);
        Set<Animal> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(1, duplicateAnimals.size());
        assertTrue(duplicateAnimals.contains(animals[0]));
    }

    @Test
    @DisplayName(value = "Tests of the findDuplicate incorrect")
    void findDuplicateIncorrect() {
        animalsRepository.setAnimals(null);
        Set<Animal> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(0, duplicateAnimals.size());
    }
}