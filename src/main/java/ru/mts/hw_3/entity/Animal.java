package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = "idAnimal")
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

    private BigDecimal cost;
    private LocalDate birthDate;

    public Animal(int idAnimal, String name, AnimalType typeId, Integer age, LocalDate created, LocalDate updated) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.animalType = typeId;
        this.age = age;
        this.created = created;
        this.updated = updated;
    }

    public Animal() {
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
                '}';
    }
}