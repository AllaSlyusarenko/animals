package ru.mtsbank.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * интерфейс определяет поведение всех объектов "животные"
 *
 * @version 1.1
 * @autor Алла Слюсаренко
 */

public interface Animal {
    /**
     * Метод - определение породы животного
     *
     * @return breed - название породы
     * @see Animal#getBreed()
     */
    String getBreed();

    /**
     * Метод - определение имени животного
     *
     * @return name - имя животного
     * @see Animal#getName()
     */
    String getName();

    /**
     * Метод - определение цены животного
     *
     * @return cost - цену животного
     * @see Animal#getCost()
     */
    BigDecimal getCost();

    /**
     * Метод - определение характера животного
     *
     * @return character - характер животного
     * @see Animal#getCharacter()
     */
    String getCharacter();

    /**
     * Метод - определение даты рождения животного
     *
     * @return birthDate - день рождения животного
     * @see Animal#getBirthDate()
     */
    LocalDate getBirthDate();
}