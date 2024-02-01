package ru.mts.hw_3.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AbstractAnimalTest {
    @Test
    @DisplayName(value = "Тест сравнения объекта Dog с null")
    void testEqualsNull() {
        Dog dog1 = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        Dog dog2 = null;
        assertFalse(dog1.equals(dog2));
    }

    @Test
    @DisplayName(value = "Тест сравнения двух объектов Dog с одинаковыми значениями")
    void testEqualsCorrect() {
        Dog dog1 = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        Dog dog2 = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        assertTrue(dog1.equals(dog2));
        assertTrue(dog1.hashCode() == dog2.hashCode());
    }

    @Test
    @DisplayName(value = "Тест сравнения двух объектов Dog с разными значениями breed")
    void testEqualsDifferentValues() {
        Dog dog1 = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        Dog dog2 = new Dog("breed12", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        assertFalse(dog1.equals(dog2));
        assertFalse(dog1.hashCode() == dog2.hashCode());
    }

    @Test
    @DisplayName(value = "Тест сравнения объектов Dog и Wolf с одинаковыми значениями")
    void testEqualsDifferentTypes() {
        Dog dog = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        Wolf wolf = new Wolf("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        assertFalse(dog.equals(wolf));
        assertTrue(dog.hashCode() == wolf.hashCode());
    }
}