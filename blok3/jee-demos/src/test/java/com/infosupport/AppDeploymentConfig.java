package com.infosupport;

import org.microshed.testing.SharedContainerConfiguration;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;

import java.time.Duration;

public class AppDeploymentConfig implements SharedContainerConfiguration {

    private static final Network network = Network.newNetwork();
    private static final String dbName = "people-db";
    private static final String dbHost = "mysql-container-it";
    private static final int dbPort = 3306;

    @Container
    public static MySQLContainer<?> mysql;

    static {
        mysql = new MySQLContainer<>("mysql:latest")
                .withNetwork(network)
                .withNetworkAliases(dbHost)
                .withDatabaseName(dbName)
                .withUsername("root")
                .withPassword("root")
                .withStartupTimeout(Duration.ofMinutes(1));
    }

    // create docker container based on the Dockerfile
    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withNetwork(network)
            .withAppContextRoot("/beers")
            .withExposedPorts(9080)
            .withEnv("MYSQL_HOSTNAME", dbHost)
            .withEnv("MYSQL_PORT", dbPort + "")
            .withEnv("MYSQL_DATABASE", dbName)
            .withEnv("MYSQL_ROOT_PASSWORD", "root")
            .withReadinessPath("/health/ready")
            .withStartupTimeout(Duration.ofMinutes(3))
            .dependsOn(mysql);
}
