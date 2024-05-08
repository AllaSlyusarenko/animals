package ru.mts.hw_3.repository.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;
import ru.mts.hw_3.config.ConfigPropertiesForDB;
import ru.mts.hw_3.service.ServiceFoDB;

@TestConfiguration
public class TestDatabaseConfig {
    private static final String POSTGRES_IMAGE = "postgres";
    private static final String INIT_DATABASE_FILE_PATH = "sql/create_schema_animals_antype_breed_animal.sql";

    @Bean(initMethod = "start", destroyMethod = "stop")
    PostgreSQLContainer<?> postgresContainer(DockerImageName dockerPostgres) {
        return new PostgreSQLContainer<>(dockerPostgres)
                .withDatabaseName("postgres")
                .withInitScript(INIT_DATABASE_FILE_PATH)
                .waitingFor(Wait.forListeningPort());
    }

    @Bean
    DockerImageName dockerPostgres() {
        return DockerImageName.parse(POSTGRES_IMAGE);
    }

    @Bean
    public ServiceFoDB serviceFoDB(PostgreSQLContainer postgresContainer){
        ConfigPropertiesForDB databaseProperties = new ConfigPropertiesForDB();
        databaseProperties.setHost(postgresContainer.getHost());
        databaseProperties.setPort(postgresContainer.getMappedPort(5432));
        databaseProperties.setUsername(postgresContainer.getUsername());
        databaseProperties.setPassword(postgresContainer.getPassword());
        databaseProperties.setUrl(postgresContainer.getJdbcUrl());
        return new ServiceFoDB(databaseProperties); }
}