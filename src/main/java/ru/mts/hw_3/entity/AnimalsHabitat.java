package ru.mts.hw_3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "animals_habitats")
public class AnimalsHabitat {
    @EmbeddedId
    private AnimalsHabitatId id;

    @MapsId("idAnimalType")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_animal_type", nullable = false)
    private AnimalType idAnimalType;

    @MapsId("idArea")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_area", nullable = false)
    private Habitat idArea;

}