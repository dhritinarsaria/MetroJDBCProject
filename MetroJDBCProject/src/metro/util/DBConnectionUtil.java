package metro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import metro.exceptions.DatabaseConnectionException;

public class DBConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/javatraining";  // change db name if needed
    private static final String USER = "root";  // replace with your DB username
    private static final String PASSWORD = "admin"; // replace with your DB password
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    // Private constructor to prevent instantiation
    private DBConnectionUtil() {}

    public static Connection getConnection() throws DatabaseConnectionException {
        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionException("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to establish DB connection: " + e.getMessage());
        }
    }
}


