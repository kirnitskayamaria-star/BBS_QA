package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {
    public static final String BASE_URL = "https://line.pr-cy.ru/api/v1.1.0";
    public static final String API_KEY;

    static {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            API_KEY = properties.getProperty("api.key");

            if (API_KEY == null) {
                throw new IllegalStateException("В файле config.properties не найден ключ api.key!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл config.properties.", e);
        }
    }
}
