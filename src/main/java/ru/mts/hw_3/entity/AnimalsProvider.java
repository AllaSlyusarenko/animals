package ru.mts.hw_3.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "animals_providers", schema = "animals")
public class AnimalsProvider {
    @EmbeddedId
    private AnimalsProviderId id;

    @MapsId("idAnimalType")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_animal_type", nullable = false)
    private AnimalType idAnimalType;

    @MapsId("idProvider")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_provider", nullable = false)
    private Provider idProvider;

}