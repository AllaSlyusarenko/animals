package ru.mts.hw_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mts.hw_1.Purchase.countTotalSum;

class PurchaseTest {
    @Test
    public void countTotalSumCorrectValues() {
        //given (дано)
        Purchase purchase = new Purchase(5, 15.05, 10);
        //when (действие)
        double total = countTotalSum(purchase);
        //then (сравнения)
        assertEquals(67.73, total, "the calculations are correct");
    }

    @Test
    void countTotalSumNull() {
        boolean exceptionThrown = false;
        Purchase purchase = null;
        try {
            countTotalSum(purchase);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void countTotalSumWithWrongNumberOfGoods() {
        boolean exceptionThrown = false;
        Purchase purchase = new Purchase(-5, 15.05, 10);
        try {
            countTotalSum(purchase);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void countTotalSumWithWrongPrice() {
        boolean exceptionThrown = false;
        Purchase purchase = new Purchase(5, -15.05, 10);
        try {
            countTotalSum(purchase);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void countTotalSumWithDiscountLess0() {
        boolean exceptionThrown = false;
        Purchase purchase = new Purchase(5, 15.05, -10);
        try {
            countTotalSum(purchase);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void countTotalSumWithDiscountMore100() {
        boolean exceptionThrown = false;
        Purchase purchase = new Purchase(5, 15.05, 1000);
        try {
            countTotalSum(purchase);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void testWithMethodSource(Purchase argument) {
        assertNotNull(argument);
    }

    static Stream<Purchase> argsProviderFactory() {
        return Stream.of(new Purchase(5, 15.05, 10),
                new Purchase(-5, 15.05, 10),
                new Purchase(5, -15.05, 10),
                new Purchase(5, 15.05, -10),
                new Purchase(5, 15.05, 1000));
    }
}