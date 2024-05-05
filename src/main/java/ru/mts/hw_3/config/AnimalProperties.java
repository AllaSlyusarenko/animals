package ru.mts.hw_3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@ConfigurationProperties(prefix = "animal")
public class AnimalProperties {
    private String[] names;
    private String[] age;
    private String[] animalType;
    private String[] breed;
    private String[] isWild;
    private BigDecimal[] cost;
    private String[] dates;
}
