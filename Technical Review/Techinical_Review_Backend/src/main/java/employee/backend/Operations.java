package employee.backend;

import employee.entity.Employee;


import java.util.ArrayList;
import java.util.List;

public interface Operations {
    void create(Employee employee);
    ArrayList<Employee> read();
    Object read(Long employeeID);
    Object filterAddress(String parameter, Object value);

}
