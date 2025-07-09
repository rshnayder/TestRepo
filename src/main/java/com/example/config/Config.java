package com.example.config;

import java.io.*;
import java.util.Properties;

import com.example.exceptions.PropertiesNotLoadedException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

public class Config {

    private static final String CONFIG_FILE = "src/main/resources/properties/config.properties";
    private static final Properties properties = new Properties();
    private static final String BASE_URL = getProperty("base.url");
    private static final String THREAD_COUNT = getProperty("thread.count");


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getThreadCount() {
        return THREAD_COUNT;
    }

    public static String getProperty(String propertyKey) {
        String systemProperty = System.getProperty(propertyKey);
        return StringUtils.isNotBlank(systemProperty) ? systemProperty : properties.getProperty(propertyKey);
    }

    static {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (FileNotFoundException | NullPointerException e) {
            throw new PropertiesNotLoadedException(
                    CONFIG_FILE + " resource is not found, please check that the file exists");
        } catch (final IOException e) {
            throw new PropertiesNotLoadedException("Failed to load configuration file: ".concat(CONFIG_FILE), e);
        }
    }


}
