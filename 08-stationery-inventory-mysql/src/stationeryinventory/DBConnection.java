package stationeryinventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates database connections. The connection details are read from environment
 * variables, so no password is stored in the source code:
 *   DB_URL      (default: jdbc:mysql://localhost:3306/stationery_db)
 *   DB_USER     (default: root)
 *   DB_PASSWORD (default: empty)
 */
public class DBConnection {
    private static final String URL = getEnv("DB_URL", "jdbc:mysql://localhost:3306/stationery_db");
    private static final String USER = getEnv("DB_USER", "root");
    private static final String PASSWORD = getEnv("DB_PASSWORD", "");

    private static String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isEmpty()) ? defaultValue : value;
    }

    /** Opens a new connection. The caller must close it (use try-with-resources). */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Not fatal: modern JDBC drivers register themselves automatically.
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
