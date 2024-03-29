package ru.mts.entity;

import ru.mts.utility.Constants;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class AbstractAnimal implements Animal {
    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // день рождения животного
    protected transient String secretInformation; // секретная информация из файла

    public AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.CEILING);
        this.character = character;
        this.birthDate = birthDate;
        this.secretInformation = setSecretInformation();
    }

    /**
     * Метод - определение породы животного
     */
    @Override
    public String getBreed() {
        return this.breed;
    }

    /**
     * Метод - определение имени животного
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Метод - определение цены животного с округлением до 2 знаков после запятой
     */
    @Override
    public BigDecimal getCost() {
        return this.cost.setScale(2, RoundingMode.CEILING);
    }

    /**
     * Метод - определение характера животного
     */
    @Override
    public String getCharacter() {
        return this.character;
    }

    /**
     * Метод - определение даты рождения животного
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Метод - вывод даты рождения животного в формате "dd-MM-yyyy"
     */
    public String getBirthDateString() {
        return getBirthDate().format(Constants.DATE_FORMATTER_OUT);
    }

    /**
     * Метод - для получения секретной информации животного
     */
    public String getSecretInformation() {
        return this.secretInformation;
    }

    private String setSecretInformation() {
        Path path = Paths.get("src\\main\\resources\\secretStore\\secretInformation.txt");
        String secretWord;
        try {
            List<String> words = Files.readAllLines(path);
            secretWord = words.get(new Random().nextInt(words.size()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return secretWord;
    }

    /**
     * Метод - для сравнения объектов класса
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost)
                && Objects.equals(character, that.character) && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(secretInformation, that.secretInformation);
    }

    /**
     * Метод - для генерации целочисленного кода объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate, secretInformation);
    }

    /**
     * Метод - возвращает строку, представляющую объект
     */
    @Override
    public String toString() {
        return "Animal {" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }
}