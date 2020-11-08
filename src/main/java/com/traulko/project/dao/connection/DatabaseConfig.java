package com.traulko.project.dao.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Logger LOGGER = LogManager.getLogger(DatabaseConfig.class);
    private static final String FILE_NAME = "property/database.properties";
    private static final String DATABASE_DRIVER_NAME = "database.driverClassName";
    private static final String DATABASE_URL = "database.url";
    private static final String DATABASE_USERNAME = "database.username";
    private static final String DATABASE_PASSWORD = "database.password";
    private final String driverName;
    private final String url;
    private final String username;
    private final String password;

    DatabaseConfig() {
        Properties properties = new Properties();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Error with database properties file", e);
            throw new RuntimeException("Error with database properties file", e);
        }
        driverName = properties.getProperty(DATABASE_DRIVER_NAME);
        url = properties.getProperty(DATABASE_URL);
        username = properties.getProperty(DATABASE_USERNAME);
        password = properties.getProperty(DATABASE_PASSWORD);
    }

    public String getDriverName() {
        return driverName;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
