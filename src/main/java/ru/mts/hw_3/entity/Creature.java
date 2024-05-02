package ru.mts.hw_3.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "creature", schema = "animals")
public class Creature implements Serializable { //существо, животное
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "creature_generator")
    @SequenceGenerator(name="creature_generator", sequenceName = "creature_seq", allocationSize = 1, initialValue = 1)
    private int idCreature;
    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "type_id")
    @ManyToOne
    private AnimalType typeId;
    @JoinColumn(name = "age")
    private short age;
    @JoinColumn(name = "created")
    private LocalDate created;
    @JoinColumn(name = "updated")
    private LocalDate updated;
    @JoinColumn(name = "id_breed")
    @ManyToOne(cascade = CascadeType.ALL)
    private Breed idBreed;

    public Creature(int idCreature, String name, AnimalType typeId, short age, LocalDate created, LocalDate updated, Breed idBreed) {
        this.idCreature = idCreature;
        this.name = name;
        this.typeId = typeId;
        this.age = age;
        this.created = created;
        this.updated = updated;
        this.idBreed = idBreed;
    }

    public Creature() {

    }

    public Breed getIdBreed() {
        return idBreed;
    }

    public void setIdBreed(Breed idBreed) {
        this.idBreed = idBreed;
    }

    public int getIdCreature() {
        return idCreature;
    }

    public void setIdCreature(int idCreature) {
        this.idCreature = idCreature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalType getTypeId() {
        return typeId;
    }

    public void setTypeId(AnimalType typeId) {
        this.typeId = typeId;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creature)) return false;

        Creature creature = (Creature) o;
        return idCreature == creature.idCreature && typeId == creature.typeId && age == creature.age && idBreed == creature.idBreed && Objects.equals(name, creature.name) && Objects.equals(created, creature.created) && Objects.equals(updated, creature.updated);
    }

    @Override
    public int hashCode() {
        int result = idCreature;
        result = 31 * result + Objects.hashCode(name);
//        result = 31 * result + typeId;
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        return result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "idCreature=" + idCreature +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", age=" + age +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}