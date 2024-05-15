package ru.mts.hw_3.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.mts.hw_3.Main;
import ru.mts.hw_3.repository.config.TestDatabaseConfig;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = {TestDatabaseConfig.class, Main.class})
@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@WebAppConfiguration
//        ("classpath:application-test.properties")
@DisplayName(value = "Animal methods controller tests")
class AnimalMethodsControllerTest {

    @Test
    void getAllAnimals() {
    }

    @Test
    void createAnimal() {
    }

    @Test
    void deleteAnimal() {
    }
}