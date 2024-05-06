package ru.mts.hw_3.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(exclude = "idType")
@Entity
@Table(name = "animal_type", schema = "animals")
public class AnimalType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_type_generator")
    @SequenceGenerator(name = "animal_type_generator", sequenceName = "animal_type_id_type_sq", allocationSize = 1, initialValue = 1)
    private Integer idType;
    @Column(name = "type")
    private String type;
    @Column(name = "is_wild")
    private Boolean isWild;
    @Column(name = "created")
    private OffsetDateTime created;
    @Column(name = "updated")
    private OffsetDateTime updated;


    public AnimalType(int idType, String type, Boolean isWild, OffsetDateTime created, OffsetDateTime updated) {
        this.idType = idType;
        this.type = type;
        this.isWild = isWild;
        this.created = created;
        this.updated = updated;
    }

    public AnimalType() {
    }


    @Override
    public String toString() {
        return type;
    }
}