package ru.mts.hw_3.entity;

import java.io.Serializable;
import java.util.Objects;

public class AHPK implements Serializable {

    private AnimalType animalType;
    private Habitat habitat;

    public AHPK(AnimalType animalType, Habitat habitat) {
        this.animalType = animalType;
        this.habitat = habitat;
    }

    public AHPK() {
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AHPK)) return false;

        AHPK ahpk = (AHPK) o;
        return Objects.equals(animalType, ahpk.animalType) && Objects.equals(habitat, ahpk.habitat);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(animalType);
        result = 31 * result + Objects.hashCode(habitat);
        return result;
    }
}