package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = "idArea")
@Entity
@Table(name = "habitat", schema = "animals")
public class Habitat implements Serializable { //место обитания
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idArea;
    @Column(name = "area")
    private String area;
    @Column(name = "created")
    private LocalDate created;
    @Column(name = "updated")
    private LocalDate updated;

    public Habitat(int idArea, String area, LocalDate created, LocalDate updated) {
        this.idArea = idArea;
        this.area = area;
        this.created = created;
        this.updated = updated;
    }

    public Habitat() {

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