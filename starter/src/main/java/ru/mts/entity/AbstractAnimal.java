package ru.mts.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.mts.config.AnimalSerializer;
import ru.mts.utility.Constants;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

//@JsonSerialize(using = AnimalSerializer.class)
public abstract class AbstractAnimal implements Animal, Serializable {
    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине
    protected String character; // характер
//    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthDate; // день рождения животного
    @JsonSerialize(using = SecretInformationSerializer.class, as = String.class)
    protected String secretInformation; // секретная информация из файла

    public AbstractAnimal() {
    }

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

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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