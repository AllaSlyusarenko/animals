package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = "idProvider")
@Entity
@Table(name = "provider", schema = "animals")
public class Provider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProvider;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created")
    private LocalDate created;
    @Column(name = "updated")
    private LocalDate updated;

    public Provider(int idProvider, String name, String phone, LocalDate created, LocalDate updated) {
        this.idProvider = idProvider;
        this.name = name;
        this.phone = phone;
        this.created = created;
        this.updated = updated;
    }

    public Provider() {
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider=" + idProvider +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}