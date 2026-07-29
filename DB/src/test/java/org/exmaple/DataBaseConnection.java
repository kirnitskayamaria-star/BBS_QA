package org.exmaple;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://ep-falling-breeze-ay6xfwb2.c-5.us-east-2.aws.neon.tech/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD;

    static {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            PASSWORD = properties.getProperty("db.password");

            if (PASSWORD == null) {
                throw new IllegalStateException("В файле config.properties не найден ключ db.password!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить файл config.properties.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
