package ru.mts.hw_3.entity;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "animal", schema = "animals")
public class Animal implements Serializable { //существо, животное
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creature_generator")
    @SequenceGenerator(name = "creature_generator", sequenceName = "creature_seq", allocationSize = 1, initialValue = 1)
    private int idAnimal;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "type_id")
    @ManyToOne
    private AnimalType animalType;
    @Column(name = "age")
    private Integer age;
    @Column(name = "created")
    private LocalDate created;
    @Column(name = "updated")
    private LocalDate updated;
    @JoinColumn(name = "id_breed")
    @ManyToOne(cascade = CascadeType.ALL)
    private Breed breed;

    private BigDecimal cost;
    private LocalDate birthDate;

    public Animal(int idAnimal, String name, AnimalType typeId, Integer age, LocalDate created, LocalDate updated, Breed idBreed) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.animalType = typeId;
        this.age = age;
        this.created = created;
        this.updated = updated;
        this.breed = idBreed;
    }

    public Animal() {
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;
        return idAnimal == animal.idAnimal && age == animal.age && Objects.equals(name, animal.name) && Objects.equals(cost, animal.cost) && Objects.equals(animalType, animal.animalType) && Objects.equals(created, animal.created) && Objects.equals(updated, animal.updated) && Objects.equals(breed, animal.breed);
    }

    @Override
    public int hashCode() {
        int result = idAnimal;
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(cost);
        result = 31 * result + Objects.hashCode(animalType);
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        result = 31 * result + Objects.hashCode(breed);
        return result;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", typeId=" + animalType +
                ", age=" + age +
                ", created=" + created +
                ", updated=" + updated +
                ", breed=" + breed +
                '}';
    }
}