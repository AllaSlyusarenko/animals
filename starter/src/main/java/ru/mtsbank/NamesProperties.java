package ru.mtsbank;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("name")
public class NamesProperties {
    List<String> names;
}
