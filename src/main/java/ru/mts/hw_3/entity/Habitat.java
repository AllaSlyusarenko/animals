package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(exclude = "idArea")
@Entity
@Table(name = "habitat", schema = "animals")
public class Habitat implements Serializable { //место обитания
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habitat_generator")
    @SequenceGenerator(name = "habitat_generator", sequenceName = "habitat_id_area_sq", allocationSize = 1, initialValue = 1)
    private Integer idArea;
    @Column(name = "area")
    private String area;
    @Column(name = "created")
    private OffsetDateTime created;
    @Column(name = "updated")
    private OffsetDateTime updated;

    public Habitat(int idArea, String area, OffsetDateTime created, OffsetDateTime updated) {
        this.idArea = idArea;
        this.area = area;
        this.created = created;
        this.updated = updated;
    }

    public Habitat() {

    }
}