package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection dbConnection = null;
    private final Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/SupplierSystem",
                "root",
                "1234");
    }

    public static DbConnection getInstance() throws ClassNotFoundException, SQLException {
        return (dbConnection == null) ? (dbConnection = new DbConnection()) : (dbConnection);

    }

    public Connection getConnection() {
        return connection;
    }
}
