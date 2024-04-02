//package employee.console;
//
//import employee.entity.Address;
//import employee.validation.Validation;
////import employeebackend.connection.DatabaseConnection;
////import employeebackend.entity.Employee;
////import employeebackend.exceptions.EmployeeExistException;
////import employeebackend.exceptions.NoEmployeeFoundException;
////import employeebackend.exceptions.ValidationException;
////import employeebackend.interfaces.Operations;
////import employeebackend.repository.DataBaseRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//
////import employee.entity.Employee;
//
//@RunWith(MockitoJUnitRunner.class)
//public class EmployeeConsoleTest {
//    @Mock
//    private DataBaseRepository mockDatabaseRepository;
//    @Mock
//    private DatabaseConnection mockDatabaseConnection;
//    @Mock
//    private Connection connection;
//
//    private Validation validationFrontend = new Validation();
//
//    private employeebackend.validation.Validation validationBackend = new employeebackend.validation.Validation();
//
//    private Operations operations;
//
////    private EmployeeConsole employeeConsole;
//
//    @Before
//    public void prepareStore() throws SQLException {
//        when(mockDatabaseConnection.getDatabaseConnection()).thenReturn(connection);
//        operations = mockDatabaseRepository;
//    }
//
//    @Test
//    public void testCreate() throws EmployeeExistException, SQLException, ValidationException {
//        Employee employee1 = new Employee("Rakesh", "Shetty", null, 1234567890L, "rakesh@email.com", 1001L, null, null);
//        Employee employee2 = new Employee("Varun", "Raj", null, 7418596301L, "varun@email.com", 1010L, null, null);
//
//        when((mockDatabaseRepository).create(employee1)).thenReturn("SQL-000");
//
//        String output = operations.create(employee1);
//
//        assertEquals("SQL-000", output);
//
//    }
//
//    @Test
//    public void testDisplay() throws SQLException, NoEmployeeFoundException {
//        Employee employee1 = new Employee("Rakesh", "Shetty", null, 1234567890L, "rakesh@email.com", 1001L, null, null);
//        Employee employee2 = new Employee("Varun", "Raj", null, 7418596301L, "varun@email.com", 1010L, null, null);
//
//        when((mockDatabaseRepository).read()).thenReturn((ArrayList<Employee>) Stream.of(employee1, employee2).collect(Collectors.toList()));
//
//        ArrayList<Employee> employees = operations.read();
//
//        assertEquals(employees, Stream.of(employee1, employee2).collect(Collectors.toList()));
//
//    }
//
//    @Test
//    public void testFilterByPincode() throws SQLException, NoEmployeeFoundException {
//        Employee employee1 = new Employee("rakesh", "Shetty", "k", 1234567890L, "rakesh@email.com", 1001L, new employeebackend.entity.Address(1001L, "qu", "k", "j", "k", 741852,"permanent"), new employeebackend.entity.Address(1001L, "qu", "k", "j", "k", 741852,"temporary"));
//        Employee employee2 = new Employee("varun", "raj", "k", 1234567890L, "rakesh@email.com", 1001L, new employeebackend.entity.Address(1001L, "qu", "k", "j", "k", 564102,"permanent"), new employeebackend.entity.Address(1001L, "qu", "k", "j", "k", 741852,"temporary"));
//
//        when((mockDatabaseRepository).filterByPincode(anyInt())).thenReturn((ArrayList<Employee>) Stream.of(employee2).collect(Collectors.toList()));
//
//        ArrayList<Employee> employees = operations.filterByPincode(anyInt());
//
//        assertEquals(employee2.getPermanentAddress().getPincode(),employees.get(0).getPermanentAddress().getPincode() );
//
//    }
//
//    @Test
//    public void testValidationFrontend() throws SQLException {
//        employee.entity.Employee employee1 = new employee.entity.Employee("Rakesh", "Shetty", "k", 1234567890L, "rakesh@email.com", 1001L, new Address(1001L, "qu", "k", "j", "k", 741852,"permanent"), new Address(1001L, "qu", "k", "j", "k", 741852,"temporary"));
////        Employee employee2 = new Employee("Varun", "Raj", null, 7418596301L, "varun@email.com", 1010L, null, null);
//
//        employee.entity.Employee output = validationFrontend.validateEmployee(employee1);
//
//        assertEquals(employee1, output);
//
//    }
//
//    @Test(expected = ValidationException.class)
//    public void testValidationBackend() throws ValidationException {
//        employeebackend.entity.Employee employee1 = new employeebackend.entity.Employee("Rakesh", "Shetty", "k", 1234567890L, "rakesh@email.com", 1001L, new employeebackend.entity.Address(1001L, "qu", "k", "j", "k", 741852,"permanent"), new employeebackend.entity.Address(1001L, "qu", "k", "j", "k", 741852,"temporary"));
////        Employee employee2 = new Employee("Varun", "Raj", null, 7418596301L, "varun@email.com", 1010L, null, null);
//
//        validationBackend.validateEmployee(employee1);
//
//    }
//
//
//}