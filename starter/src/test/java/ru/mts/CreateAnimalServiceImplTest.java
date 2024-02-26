package ru.mts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.entity.Animal;
import ru.mts.service.CreateAnimalService;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest(classes = TestConfigurationApp.class)
@DisplayName(value = "Animal creation service test")
public class CreateAnimalServiceImplTest {
    @Autowired
    private CreateAnimalService createAnimalService;

    @Test
    @DisplayName(value = "Animal creation test")
    void createAnimals() {
        Map<String, List<Animal>> animals = createAnimalService.createAnimals();
        for (List<Animal> value : animals.values()) {
            assertEquals(10, value.size());
            assertThat(value.get(0), instanceOf(Animal.class));
            assertThat(value.get(9), instanceOf(Animal.class));
            assertEquals(value.get(0).getClass(), value.get(1).getClass());
        }
    }

    @Test
    @DisplayName(value = "Animal creation specified quantity test")
    void createAnimalsSpecifiedQuantity() {
        int N = 15;
        Map<String, List<Animal>> animals = createAnimalService.createAnimals(N);
        for (List<Animal> value : animals.values()) {
            assertEquals(N, value.size());
            assertThat(value.get(0), instanceOf(Animal.class));
            assertThat(value.get(N - 1), instanceOf(Animal.class));
        }
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