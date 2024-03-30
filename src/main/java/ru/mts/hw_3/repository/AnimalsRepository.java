package ru.mts.hw_3.repository;

import ru.mts.entity.AbstractAnimal;
import ru.mts.hw_3.exception.CollectionEmptyException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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
    Map<String, LocalDate> findLeapYearNames() throws IOException;

    /**
     * Метод - ищет животных по возрасту, который больше, чем заданное значение
     */
    Map<AbstractAnimal, Integer> findOlderAnimal(int N) throws IOException;

    /**
     * Метод - ищет животных по наличию дубликатов
     */
    Map<String, List<AbstractAnimal>> findDuplicate();

    /**
     * Метод - для печати дубликатов
     */
    void printDuplicate();

    /**
     * Метод - для нахождения среднего возраста животных в списке
     */
    void findAverageAge(List<AbstractAnimal> animalList) throws CollectionEmptyException;

    /**
     * Метод - для нахождения животных в списке старше 5 лет и стоимость больше средней стоимости,
     * отсортированный по дате рождения(по возрастанию)
     */
    List<AbstractAnimal> findOldAndExpensive(List<AbstractAnimal> animalList) throws CollectionEmptyException;

    /**
     * Метод - для нахождения 3 животных с самой низкой ценой, вывод - список имен в обратном порядке
     */
    List<String> findMinConstAnimals(List<AbstractAnimal> animalList) throws CollectionEmptyException;
}