package ru.mts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.entity.AbstractAnimal;
import ru.mts.service.CreateAnimalService;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    void createAnimals() throws IOException, NoSuchFieldException, IllegalAccessException {
        Path path = Paths.get("src\\test\\resources\\animals\\logData.txt");
        Field pathField = createAnimalService.getClass().getDeclaredField("path");
        pathField.setAccessible(true);
        pathField.set(createAnimalService, path);
        Map<String, List<AbstractAnimal>> animals = createAnimalService.createAnimals(10);
        for (List<AbstractAnimal> value : animals.values()) {
            assertEquals(10, value.size());
            assertThat(value.get(0), instanceOf(AbstractAnimal.class));
            assertThat(value.get(9), instanceOf(AbstractAnimal.class));
            assertEquals(value.get(0).getClass(), value.get(1).getClass());
        }
    }

    @Test
    @DisplayName(value = "Animal creation specified quantity test")
    void createAnimalsSpecifiedQuantity() throws IOException, NoSuchFieldException, IllegalAccessException {
        Path path = Paths.get("src\\test\\resources\\animals\\logData.txt");
        Field pathField = createAnimalService.getClass().getDeclaredField("path");
        pathField.setAccessible(true);
        pathField.set(createAnimalService, path);
        int N = 15;
        Map<String, List<AbstractAnimal>> animals = createAnimalService.createAnimals(N);
        for (List<AbstractAnimal> value : animals.values()) {
            assertEquals(N, value.size());
            assertThat(value.get(0), instanceOf(AbstractAnimal.class));
            assertThat(value.get(N - 1), instanceOf(AbstractAnimal.class));
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