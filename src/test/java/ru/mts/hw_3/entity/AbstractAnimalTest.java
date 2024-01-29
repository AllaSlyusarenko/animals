package ru.mts.hw_3.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AbstractAnimalTest {

    @Test
    void testEqualsCorrect() {
        Dog dog1 = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        Dog dog2 = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        assertTrue(dog1.equals(dog2));
        assertTrue(dog1.hashCode() == dog2.hashCode());
    }

    @Test
    void testEqualsDifferentValues() {
        Dog dog1 = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        Dog dog2 = new Dog("breed12", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        assertFalse(dog1.equals(dog2));
        assertFalse(dog1.hashCode() == dog2.hashCode());
    }

    @Test
    void testEqualsDifferentTypes() {
        Dog dog = new Dog("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        Wolf wolf = new Wolf("breed11", "name11", new BigDecimal("3224.68"), "character11", LocalDate.of(1970, 5, 22));
        assertFalse(dog.equals(wolf));
        assertTrue(dog.hashCode() == wolf.hashCode());
    }
}