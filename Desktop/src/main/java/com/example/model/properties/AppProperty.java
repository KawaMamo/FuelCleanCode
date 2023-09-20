package com.example.model.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperty {
    private static final Properties properties = new Properties();
    public static Properties getProperties() {
        try {
            properties.load(new FileInputStream("Desktop/src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
