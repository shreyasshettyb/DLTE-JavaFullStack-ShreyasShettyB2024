package employeebackend.service.exceptions;

import java.sql.SQLException;

public class ConnectionException extends SQLException {
    public ConnectionException() {
        super("CON-001: System Failure");
    }
}
