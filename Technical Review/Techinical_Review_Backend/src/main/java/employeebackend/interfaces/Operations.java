package employeebackend.interfaces;

import employeebackend.entity.Employee;
import employeebackend.exceptions.EmployeeExistException;
import employeebackend.exceptions.NoEmployeeFoundException;
import employeebackend.exceptions.ValidationException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Operations {
    String create(Employee employee) throws ValidationException, SQLException, EmployeeExistException;

    ArrayList<Employee> read() throws SQLException, NoEmployeeFoundException;

    ArrayList<Employee> read(Long employeeID);

    Object filterAddress(String parameter, Object value);

}
