//package ru.mts.hw_3.service;
//
//import org.junit.Rule;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
////@Sql
//@DisplayName(value = "Tests of methods for getting all tables from the database")
//@SpringBootTest
//class ServiceDBTest {
//
//    @Rule
//    public PostgreSQLContainer postgres = new PostgreSQLContainer();
//
//    @Test
//    public void test() {
//        assertNotNull(postgres.getJdbcUrl());
//    }
//
//    @Test
//    @DisplayName(value = "Test of getting All Creatures")
//    @Sql({"/creature.sql"})
//    void getAllCreatures() throws SQLException {
////        List<Creature> creatures = serviceDB.getAllCreatures();
////        assertEquals(3, creatures.size());
//    }
//
//    @Test
//    void getAllAnimalTypes() {
//    }
//
//    @Test
//    void getAllHabitats() {
//    }
//
//    @Test
//    void getAllProviders() {
//    }
//}