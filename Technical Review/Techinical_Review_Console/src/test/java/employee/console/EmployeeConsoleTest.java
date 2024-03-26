package employee.console;

import employee.entity.Address;
import employee.validation.Validation;
import employeebackend.connection.DatabaseConnection;
import employeebackend.entity.Employee;
import employeebackend.exceptions.EmployeeExistException;
import employeebackend.exceptions.NoEmployeeFoundException;
import employeebackend.exceptions.ValidationException;
import employeebackend.interfaces.Operations;
import employeebackend.repository.DataBaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//import employee.entity.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeConsoleTest {
    @Mock
    private DataBaseRepository mockDatabaseRepository;
    @Mock
    private DatabaseConnection mockDatabaseConnection;
    @Mock
    private Connection connection;
    @Mock
    private Validation validationFrontend;

    private employeebackend.validation.Validation validationBackend;

    private Operations operations;

    @Before
    public void prepareStore() throws SQLException {
        when(mockDatabaseConnection.getDatabaseConnection()).thenReturn(connection);
        operations = mockDatabaseRepository;
    }

    @Test
    public void testCreate() throws EmployeeExistException, SQLException, ValidationException {
        Employee employee1 = new Employee("Rakesh", "Shetty",null, 1234567890L, "rakesh@email.com", 1001L, null, null);
        Employee employee2 = new Employee("Varun", "Raj", null, 7418596301L, "varun@email.com", 1010L, null, null);

        when((mockDatabaseRepository).create(employee1)).thenReturn("SQL-000");

        String output=operations.create(employee1);

        assertEquals("SQL-000",output);

    }

    @Test
    public void testDisplay() throws EmployeeExistException, SQLException, ValidationException, NoEmployeeFoundException {
        Employee employee1 = new Employee("Rakesh", "Shetty",null, 1234567890L, "rakesh@email.com", 1001L, null, null);
        Employee employee2 = new Employee("Varun", "Raj", null, 7418596301L, "varun@email.com", 1010L, null, null);

        when((mockDatabaseRepository).read()).thenReturn((ArrayList<Employee>) Stream.of(employee1,employee2).collect(Collectors.toList()));

       ArrayList<Employee> employees= operations.read();

        assertEquals(employees,Stream.of(employee1,employee2).collect(Collectors.toList()));

    }

    @Test
    public void testValidationFrontend() throws EmployeeExistException, SQLException, ValidationException {
        employee.entity.Employee employee1 = new employee.entity.Employee("Rakesh", "Shetty","", 1234567890L, "rakesh@email.com", 1001L,new Address(1001L,"qu","k","j","k",741852),new Address(1001L,"qu","k","j","k",741852));
        Employee employee2 = new Employee("Varun", "Raj", null, 7418596301L, "varun@email.com", 1010L, null, null);

        when((validationFrontend).validateEmployee(employee1)).thenReturn(employee1);

        employee.entity.Employee output=validationFrontend.validateEmployee(employee1);

        assertEquals(employee1,output);

    }


}