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
import ru.mts.service.CreateAnimalService;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName(value = "Tests of the AnimalsRepository class")
class AnimalsRepositoryImplTest {
    int N = 10;
    AnimalType animalTypeDog = AnimalType.DOG;
    private final Map<String, List<Animal>> animalsMap = new HashMap<>();
    @Autowired
    private AnimalsRepository animalsRepository;
    @Autowired
    private CreateAnimalService createAnimalService;

    protected void initAnimals() throws NoSuchFieldException, IllegalAccessException {
        List<Animal> animalsDog1 = new ArrayList<>();
        animalsDog1.add(new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8)));
        animalsDog1.add(new Dog("breed2", "bumburuwka", new BigDecimal("3441.68"), "character2", LocalDate.of(1985, 4, 6)));
        animalsDog1.add(new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1983, 2, 8)));
        animalsDog1.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog1.add(new Dog("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1970, 1, 4)));
        animalsDog1.add(new Dog("breed6", "persik", new BigDecimal("939.68"), "character6", LocalDate.of(1988, 4, 30)));
        animalsDog1.add(new Dog("breed7", "taratuwka", new BigDecimal("4602.68"), "character7", LocalDate.of(2009, 8, 7)));
        animalsDog1.add(new Dog("breed8", "mushka", new BigDecimal("3981.68"), "character8", LocalDate.of(2014, 9, 7)));
        animalsDog1.add(new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10)));
        animalsDog1.add(new Dog("breed10", "persik", new BigDecimal("1388.68"), "character10", LocalDate.of(1994, 6, 20)));
        Field animalTypeField1 = createAnimalService.getClass().getDeclaredField("animalType");
        animalTypeField1.setAccessible(true);
        animalTypeField1.set(createAnimalService, animalTypeDog);
        animalsMap.put(animalTypeDog.name(), animalsDog1);
    }

    @BeforeAll
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        initAnimals();
//        animalsRepository = new AnimalsRepositoryImpl(createAnimalService);
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
        assertEquals(2, names.size());
        String name = "DOG persik";
        assertTrue(names.containsKey(name));
    }

//    @Test
//    @DisplayName(value = "Tests of the findLeapYearNames Incorrect - null")
//    void findLeapYearNamesIncorrect() throws NoSuchFieldException, IllegalAccessException {
//        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
//        animalNamesField.setAccessible(true);
//        animalNamesField.set(animalsRepository, null);
//        String[] names = animalsRepository.findLeapYearNames();
//        assertEquals(0, names.length);
//    }
//
//    @Test
//    @DisplayName(value = "Tests of the findOlderAnimal correct")
//    void findOlderAnimalCorrect() throws NoSuchFieldException, IllegalAccessException {
//        int N = 15;
//        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
//        animalNamesField.setAccessible(true);
//        animalNamesField.set(animalsRepository, animals);
//        Animal[] names = animalsRepository.findOlderAnimal(N);
//        assertEquals(7, names.length);
//        assertEquals("tuzik", names[0].getName());
//    }
//
//    @Test
//    @DisplayName(value = "Tests of the findOlderAnimal incorrect")
//    void findOlderAnimalIncorrect() throws NoSuchFieldException, IllegalAccessException {
//        int N = 15;
//        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
//        animalNamesField.setAccessible(true);
//        animalNamesField.set(animalsRepository, null);
//        Animal[] names = animalsRepository.findOlderAnimal(N);
//        assertEquals(0, names.length);
//    }
//
//    @Test
//    @DisplayName(value = "Tests of the findDuplicate correct")
//    void findDuplicateCorrect() throws NoSuchFieldException, IllegalAccessException {
//        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
//        animalNamesField.setAccessible(true);
//        animalNamesField.set(animalsRepository, animals);
//        Set<Animal> duplicateAnimals = animalsRepository.findDuplicate();
//        assertEquals(1, duplicateAnimals.size());
//        assertTrue(duplicateAnimals.contains(animals[0]));
//    }
//
//    @Test
//    @DisplayName(value = "Tests of the findDuplicate incorrect")
//    void findDuplicateIncorrect() throws NoSuchFieldException, IllegalAccessException {
//        Field animalNamesField = animalsRepository.getClass().getDeclaredField("animals");
//        animalNamesField.setAccessible(true);
//        animalNamesField.set(animalsRepository, null);
//        Set<Animal> duplicateAnimals = animalsRepository.findDuplicate();
//        assertEquals(0, duplicateAnimals.size());
//    }
}