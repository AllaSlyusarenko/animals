package ru.mts.hw_3.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.entity.Animal;
import ru.mts.entity.AnimalType;
import ru.mts.entity.Dog;
import ru.mts.entity.Wolf;
import ru.mts.service.CreateAnimalService;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName(value = "Tests of the AnimalsRepository class")
class AnimalsRepositoryImplTest {
    AnimalType animalTypeDog = AnimalType.DOG;
    AnimalType animalTypeWolf = AnimalType.WOLF;
    private final Map<String, List<Animal>> animalsMap = new HashMap<>();
    @Autowired
    private AnimalsRepository animalsRepository;
    @Autowired
    private CreateAnimalService createAnimalService;

    protected void initAnimals() throws NoSuchFieldException, IllegalAccessException {
        List<Animal> animalsDog = new ArrayList<>();
        animalsDog.add(new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8)));
        animalsDog.add(new Dog("breed2", "bumburuwka", new BigDecimal("3441.68"), "character2", LocalDate.of(1985, 4, 6)));
        animalsDog.add(new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1983, 2, 8)));
        animalsDog.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog.add(new Dog("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1965, 1, 4)));
        animalsDog.add(new Dog("breed6", "persik", new BigDecimal("939.68"), "character6", LocalDate.of(1988, 4, 30)));
        animalsDog.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog.add(new Dog("breed8", "mushka", new BigDecimal("3981.68"), "character8", LocalDate.of(2014, 9, 7)));
        animalsDog.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog.add(new Dog("breed10", "persik", new BigDecimal("1388.68"), "character10", LocalDate.of(1994, 6, 20)));
        Field animalTypeFieldDog = createAnimalService.getClass().getDeclaredField("animalType");
        animalTypeFieldDog.setAccessible(true);
        animalTypeFieldDog.set(createAnimalService, animalTypeDog);
        animalsMap.put(animalTypeDog.name(), animalsDog);

        List<Animal> animalsWolf = new ArrayList<>();
        animalsWolf.add(new Wolf("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8)));
        animalsWolf.add(new Wolf("breed2", "bumburuwka", new BigDecimal("3441.68"), "character2", LocalDate.of(1985, 4, 6)));
        animalsWolf.add(new Wolf("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1983, 2, 8)));
        animalsWolf.add(new Wolf("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsWolf.add(new Wolf("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1970, 1, 4)));
        animalsWolf.add(new Wolf("breed6", "persik", new BigDecimal("939.68"), "character6", LocalDate.of(1988, 4, 30)));
        animalsWolf.add(new Wolf("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsWolf.add(new Wolf("breed8", "mushka", new BigDecimal("3981.68"), "character8", LocalDate.of(2014, 9, 7)));
        animalsWolf.add(new Wolf("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsWolf.add(new Wolf("breed10", "persik", new BigDecimal("1388.68"), "character10", LocalDate.of(1994, 6, 20)));
        Field animalTypeFieldWolf = createAnimalService.getClass().getDeclaredField("animalType");
        animalTypeFieldWolf.setAccessible(true);
        animalTypeFieldWolf.set(createAnimalService, animalTypeWolf);
        animalsMap.put(animalTypeWolf.name(), animalsWolf);
    }

    @BeforeAll
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        initAnimals();
    }

    @Test
    @DisplayName(value = "Tests of the findLeapYearNames Correct")
    void findLeapYearNamesCorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);

        List<Animal> animalList = animalsMap.get(animalTypeDog.toString());
        assertThat(animalList.get(0), instanceOf(Animal.class));
        assertEquals("breed1", animalList.get(0).getBreed());

        Map<String, LocalDate> names = animalsRepository.findLeapYearNames();
        assertEquals(4, names.size());
        assertTrue(names.containsKey("DOG persik"));
        assertTrue(names.containsKey("WOLF persik"));
    }

    @Test
    @DisplayName(value = "Tests of the findLeapYearNames Incorrect - null")
    void findLeapYearNamesIncorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, null);
        Map<String, LocalDate> names = animalsRepository.findLeapYearNames();
        assertEquals(0, names.size());
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal correct")
    void findOlderAnimalCorrect() throws NoSuchFieldException, IllegalAccessException {
        int N = 15;
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(N);
        assertEquals(14, olderAnimals.size());
        assertTrue(olderAnimals.containsKey(
                new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8))));
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal incorrect")
    void findOlderAnimalIncorrect() throws NoSuchFieldException, IllegalAccessException {
        int N = 15;
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, null);
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(N);
        assertEquals(0, olderAnimals.size());
    }

    @Test
    @DisplayName(value = "Tests of the findOlderAnimal max")
    void findOlderAnimalMax() throws NoSuchFieldException, IllegalAccessException {
        int N = 100;
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(N);
        assertEquals(1, olderAnimals.size());
        assertTrue(olderAnimals.containsKey(
                new Dog("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1965, 1, 4))));
    }

    @Test
    @DisplayName(value = "Tests of the findDuplicate correct")
    void findDuplicateCorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, animalsMap);
        Map<String, Integer> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(2, duplicateAnimals.size());
        assertEquals(3, duplicateAnimals.get(animalTypeDog.name()));
        assertEquals(3, duplicateAnimals.get(animalTypeWolf.name()));
    }

    @Test
    @DisplayName(value = "Tests of the findDuplicate incorrect")
    void findDuplicateIncorrect() throws NoSuchFieldException, IllegalAccessException {
        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
        animalNamesField.setAccessible(true);
        animalNamesField.set(animalsRepository, null);
        Map<String, Integer> duplicateAnimals = animalsRepository.findDuplicate();
        assertEquals(0, duplicateAnimals.size());
    }
}