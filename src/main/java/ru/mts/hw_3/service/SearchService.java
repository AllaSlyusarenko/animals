package ru.mts.hw_3.service;

import ru.mts.hw_3.entity.Animal;

/**
 * интерфейс предназначен для поиска животных по:
 * - дате рождения в високосный год
 * - возрасту, который больше, чем заданное значение
 * - наличию дубликатов
 *
 * @version 1.1
 * @autor Алла Слюсаренко
 */
public interface SearchService {
    /**
     * Метод - ищет животных по дате рождения в високосный год
     */
    String[] findLeapYearNames(Animal[] animals);

    /**
     * Метод - ищет животных по возрасту, который больше, чем заданное значение
     */
    Animal[] findOlderAnimal(Animal[] animals, int N);

    /**
     * Метод - ищет животных по наличию дубликатов
     */
    Animal[] findDuplicate(Animal[] animals);
}