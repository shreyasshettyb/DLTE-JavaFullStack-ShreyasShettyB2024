package employeebackend.service.exceptions;

public class NoEmployeeFoundException extends Exception {
    public NoEmployeeFoundException() {
        super("There were no Employee details found in the record");
    }
}
