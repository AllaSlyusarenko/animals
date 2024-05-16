package ru.mts.hw_3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.mts.hw_3.entity.Animal;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;
import ru.mts.hw_3.repository.config.TestDatabaseConfig;
import ru.mts.hw_3.service.AnimalService;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = {TestDatabaseConfig.class})
@AutoConfigureMockMvc
@WebAppConfiguration
@DisplayName(value = "Animal methods controller tests")
class AnimalMethodsControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    AnimalService animalService;
    private Animal animalIn;
    private Animal animalOut;
    private Animal animal1;
    private Animal animal2;

    @BeforeEach
    void init() throws Exception {
        Breed breed = new Breed();
        breed.setIdBreed(1);
        breed.setName("breed name");
        AnimalType animalType = new AnimalType();
        animalType.setIdType(1);
        animalType.setType("type");
        animalType.setIsWild(true);

        animalIn = new Animal();
        animalIn.setName("animal name");
        animalIn.setAnimalType(animalType);
        animalIn.setAge(15);
        animalIn.setBreed(breed);
        animalIn.setCost(BigDecimal.valueOf(1500));
        animalIn.setBirthDate(LocalDate.of(1987, 5, 18));

        animalOut = new Animal();
        animalOut.setIdAnimal(1);
        animalOut.setName("animal name");
        animalOut.setAnimalType(animalType);
        animalOut.setAge(15);
        animalOut.setBreed(breed);
        animalOut.setCost(BigDecimal.valueOf(1500));
        animalOut.setBirthDate(LocalDate.of(1987, 5, 18));

        animal1 = new Animal();
        animal1.setIdAnimal(1);
        animal1.setName("animal name1");
        animal1.setAnimalType(animalType);
        animal1.setAge(15);
        animal1.setBreed(breed);
        animal1.setCost(BigDecimal.valueOf(1500));
        animal1.setBirthDate(LocalDate.of(1987, 5, 18));

        animal2 = new Animal();
        animal2.setIdAnimal(2);
        animal2.setName("animal name2");
        animal2.setAnimalType(animalType);
        animal2.setAge(16);
        animal2.setBreed(breed);
        animal2.setCost(BigDecimal.valueOf(2500));
        animal2.setBirthDate(LocalDate.of(1988, 6, 19));
    }

    @Test
    void getAllAnimals() throws Exception {
        when(animalService.getAllAnimals()).thenReturn(List.of(animal1, animal2));

        mockMvc.perform(get("/animals/all")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].idAnimal", is(animal1.getIdAnimal()), Integer.class))
                .andExpect(jsonPath("$[0].name").value(animal1.getName()))
                .andExpect(jsonPath("$[0].age").value(animal1.getAge()))
                .andExpect(jsonPath("$[0].cost").value(animal1.getCost()))
                .andExpect(jsonPath("$[1].idAnimal", is(animal2.getIdAnimal()), Integer.class))
                .andExpect(jsonPath("$[1].name").value(animal2.getName()))
                .andExpect(jsonPath("$[1].age").value(animal2.getAge()))
                .andExpect(jsonPath("$[1].cost").value(animal2.getCost()));
    }

    @Test
    void createAnimal() throws Exception {
        when(animalService.saveAnimal(Mockito.any(Animal.class)))
                .thenReturn(animalOut);
        mockMvc.perform(post("/animals/add")
                        .content(objectMapper.writeValueAsString(animalIn))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.idAnimal", is(animalOut.getIdAnimal()), Integer.class))
                .andExpect(jsonPath("$.name").value(animalOut.getName()))
                .andExpect(jsonPath("$.age").value(animalOut.getAge()))
                .andExpect(jsonPath("$.cost").value(animalOut.getCost()));
        verify(animalService, times(1)).saveAnimal(Mockito.any(Animal.class));
    }

    @Test
    void deleteAnimal() throws Exception {
        //when
        doNothing().when(animalService).deleteAnimal(Mockito.any(Integer.class));
        //then
        mockMvc.perform(delete("/animals/delete/{id}", 1)
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(animalService, times(1)).deleteAnimal(Mockito.any(Integer.class));
    }
}