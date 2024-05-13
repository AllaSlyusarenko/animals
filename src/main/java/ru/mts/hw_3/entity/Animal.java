package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(exclude = "idAnimal")
@Entity
@Table(name = "animal", schema = "animals")
public class Animal implements Serializable { //существо, животное
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_generator")
    @SequenceGenerator(name = "animal_generator", sequenceName = "animal_id_animal_sq", allocationSize = 1, initialValue = 1)
    private Integer idAnimal;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "type_id")
    @ManyToOne
    private AnimalType animalType;
    @Column(name = "age")
    private Integer age;
    @Column(name = "created")
    private OffsetDateTime created;
    @Column(name = "updated")
    private OffsetDateTime updated;
    @JoinColumn(name = "id_breed")
    @ManyToOne
    private Breed breed;
    @Column(name = "cost")
    private BigDecimal cost;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    public Animal() {
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", name='" + name + '\'' +
                ", animalType=" + animalType.getType() +
                ", age=" + age +
                ", created=" + created +
                ", updated=" + updated +
                ", breed=" + breed.getName() +
                ", cost=" + cost +
                ", birthDate=" + birthDate +
                '}';
    }
}