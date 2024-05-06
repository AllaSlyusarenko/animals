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

    private BigDecimal cost;
    private LocalDate birthDate;

    public Animal(int idAnimal, String name, AnimalType typeId, Integer age, OffsetDateTime created, OffsetDateTime updated) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.animalType = typeId;
        this.age = age;
        this.created = created;
        this.updated = updated;
    }

    public Animal() {
    }
}