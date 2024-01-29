package ru.mts.hw_3.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalFactory;
import ru.mts.hw_3.entity.AnimalType;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchServiceImplTest {
    private SearchServiceImpl searchService;
    Animal[] animals = new Animal[8];

    @BeforeAll
    void setup() {
        AnimalFactory animalFactory = new AnimalFactory();
        searchService = new SearchServiceImpl();
        animals[0] = null;
        animals[1] = animalFactory.createAnimal(AnimalType.DOG, "breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1972, 5, 22));
        animals[2] = animalFactory.createAnimal(AnimalType.WOLF, "breed21", "name21", new BigDecimal("34.68"), "character21", LocalDate.of(2010, 3, 22));
        animals[3] = animalFactory.createAnimal(AnimalType.DOG, "breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1972, 5, 22));
        animals[4] = null;
        animals[5] = animalFactory.createAnimal(AnimalType.DOG, "breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1972, 5, 22));
        animals[6] = animalFactory.createAnimal(AnimalType.DOG, "breed21", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        animals[7] = animalFactory.createAnimal(AnimalType.WOLF, "breed21", "name21", new BigDecimal("34.68"), "character21", LocalDate.of(2010, 3, 22));
    }

    @Test
    void findLeapYearNames() {
        String[] names = searchService.findLeapYearNames(animals);
        assertEquals(3, names.length);
        assertEquals("name11", animals[1].getName());
        assertEquals("name21", animals[2].getName());
    }

    @Test
    void findLeapYearNamesEmptyArray() {
        Animal[] animalsEmpty = new Animal[0];
        String[] names = searchService.findLeapYearNames(animalsEmpty);
        assertEquals(0, names.length);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 50, 30, 20, -50})
    void findOlderAnimal(Integer argument) {
        Animal[] animalsOut = searchService.findOlderAnimal(animals, argument);
        assertNotNull(animalsOut);
    }

    @Test
    void findOlderAnimal100() {
        int age = 100;
        Animal[] animalsOut = searchService.findOlderAnimal(animals, age);
        assertEquals(0, animalsOut.length);
    }

    @Test
    void findOlderAnimal50() {
        int age = 50;
        Animal[] animalsOut = searchService.findOlderAnimal(animals, age);
        assertEquals(4, animalsOut.length);
    }

    @Test
    void findOlderAnimalLess0() {
        int age = -50;
        Animal[] animalsOut = searchService.findOlderAnimal(animals, age);
        assertEquals(0, animalsOut.length);
    }

    @Test
    void findDuplicate() {
        Animal[] animalsOut = searchService.findDuplicate(animals);
        assertEquals(2, animalsOut.length);
        assertEquals("name11", animalsOut[0].getName());
        assertEquals("name21", animalsOut[1].getName());
    }

    @Test
    void findDuplicateEmptyArray() {
        Animal[] animalsEmpty = new Animal[0];
        Animal[] animalsOut = searchService.findDuplicate(animalsEmpty);
        assertEquals(0, animalsOut.length);
    }
}