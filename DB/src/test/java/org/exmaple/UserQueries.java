package org.exmaple;

import java.sql.*;

public class UserQueries {

    public void printSqlFeatures(Connection connection, int limit) throws SQLException {
        String query = "SELECT * FROM information_schema.sql_features LIMIT " + limit;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getString("feature_name"));
            }
        }
    }

    public void printTableNames(Connection connection, int limit) throws SQLException {
        String query = "SELECT table_name FROM information_schema.tables LIMIT " + limit;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getString("table_name"));
            }
        }
    }

    public void printImplementationNames(Connection connection, Integer integerValue) throws SQLException {
        String newQuery = (integerValue == null)
                ? "SELECT implementation_info_name FROM information_schema.sql_implementation_info WHERE integer_value IS NULL"
                : "SELECT implementation_info_name FROM information_schema.sql_implementation_info WHERE integer_value = ?";
        try (PreparedStatement pstmtt = connection.prepareStatement(newQuery)) {
            if (integerValue != null) {
                pstmtt.setInt(1, integerValue);
            }

            try (ResultSet rs = pstmtt.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("implementation_info_name"));
                }
            }
        }
    }

    public void printSizingNames(Connection connection, int supportedValue) throws SQLException {
        String query = "SELECT sizing_id, sizing_name FROM information_schema.sql_sizing WHERE supported_value > " + supportedValue;
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1) + " | Название: " + rs.getString(2));
            }
        }
    }
}
