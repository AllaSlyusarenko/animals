package ru.mts.hw_3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.repository.config.TestDatabaseConfig;
import ru.mts.hw_3.service.AnimalService;

import java.util.List;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = {TestDatabaseConfig.class, Main.class})
@AutoConfigureMockMvc
@WebAppConfiguration
@DisplayName(value = "AOP Logging tests")
class SpringAopApplicationTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AnimalService animalService;

    @Test
    void getAllAnimals() throws Exception {
        List<Animal> animals = animalService.getAllAnimals();
        System.out.println("");
    }

}
