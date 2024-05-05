package ru.mts.hw_3.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public int getIdBreed() {
        return idBreed;
    }

    public void setIdBreed(int idBreed) {
        this.idBreed = idBreed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Breed)) return false;

        Breed breed = (Breed) o;
        return Objects.equals(idBreed, breed.idBreed) && Objects.equals(name, breed.name) && Objects.equals(created, breed.created) && Objects.equals(updated, breed.updated) && Objects.equals(animals, breed.animals);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(idBreed);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        result = 31 * result + Objects.hashCode(animals);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}