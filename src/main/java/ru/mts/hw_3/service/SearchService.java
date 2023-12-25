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
    String[] findLeapYearNames(Animal[] animals);

    Animal[] findOlderAnimal(Animal[] animals, int N);

    Animal[] findDuplicate(Animal[] animals);
}