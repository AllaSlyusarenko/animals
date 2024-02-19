package ru.mts.hw_3.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.mts.entity.Animal;
import ru.mts.entity.Dog;
import ru.mts.hw_3.TestAppConfiguration;
import ru.mts.service.CreateAnimalService;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestAppConfiguration.class)
@ExtendWith(MockitoExtension.class)
class AnimalsRepositoryImplTest {
    int N = 10;
    private Animal[] animals = new Animal[N];
    private AnimalsRepository animalsRepository;
    @MockBean
    private CreateAnimalService createAnimalService;

    protected void initAnimals() {
        animals[0] = new Dog("breed1", "tuzik", new BigDecimal("2844.68"), "character1", LocalDate.of(1980, 2, 8));
        animals[1] = new Dog("breed2", "bumburuwka", new BigDecimal("3441.68"), "character2", LocalDate.of(1985, 4, 6));
        animals[2] = new Dog("breed3", "mushka", new BigDecimal("1785.68"), "character3", LocalDate.of(1980, 2, 9));
        animals[3] = new Dog("breed4", "mushka", new BigDecimal("3061.6"), "character4", LocalDate.of(1998, 4, 10));
        animals[4] = new Dog("breed5", "barsik", new BigDecimal("718.68"), "character5", LocalDate.of(1970, 1, 4));
        animals[5] = new Dog("breed6", "persik", new BigDecimal("939.68"), "character6", LocalDate.of(1988, 4, 30));
        animals[6] = new Dog("breed7", "taratuwka", new BigDecimal("4602.68"), "character7", LocalDate.of(2009, 8, 7));
        animals[7] = new Dog("breed8", "mushka", new BigDecimal("3981.68"), "character8", LocalDate.of(2014, 9, 7));
        animals[8] = new Dog("breed9", "barsik", new BigDecimal("1241.68"), "character9", LocalDate.of(2013, 10, 10));
        animals[9] = new Dog("breed10", "persik", new BigDecimal("1388.68"), "character10", LocalDate.of(1994, 6, 20));
    }

    @Test
    void test(){}

    @Test
    void findLeapYearNames() {
        initAnimals();
        when(createAnimalService.createAnimals()).thenReturn(animals);
        animalsRepository = new AnimalsRepositoryImpl(createAnimalService);
//        animalsRepository.setAnimals(animals);
        assertThat(animals[0], instanceOf(Animal.class));
        assertEquals("breed1", animals[0].getBreed());

        String[] names = animalsRepository.findLeapYearNames();
        System.out.println("");
        assertEquals(3, names.length);

    }

    @Test
    void findOlderAnimal() {
    }

    @Test
    void findDuplicate() {
    }

    @Test
    void printDuplicate() {
    }
}