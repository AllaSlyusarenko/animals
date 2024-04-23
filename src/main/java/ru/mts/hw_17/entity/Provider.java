package ru.mts.hw_17.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Provider {
    private int id_provider;
    private String name;
    private String phone;
    private LocalDate created;
    private LocalDate updated;
}
