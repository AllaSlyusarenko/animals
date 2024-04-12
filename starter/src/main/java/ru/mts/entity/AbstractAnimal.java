package ru.mts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "animalType")
@JsonSubTypes({
        @JsonSubTypes.Type(name = "Dog", value = Dog.class),
        @JsonSubTypes.Type(name = "Wolf", value = Wolf.class),
        @JsonSubTypes.Type(name = "DOG", value = Dog.class),
        @JsonSubTypes.Type(name = "WOLF", value = Wolf.class)
})
public abstract class AbstractAnimal implements Animal, Serializable {
    protected String breed; // порода
    protected String name; // имя
    protected BigDecimal cost; // цена в магазине
    protected String character; // характер
    protected LocalDate birthDate; // день рождения животного
    @JsonSerialize(using = SecretInformationSerializer.class, as = String.class)
    @JsonDeserialize(using = SecretInformationDeserializer.class)
    protected String secretInformation; // секретная информация из файла
    protected AnimalType animalType; //тип животного

    public AbstractAnimal() {
    }

    public AbstractAnimal(AnimalType animalType, String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.animalType = animalType;
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.CEILING);
        this.character = character;
        this.birthDate = birthDate;
        this.secretInformation = setSecretInformationFromFile();
    }
    /**
     * Метод - определение типа животного
     */
    @JsonProperty("animalType")
    public AnimalType getAnimalType() {
        return animalType;
    }

    @JsonProperty("animalType")
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Метод - определение породы животного
     */
    @JsonProperty("breed")
    @Override
    public String getBreed() {
        return this.breed;
    }

    /**
     * Метод - определение имени животного
     */
    @Override
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    /**
     * Метод - определение цены животного с округлением до 2 знаков после запятой
     */
    @Override
    @JsonProperty("cost")
    public BigDecimal getCost() {
        return this.cost.setScale(2, RoundingMode.CEILING);
    }

    /**
     * Метод - определение характера животного
     */
    @Override
    @JsonProperty("character")
    public String getCharacter() {
        return this.character;
    }

    /**
     * Метод - определение даты рождения животного
     */
    @Override
    @JsonProperty("birthDate")
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Метод - для получения секретной информации животного
     */
    @JsonProperty("secretInformation")
    @Override
    @JsonSerialize(using = SecretInformationSerializer.class, as = String.class)
    @JsonDeserialize(using = SecretInformationDeserializer.class)
    public String getSecretInformation() {
        return this.secretInformation;
    }

    @JsonIgnore
    private String setSecretInformationFromFile() {
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

    @JsonProperty("secretInformation")
    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
    }

    @JsonProperty("breed")
    public void setBreed(String breed) {
        this.breed = breed;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("cost")
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @JsonProperty("character")
    public void setCharacter(String character) {
        this.character = character;
    }

    @JsonProperty("birthDate")
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