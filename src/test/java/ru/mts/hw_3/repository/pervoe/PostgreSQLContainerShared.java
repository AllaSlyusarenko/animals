package ru.mts.hw_3.repository.pervoe;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLContainerShared extends PostgreSQLContainer<PostgreSQLContainerShared> {
    public static final String IMAGE_VERSION = "postgres:11.1";
    public static PostgreSQLContainerShared postgreSQLContainerShared;


    public PostgreSQLContainerShared() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLContainerShared getInstance() {
        if (postgreSQLContainerShared == null) {
            postgreSQLContainerShared = new PostgreSQLContainerShared();
        }
        return postgreSQLContainerShared;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", postgreSQLContainerShared.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgreSQLContainerShared.getUsername());
        System.setProperty("DB_PASSWORD", postgreSQLContainerShared.getPassword());
    }

    @Override
    public void stop() {
    }
}