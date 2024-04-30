package ru.mts.hw_3.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass( AHPK.class )
//@Table(name = "animals_habitats")
public class AnimalsHabitats implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    AnimalType animalType;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    Habitat habitat;

//    @Column(name = "id_animal_type")
//    private int idType;
//
//    @Column(name = "id_area")
//    private int idArea;
//
//    public AnimalsHabitats(int idType, int idArea) {
//        this.idType = idType;
//        this.idArea = idArea;
//    }
//
//    public AnimalsHabitats() {
//
//    }
//
//    public int getIdType() {
//        return idType;
//    }
//
//    public void setIdType(int idType) {
//        this.idType = idType;
//    }
//
//    public int getIdArea() {
//        return idArea;
//    }
//
//    public void setIdArea(int idArea) {
//        this.idArea = idArea;
//    }
//
//    @Override
//    public final boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof AnimalsHabitats)) return false;
//
//        AnimalsHabitats that = (AnimalsHabitats) o;
//        return idType == that.idType && idArea == that.idArea;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idType;
//        result = 31 * result + idArea;
//        return result;
//    }
}