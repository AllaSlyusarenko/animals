package ru.mts.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    /**
     * Метод - возвращает строку, представляющую объект Wolf(Волк)
     */
    @Override
    public String toString() {
        return "Wolf {" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                ", secretInformation='" + secretInformation + '\'' +
                '}';
    }
}