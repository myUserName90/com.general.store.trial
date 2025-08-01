package com.general.store.trial.configuration;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for reading configuration properties from files or system properties.
 */
@Log4j2
public final class ConfigReader {

    // Private constructor to prevent instantiation
    private ConfigReader() {}

    /**
     * Loads properties from the specified file path.
     *
     * @param path path to the properties file
     * @return loaded Properties object
     */
    public static Properties readFile(String path) {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream(path)) {
            properties.load(file);
            log.info("Successfully loaded configuration from {}", path);
        } catch (IOException e) {
            log.error("Failed to load configuration from {}: {}", path, e.getMessage());
        }
        return properties;
    }

    /**
     * Retrieves a system property by key, returning a default value if not present or empty.
     *
     * @param key          system property key
     * @param defaultValue value to return if property is not found or empty
     * @return system property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        String value = System.getProperty(key);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }
}
