package ru.mts.hw_3.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "habitat", schema = "animals")
public class Habitat implements Serializable { //место обитания
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idArea;
    @JoinColumn(name = "area")
    private String area;
    @JoinColumn(name = "created")
    private LocalDate created;
    @JoinColumn(name = "updated")
    private LocalDate updated;

    public Habitat(int idArea, String area, LocalDate created, LocalDate updated) {
        this.idArea = idArea;
        this.area = area;
        this.created = created;
        this.updated = updated;
    }

    public Habitat() {

    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
        if (!(o instanceof Habitat)) return false;

        Habitat habitat = (Habitat) o;
        return idArea == habitat.idArea && Objects.equals(area, habitat.area) && Objects.equals(created, habitat.created)
                && Objects.equals(updated, habitat.updated);
    }

    @Override
    public int hashCode() {
        int result = idArea;
        result = 31 * result + Objects.hashCode(area);
        result = 31 * result + Objects.hashCode(created);
        result = 31 * result + Objects.hashCode(updated);
        return result;
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "idArea=" + idArea +
                ", area='" + area + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}