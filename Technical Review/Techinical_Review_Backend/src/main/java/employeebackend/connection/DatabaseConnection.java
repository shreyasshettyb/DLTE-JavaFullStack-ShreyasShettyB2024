package employeebackend.connection;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {

    public Connection getDatabaseConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        Driver driver = new OracleDriver();
        DriverManager.registerDriver(driver);
        return DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
    }
}
