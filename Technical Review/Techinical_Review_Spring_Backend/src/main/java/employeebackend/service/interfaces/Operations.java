package employeebackend.service.interfaces;


import employeebackend.service.entity.Employee;
import employeebackend.service.exceptions.EmployeeExistException;
import employeebackend.service.exceptions.NoEmployeeFoundException;
import employeebackend.service.exceptions.ValidationException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface Operations {
    String create(Employee employee) throws ValidationException, SQLException, EmployeeExistException;

    List<Employee> read() throws SQLException, NoEmployeeFoundException;

    List<Employee> read(Long employeeID);

    List<Employee>filterByPincode(Integer pincode) throws SQLException,NoEmployeeFoundException;

}
