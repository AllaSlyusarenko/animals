package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "idBreed")
@Entity
@Table(name = "breed", schema = "animals")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breed_generator")
    @SequenceGenerator(name = "breed_generator", sequenceName = "breed_id_breed_sq", allocationSize = 1, initialValue = 1)
    private Integer idBreed;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private OffsetDateTime created;
    @Column(name = "updated")
    private OffsetDateTime updated;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_breed")
    private List<Animal> animals;

    public Breed() {
    }

    @Override
    public String toString() {
        return name;
    }
}