package ru.mts.hw_3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AnimalsProviderId implements java.io.Serializable {
    private static final long serialVersionUID = -8036476692494338476L;
    @Column(name = "id_animal_type", nullable = false)
    private Integer idAnimalType;

    @Column(name = "id_provider", nullable = false)
    private Integer idProvider;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimalsProviderId entity = (AnimalsProviderId) o;
        return Objects.equals(this.idAnimalType, entity.idAnimalType) &&
                Objects.equals(this.idProvider, entity.idProvider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimalType, idProvider);
    }

}