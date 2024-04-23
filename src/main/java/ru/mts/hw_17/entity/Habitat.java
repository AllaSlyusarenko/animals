package ru.mts.hw_17.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Habitat {
    private int id_area;
    private String area;
    private LocalDate created;
    private LocalDate updated;
}
