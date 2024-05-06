package ru.mts.hw_3.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AnimalsHabitatId implements java.io.Serializable {
    private static final long serialVersionUID = -6907175441736297329L;
    @Column(name = "id_animal_type", nullable = false)
    private Integer idAnimalType;

    @Column(name = "id_area", nullable = false)
    private Integer idArea;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimalsHabitatId entity = (AnimalsHabitatId) o;
        return Objects.equals(this.idAnimalType, entity.idAnimalType) &&
                Objects.equals(this.idArea, entity.idArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimalType, idArea);
    }

}