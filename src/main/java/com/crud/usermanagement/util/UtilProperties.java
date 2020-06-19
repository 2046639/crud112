package com.crud.usermanagement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UtilProperties {
    private static Properties prop;

    static {
        prop = new Properties();
        try (InputStream inputStream = UtilProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String propertyName) {
        return prop.getProperty(propertyName);
    }
}