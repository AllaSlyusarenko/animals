package ru.mts.hw_1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mts.hw_1.Purchase.countTotalSum;

class PurchaseTest {
    @Test
    @DisplayName(value = "Тест расчёта суммы покупки с корректными входными данными")
    public void countTotalSumCorrectValues() {
        //given (дано)
        Purchase purchase = new Purchase(5, 15.05, 10);
        //when (действие)
        double total = countTotalSum(purchase);
        //then (сравнения)
        assertEquals(67.73, total, "the calculations are correct");
    }

    @Test
    @DisplayName(value = "Тест расчёта суммы покупки с некорректными данными - null")
    void countTotalSumNull() {
        Purchase purchase = null;
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> countTotalSum(purchase));
    }

    @Test
    @DisplayName(value = "Тест расчёта суммы покупки с некорректными данными - отрицательное количество товара")
    void countTotalSumWithWrongNumberOfGoods() {
        Purchase purchase = new Purchase(-5, 15.05, 10);
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> countTotalSum(purchase));
    }

    @Test
    @DisplayName(value = "Тест расчёта суммы покупки с некорректными данными - отрицательная цена товара")
    void countTotalSumWithWrongPrice() {
        Purchase purchase = new Purchase(5, -15.05, 10);
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> countTotalSum(purchase));
    }

    @Test
    @DisplayName(value = "Тест расчёта суммы покупки с некорректными данными - отрицательная скидка на товар")
    void countTotalSumWithDiscountLess0() {
        Purchase purchase = new Purchase(5, 15.05, -10);
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> countTotalSum(purchase));
    }

    @Test
    @DisplayName(value = "Тест расчёта суммы покупки с некорректными данными - скидка на товар более 100")
    void countTotalSumWithDiscountMore100() {
        Purchase purchase = new Purchase(5, 15.05, 1000);
        Class<IllegalArgumentException> exceptionClass = IllegalArgumentException.class;
        assertThrows(exceptionClass, () -> countTotalSum(purchase));
    }

    @ParameterizedTest
    @DisplayName(value = "Тест расчёта суммы покупки с корректными и некорректными данными")
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