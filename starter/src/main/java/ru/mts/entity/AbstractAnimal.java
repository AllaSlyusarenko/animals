package ru.mts.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.mts.config.SecretInformationDeserializer;
import ru.mts.config.SecretInformationSerializer;

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

public abstract class AbstractAnimal implements Animal, Serializable {
    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // день рождения животного
    @JsonSerialize(using = SecretInformationSerializer.class, as = String.class)
    @JsonDeserialize(using = SecretInformationDeserializer.class)
    protected String secretInformation; // секретная информация из файла

    public AbstractAnimal() {
    }

    public AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.CEILING);
        this.character = character;
        this.birthDate = birthDate;
        this.secretInformation = setSecretInformationFromFile();
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

    private String setSecretInformationFromFile() {
        Path path = Paths.get("src\\main\\resources\\secretStore\\secretInformation.txt");
//        Path path = Paths.get(getResourceFileAsString("secretInformation.txt"));
        String secretWord;
        try {
            List<String> words = Files.readAllLines(path);
            secretWord = words.get(new Random().nextInt(words.size()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return secretWord;
    }
//    protected String getResourceFileAsString(String fileName) {
//        var is = getResourceFileAsInputStream(fileName);
//        if (is != null) {
//            var reader = new BufferedReader(new InputStreamReader(is));
//            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
//        } else {
//            throw new RuntimeException("resource not found");
//        }
//    }
//
//    protected InputStream getResourceFileAsInputStream(String fileName) {
//        ClassLoader classLoader = AbstractAnimal.class.getClassLoader();
//        return classLoader.getResourceAsStream(fileName);
//    }

    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
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