package ru.mts.hw_3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class ConfigPropertiesForDB {
    private String url;
    private String username;
    private String password;
    private String host;
    private int port;
}