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

    public Provider() {
    }
}