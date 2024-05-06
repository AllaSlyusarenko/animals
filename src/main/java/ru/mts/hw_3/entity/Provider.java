package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(exclude = "idProvider")
@Entity
@Table(name = "provider", schema = "animals")
public class Provider implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "provider_generator")
    @SequenceGenerator(name = "provider_generator", sequenceName = "provider_id_provider_sq", allocationSize = 1, initialValue = 1)
    private Integer idProvider;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "created")
    private OffsetDateTime created;
    @Column(name = "updated")
    private OffsetDateTime updated;

    public Provider(int idProvider, String name, String phone, OffsetDateTime created, OffsetDateTime updated) {
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