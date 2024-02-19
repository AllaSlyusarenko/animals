package ru.mts.hw_3.repository;

import ru.mts.entity.Animal;

import java.util.Set;

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
    String[] findLeapYearNames();

    /**
     * Метод - ищет животных по возрасту, который больше, чем заданное значение
     */
    Animal[] findOlderAnimal(int N);

    /**
     * Метод - ищет животных по наличию дубликатов
     */
    Set<Animal> findDuplicate();

    /**
     * Метод - для печати дубликатов
     */
    void printDuplicate();

    /**
     * Метод - для внешнего списка животных
     */
    void setAnimals(Animal[] animals);
}