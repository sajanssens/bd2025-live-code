package com.infosupport.jpademo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private PropertiesReader() { }

    static Properties prop = new Properties();

    static { // static initializer block
        try (InputStream file = PropertiesReader.class.getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}

