package employeebackend.repository;

import employeebackend.connection.DatabaseConnection;
import employeebackend.entity.Address;
import employeebackend.entity.Employee;
import employeebackend.exceptions.ConnectionException;
import employeebackend.exceptions.EmployeeExistException;
import employeebackend.exceptions.NoEmployeeFoundException;
import employeebackend.exceptions.ValidationException;
import employeebackend.interfaces.Operations;
import employeebackend.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import employee.middleware.remote.Operations;

public class DataBaseRepository implements Operations {
    private Connection connection;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private Logger logger = LoggerFactory.getLogger(DataBaseRepository.class);

    private Validation validation = new Validation();

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DataBaseRepository() throws ConnectionException {
        try {
            connection = new DatabaseConnection().getDatabaseConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new ConnectionException();
        }
    }

    @Override
    public String create(Employee employee) throws ValidationException, SQLException, EmployeeExistException {
        validation.validateEmployee(employee);
        try {
            String query;
            query = "insert into employee_personal values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getMiddleName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setLong(4, employee.getPhone());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setLong(6, employee.getEmployeeID());

            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                logger.error("SQL-001");
                return "SQL-001";
            }
            String query1;
            query1 = "insert into EMPLOYEE_ADDRESS values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setLong(1, employee.getEmployeeID());
            preparedStatement.setString(2, employee.getPermanentAddress().getHouseName());
            preparedStatement.setString(3, employee.getPermanentAddress().getStreetName());
            preparedStatement.setString(4, employee.getPermanentAddress().getCity());
            preparedStatement.setString(5, employee.getPermanentAddress().getState());
            preparedStatement.setInt(6, employee.getPermanentAddress().getPincode());
            preparedStatement.setString(7,employee.getPermanentAddress().getType());
            result = preparedStatement.executeUpdate();
            if (result == 0) {
                logger.error("SQL-002");
                return "SQL-002";
            }
            String query2;
            query2 = "insert into EMPLOYEE_ADDRESS values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setLong(1, employee.getTemporaryAddress().getEmployeeID());
            preparedStatement.setString(2, employee.getTemporaryAddress().getHouseName());
            preparedStatement.setString(3, employee.getTemporaryAddress().getStreetName());
            preparedStatement.setString(4, employee.getTemporaryAddress().getCity());
            preparedStatement.setString(5, employee.getTemporaryAddress().getState());
            preparedStatement.setInt(6, employee.getTemporaryAddress().getPincode());
            preparedStatement.setString(7,employee.getTemporaryAddress().getType());
            result = preparedStatement.executeUpdate();
            if (result == 0) {
                logger.error("SQL-003");
                return "SQL-003";
            }
            connection.close();
            return "SQL-000";
        } catch (SQLIntegrityConstraintViolationException integrityViolation) {
            logger.warn("EMP-001: Employee ID Already Exist");
            throw new EmployeeExistException("EMP-001: Employee ID Already Exist");
        }

    }

    @Override
    public ArrayList<Employee> read() throws SQLException, NoEmployeeFoundException {
        ArrayList<Employee> list = new ArrayList<>();
        try {
            String query = "SELECT ep.firstname,ep.middlename,ep.lastname,ep.phone,ep.email,ep.employee_id,ea.house_name,ea.street_name,ea.city,ea.state,ea.pincode FROM employee_personal ep JOIN employee_address ea ON ep.employee_id = ea.employee_id WHERE ea.type IN ('permanent', 'temporary') ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                list.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6),
//                        new Address(resultSet.getLong(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12)),
//                        new Address(resultSet.getLong(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getString(17), resultSet.getInt(18))));
                // Create Address objects for permanent and temporary addresses
                Employee employee = new Employee();
                employee.setFirstName(resultSet.getString(1));
                employee.setMiddleName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setPhone(resultSet.getLong(4));
                employee.setEmail(resultSet.getString(5));
                employee.setEmployeeID(resultSet.getLong(6));

                Address permanentAddress = new Address();
                permanentAddress.setEmployeeID(resultSet.getLong(6));
                permanentAddress.setHouseName(resultSet.getString(7));
                permanentAddress.setStreetName(resultSet.getString(8));
                permanentAddress.setCity(resultSet.getString(9));
                permanentAddress.setState(resultSet.getString(10));
                permanentAddress.setPincode(resultSet.getInt(11));
                Address temporaryAddress = new Address();
                if(resultSet.next()) {
                    temporaryAddress.setEmployeeID(resultSet.getLong(6));
                    temporaryAddress.setHouseName(resultSet.getString(7));
                    temporaryAddress.setStreetName(resultSet.getString(8));
                    temporaryAddress.setCity(resultSet.getString(9));
                    temporaryAddress.setState(resultSet.getString(10));
                    temporaryAddress.setPincode(resultSet.getInt(11));
                }

                employee.setPermanentAddress(permanentAddress);
                employee.setTemporaryAddress(temporaryAddress);

                list.add(employee);

            }
            if (list.size() == 0) {
                throw new NoEmployeeFoundException();
            }
            connection.close();
        } catch (SQLException e) {
            throw new SQLException();
        }
        return list;

    }


    @Override
    public ArrayList<Employee> read(Long aLong) {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Employee>filterByPincode(Integer pincode) throws SQLException,NoEmployeeFoundException {
        ArrayList<Employee> list = new ArrayList<>();
        try {
            String query = "select * from employee_personal join permanent_address on employee_personal.EMPLOYEEID=permanent_address.EMPLOYEEID AND permanent_address.pincode=? Join temporary_address on employee_personal.EMPLOYEEID=temporary_address.EMPLOYEEID ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,pincode);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                list.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6),
//                        new Address(resultSet.getLong(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12)),
//                        new Address(resultSet.getLong(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getString(17), resultSet.getInt(18))));
                // Create Address objects for permanent and temporary addresses
                Address permanentAddress = new Address();
                permanentAddress.setEmployeeID(resultSet.getLong(7));
                permanentAddress.setHouseName(resultSet.getString(8));
                permanentAddress.setStreetName(resultSet.getString(9));
                permanentAddress.setCity(resultSet.getString(10));
                permanentAddress.setState(resultSet.getString(11));
                permanentAddress.setPincode(resultSet.getInt(12));

                Address temporaryAddress = new Address();
                temporaryAddress.setEmployeeID(resultSet.getLong(13));
                temporaryAddress.setHouseName(resultSet.getString(14));
                temporaryAddress.setStreetName(resultSet.getString(15));
                temporaryAddress.setCity(resultSet.getString(16));
                temporaryAddress.setState(resultSet.getString(17));
                temporaryAddress.setPincode(resultSet.getInt(18));

                Employee employee = new Employee();
                employee.setFirstName(resultSet.getString(1));
                employee.setMiddleName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setPhone(resultSet.getLong(4));
                employee.setEmail(resultSet.getString(5));
                employee.setEmployeeID(resultSet.getLong(6));
                employee.setPermanentAddress(permanentAddress);
                employee.setTemporaryAddress(temporaryAddress);

                list.add(employee);

            }
            if (list.size() == 0) {
                throw new NoEmployeeFoundException();
            }
            connection.close();
        } catch (SQLException  e) {
            throw new SQLException();
        }
        return list;
    }


}
