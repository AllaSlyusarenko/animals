package ru.mts.hw_3.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mts.entity.AnimalType;
import ru.mts.entity.Dog;
import ru.mts.entity.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName(value = "Тесты сравнения объектов AbstractAnimal")
class AbstractAnimalTest {
    @Test
    @DisplayName(value = "Тест сравнения объекта Dog с null")
    void testEqualsNull() {
        Dog dog1 = new Dog(AnimalType.DOG,"breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.now().minusYears(50).minusMonths(5).minusDays(20));
        Dog dog2 = null;
        assertFalse(dog1.equals(dog2));
    }

    @Test
    @DisplayName(value = "Тест сравнения двух объектов Dog с одинаковыми значениями")
    void testEqualsCorrect() {
        Dog dog1 = new Dog(AnimalType.DOG,"breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.now().minusYears(50).minusMonths(5).minusDays(20));
        dog1.setSecretInformation("very secret information");
        Dog dog2 = new Dog(AnimalType.DOG,"breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.now().minusYears(50).minusMonths(5).minusDays(20));
        dog2.setSecretInformation("very secret information");
        assertTrue(dog1.equals(dog2));
        assertTrue(dog1.hashCode() == dog2.hashCode());
    }

    @Test
    @DisplayName(value = "Тест сравнения двух объектов Dog с разными значениями breed")
    void testEqualsDifferentValues() {
        Dog dog1 = new Dog(AnimalType.DOG,"breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.now().minusYears(50).minusMonths(5).minusDays(20));
        Dog dog2 = new Dog(AnimalType.DOG,"breed12", "name11", new BigDecimal("3224.68"), "character11", LocalDate.now().minusYears(50).minusMonths(5).minusDays(20));
        assertFalse(dog1.equals(dog2));
        assertFalse(dog1.hashCode() == dog2.hashCode());
    }

    @Test
    @DisplayName(value = "Тест сравнения объектов Dog и Wolf с одинаковыми значениями")
    void testEqualsDifferentTypes() {
        Dog dog = new Dog(AnimalType.DOG,"breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.now().minusYears(50).minusMonths(5).minusDays(20));
        dog.setSecretInformation("very secret information");
        Wolf wolf = new Wolf(AnimalType.DOG,"breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.now().minusYears(50).minusMonths(5).minusDays(20));
        wolf.setSecretInformation("very secret information");
        assertFalse(dog.equals(wolf));
        assertTrue(dog.hashCode() == wolf.hashCode());
    }
}