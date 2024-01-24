package com.example.model.properties;

import java.io.IOException;
import java.util.Properties;


public class AppProperty {
    private static final Properties properties = new Properties();
    public static Properties getProperties() {
        try {
            properties.load(AppProperty.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
