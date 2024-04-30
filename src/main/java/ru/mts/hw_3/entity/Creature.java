package ru.mts.hw_3.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Creature {
    private int idCreature;
    private String name;
    private int typeId;
    private short age;
    private LocalDate created;
    private LocalDate updated;

    public Creature(int idCreature, String name, int typeId, short age, LocalDate created, LocalDate updated) {
        this.idCreature = idCreature;
        this.name = name;
        this.typeId = typeId;
        this.age = age;
        this.created = created;
        this.updated = updated;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
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
        return idCreature == creature.idCreature && typeId == creature.typeId && age == creature.age
                && Objects.equals(name, creature.name) && Objects.equals(created, creature.created)
                && Objects.equals(updated, creature.updated);
    }

    @Override
    public int hashCode() {
        int result = idCreature;
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + typeId;
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