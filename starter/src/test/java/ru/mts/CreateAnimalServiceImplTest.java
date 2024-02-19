package ru.mts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mts.entity.Animal;
import ru.mts.service.CreateAnimalService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootConfiguration
@SpringBootTest(classes = TestConfigurationApp.class)
@DisplayName(value = "Animal creation service test")
public class CreateAnimalServiceImplTest {
    @Autowired
    private CreateAnimalService createAnimalService;

    @Test
    @DisplayName(value = "Animal creation test")
    void createAnimals() {
        //given
        //when
        Animal[] animals = createAnimalService.createAnimals();
        //then
        assertEquals(10, animals.length);
        assertThat(animals[0], instanceOf(Animal.class));
        assertThat(animals[9], instanceOf(Animal.class));
        assertEquals(animals[0].getClass(), animals[1].getClass());
    }

    @Test
    @DisplayName(value = "Animal creation specified quantity test")
    void createAnimalsSpecifiedQuantity() {
        //given
        int N = 15;
        //when
        Animal[] animals = createAnimalService.createAnimals(N);
        //then
        assertEquals(N, animals.length);
        assertThat(animals[0], instanceOf(Animal.class));
        assertThat(animals[N - 1], instanceOf(Animal.class));
    }

    @Test
    @DisplayName(value = "Animal creation test with incorrect data")
    void createAnimalsSpecifiedQuantityWithIncorrectDataMinus() {
        int N = -15;
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> createAnimalService.createAnimals(N));
    }

    @Test
    @DisplayName(value = "Animal creation test with incorrect data 0")
    void createAnimalsSpecifiedQuantityWithIncorrectData0() {
        int N = 0;
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> createAnimalService.createAnimals(N));
    }
}