package ru.mts.hw_3.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName(value = "Тест поиска массива имён животных, рождённых в високосный год, если входящий массив null")
    void findLeapYearNamesNull() {
        Animal[] animalsNull = null;
        String[] names = searchService.findLeapYearNames(animalsNull);
        assertEquals(0, names.length);
    }

    @Test
    @DisplayName(value = "Тест поиска массива имён животных, рождённых в високосный год с корректными данными")
    void findLeapYearNames() {
        String[] names = searchService.findLeapYearNames(animals);
        assertEquals(3, names.length);
        assertEquals("name11", animals[1].getName());
        assertEquals("name21", animals[2].getName());
    }

    @Test
    @DisplayName(value = "Тест поиска массива имён животных, рождённых в високосный год, если входящий массив пустой")
    void findLeapYearNamesEmptyArray() {
        Animal[] animalsEmpty = new Animal[0];
        String[] names = searchService.findLeapYearNames(animalsEmpty);
        assertEquals(0, names.length);
    }

    @ParameterizedTest(name = "Try to test with value: {arguments}")
    @ValueSource(ints = {100, 50, 30, 20, -50})
    @DisplayName(value = "Тест поиска списка животных, которые старше значений из списка ints")
    void findOlderAnimal(Integer argument) {
        Animal[] animalsOut = searchService.findOlderAnimal(animals, argument);
        assertNotNull(animalsOut);
    }

    @Test
    @DisplayName(value = "Тест поиска массива животных, которые старше age, если входящий массив null")
    void findOlderAnimalNull() {
        int age = 100;
        Animal[] animalsNull = null;
        Animal[] animalsOut = searchService.findOlderAnimal(animalsNull, age);
        assertEquals(0, animalsOut.length);
    }

    @Test
    @DisplayName(value = "Тест поиска массива животных, которые старше age = 100")
    void findOlderAnimal100() {
        int age = 100;
        Animal[] animalsOut = searchService.findOlderAnimal(animals, age);
        assertEquals(0, animalsOut.length);
    }

    @Test
    @DisplayName(value = "Тест поиска массива животных, которые старше age = 50")
    void findOlderAnimal50() {
        int age = 50;
        Animal[] animalsOut = searchService.findOlderAnimal(animals, age);
        assertEquals(4, animalsOut.length);
    }

    @Test
    @DisplayName(value = "Тест поиска массива животных, которые старше age = -50")
    void findOlderAnimalLess0() {
        int age = -50;
        Animal[] animalsOut = searchService.findOlderAnimal(animals, age);
        assertEquals(0, animalsOut.length);
    }

    @Test
    @DisplayName(value = "Тест поиска массива дубликатов животных, если входящий массив null")
    void findDuplicateNull() {
        Animal[] animalsNull = null;
        Animal[] animalsOut = searchService.findDuplicate(animalsNull);
        assertEquals(0, animalsOut.length);
    }

    @Test
    @DisplayName(value = "Тест поиска массива дубликатов животных с корректными данными")
    void findDuplicate() {
        Animal[] animalsOut = searchService.findDuplicate(animals);
        assertEquals(2, animalsOut.length);
        assertEquals("name11", animalsOut[0].getName());
        assertEquals("name21", animalsOut[1].getName());
    }

    @Test
    @DisplayName(value = "Тест поиска массива дубликатов животных, если входящий массив пустой")
    void findDuplicateEmptyArray() {
        Animal[] animalsEmpty = new Animal[0];
        Animal[] animalsOut = searchService.findDuplicate(animalsEmpty);
        assertEquals(0, animalsOut.length);
    }
}