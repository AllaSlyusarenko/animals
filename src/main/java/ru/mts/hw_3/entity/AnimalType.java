package ru.mts.hw_3.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "animal_type")
public class AnimalType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idType;
    @JoinColumn(name = "type")
    private String type;
    @JoinColumn(name = "is_wild")
    private Boolean isWild;
    @JoinColumn(name = "created")
    private LocalDate created;
    @JoinColumn(name = "updated")
    private LocalDate updated;

    public AnimalType(int idType, String type, Boolean isWild, LocalDate created, LocalDate updated) {
        this.idType = idType;
        this.type = type;
        this.isWild = isWild;
        this.created = created;
        this.updated = updated;
    }

    public AnimalType() {

    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getWild() {
        return isWild;
    }

    public void setWild(Boolean wild) {
        isWild = wild;
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
        if (!(o instanceof AnimalType)) return false;

        AnimalType that = (AnimalType) o;
        return idType == that.idType && Objects.equals(type, that.type) && Objects.equals(isWild, that.isWild)
                && Objects.equals(created, that.created) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        int result = idType;
        result = 31 * result + Objects.hashCode(type);
        result = 31 * result + Objects.hashCode(isWild);
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        return result;
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                ", isWild=" + isWild +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}