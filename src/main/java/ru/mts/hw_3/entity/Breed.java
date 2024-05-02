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
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "created")
    private LocalDate created;
    @JoinColumn(name = "updated")
    private LocalDate updated;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_breed")
    private List<Creature> creatures;

    public Breed(int idBreed, String name, LocalDate created, LocalDate updated, List<Creature> creatures) {
        this.idBreed = idBreed;
        this.name = name;
        this.created = created;
        this.updated = updated;
        this.creatures = creatures;
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

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<Creature> creatures) {
        this.creatures = creatures;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Breed)) return false;

        Breed breed = (Breed) o;
        return Objects.equals(idBreed, breed.idBreed) && Objects.equals(name, breed.name) && Objects.equals(created, breed.created) && Objects.equals(updated, breed.updated) && Objects.equals(creatures, breed.creatures);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(idBreed);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        result = 31 * result + Objects.hashCode(creatures);
        return result;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "idBreed=" + idBreed +
                ", name='" + name + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", creatures=" + creatures +
                '}';
    }
}