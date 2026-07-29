package org.exmaple;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class DBTest {
    private final UserQueries userQueries = new UserQueries();
    private Connection connection;

    @BeforeMethod
    public void setUp() throws SQLException {
        connection = DataBaseConnection.getConnection();
    }

    @Test
    public void testSqlFeatures() throws SQLException {
        userQueries.printSqlFeatures(connection, 5);
    }

    @Test
    public void testTableNames() throws SQLException {
        userQueries.printTableNames(connection, 3);
    }

    @Test
    public void testImplementationNames() throws SQLException {
        userQueries.printImplementationNames(connection, null);
    }

    @Test
    public void testSizingNames() throws SQLException {
        userQueries.printSizingNames(connection,0);
    }

    @AfterMethod
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
