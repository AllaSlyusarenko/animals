//package ru.mts.hw_3.repository.pervoe;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import ru.mts.hw_3.entity.Animal;
//import ru.mts.hw_3.repository.AnimalRepository;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@Testcontainers
//@SpringBootTest
//@ActiveProfiles({"test"})
//public class AnimalRepositoryTest {
//
//    @Container
//    public static PostgreSQLContainer<PostgreSQLContainerShared> postgreSQLContainer = PostgreSQLContainerShared.getInstance();
//
//    @Autowired
//    AnimalRepository animalRepository;
//
//    @Test
//    public void getAnimals() {
//        List<Animal> animals = animalRepository.findAll();
//        System.out.println("");
//        assertEquals(10, animals.size());
//    }
//
////    @DynamicPropertySource
////    static void registerPgProperties(DynamicPropertyRegistry registry) {
////        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
////        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
////        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
////    }
//}