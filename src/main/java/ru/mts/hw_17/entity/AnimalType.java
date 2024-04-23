package ru.mts.hw_17.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalType {
    private int id_type;
    private String type;
    private Boolean is_wild;
    private LocalDate created;
    private LocalDate updated;
}