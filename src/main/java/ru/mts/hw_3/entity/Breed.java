package ru.mts.hw_3.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idBreed;
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "created")
    private LocalDate created;
    @JoinColumn(name = "updated")
    private LocalDate updated;

    @OneToMany
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
}
