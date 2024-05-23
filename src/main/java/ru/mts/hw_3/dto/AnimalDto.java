package ru.mts.hw_3.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mts.hw_3.entity.AnimalType;
import ru.mts.hw_3.entity.Breed;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
public class AnimalDto {
    private Integer idAnimal;
    private String name;
    private AnimalType animalType;
    private Integer age;
    private OffsetDateTime created;
    private OffsetDateTime updated;
    private Breed breed;
    private BigDecimal cost;
    private LocalDate birthDate;
}