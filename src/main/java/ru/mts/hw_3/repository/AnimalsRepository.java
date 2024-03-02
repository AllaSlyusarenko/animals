package ru.mts.hw_3.repository;

import ru.mts.entity.Animal;

import java.time.LocalDate;
import java.util.Map;

/**
 * интерфейс, используемый в качестве хранилища создаваемых животных
 *
 * @version 1.1
 * @autor Алла Слюсаренко
 */

public interface AnimalsRepository {
    /**
     * Метод - ищет животных по дате рождения в високосный год
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Метод - ищет животных по возрасту, который больше, чем заданное значение
     */
    Map<Animal, Integer> findOlderAnimal(int N);

    /**
     * Метод - ищет животных по наличию дубликатов
     */
    Map<String, Integer> findDuplicate();

    /**
     * Метод - для печати дубликатов
     */
    void printDuplicate();
}