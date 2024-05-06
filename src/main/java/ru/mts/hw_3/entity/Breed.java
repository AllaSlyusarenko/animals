package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(exclude = "idBreed")
@Entity
@Table(name = "breed", schema = "animals")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBreed;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private LocalDate created;
    @Column(name = "updated")
    private LocalDate updated;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_breed")
    private List<Animal> animals;

    public Breed(int idBreed, String name, LocalDate created, LocalDate updated, List<Animal> animals) {
        this.idBreed = idBreed;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.animals = animals;
    }

    public Breed() {
    }

    @Override
    public String toString() {
        return name;
    }
}