package ru.mts.hw_17.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Creature {
    private int id_creature;
    private String name;
    private int type_id;
    private short age;
    private LocalDate created;
    private LocalDate updated;
}