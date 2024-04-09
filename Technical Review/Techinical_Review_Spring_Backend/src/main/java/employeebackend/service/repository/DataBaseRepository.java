package employeebackend.service.repository;

import employeebackend.service.entity.Address;
import employeebackend.service.entity.Employee;
import employeebackend.service.exceptions.EmployeeExistException;
import employeebackend.service.exceptions.NoEmployeeFoundException;
import employeebackend.service.exceptions.ValidationException;
import employeebackend.service.interfaces.Operations;
import employeebackend.service.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseRepository implements Operations {

    private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO employee_personal (firstname, middlename, lastname, phone, email, employee_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ADDRESS_QUERY = "INSERT INTO employee_address (employee_id, house_name, street_name, city, state, pincode, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_EMPLOYEE_QUERY = "SELECT ep.firstname, ep.middlename, ep.lastname, ep.phone, ep.email, ep.employee_id, ea.house_name, ea.street_name, ea.city, ea.state, ea.pincode FROM employee_personal ep JOIN employee_address ea ON ep.employee_id = ea.employee_id WHERE ea.type IN ('permanent', 'temporary')";
    private static final String FILTER_BY_PINCODE_QUERY = "SELECT ep.firstname, ep.middlename, ep.lastname, ep.phone, ep.email, ep.employee_id, ea.house_name, ea.street_name, ea.city, ea.state, ea.pincode FROM employee_personal ep JOIN employee_address ea ON ep.employee_id = ea.employee_id WHERE ea.pincode = ?";

    private final Logger logger = LoggerFactory.getLogger(DataBaseRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Validation validation = new Validation();

    @Override
    public String create(Employee employee) throws ValidationException, EmployeeExistException {
        validation.validateEmployee(employee);

        try {
           int result= jdbcTemplate.update(INSERT_EMPLOYEE_QUERY,
                    employee.getFirstName(), employee.getMiddleName(), employee.getLastName(),
                    employee.getPhone(), employee.getEmail(), employee.getEmployeeID());
            if (result == 0) {
                logger.error("SQL-001");
                return "SQL-001";
            }

             result= jdbcTemplate.update(INSERT_ADDRESS_QUERY,
                    employee.getEmployeeID(), employee.getPermanentAddress().getHouseName(),
                    employee.getPermanentAddress().getStreetName(), employee.getPermanentAddress().getCity(),
                    employee.getPermanentAddress().getState(), employee.getPermanentAddress().getPincode(), "permanent");
            if (result == 0) {
                logger.error("SQL-002");
                return "SQL-002";
            }

            result=jdbcTemplate.update(INSERT_ADDRESS_QUERY,
                    employee.getEmployeeID(), employee.getTemporaryAddress().getHouseName(),
                    employee.getTemporaryAddress().getStreetName(), employee.getTemporaryAddress().getCity(),
                    employee.getTemporaryAddress().getState(), employee.getTemporaryAddress().getPincode(), "temporary");
            if (result == 0) {
                logger.error("SQL-003");
                return "SQL-003";
            }

            return "SQL-000";
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            logger.warn("EMP-001: Employee ID Already Exists");
            throw new EmployeeExistException("EMP-001: Employee ID Already Exists");
        }
    }

    @Override
    public List<Employee> read() throws NoEmployeeFoundException {
        List<Employee> employees = jdbcTemplate.query(SELECT_EMPLOYEE_QUERY, new EmployeeMapper());

        if (employees.isEmpty()) {
            throw new NoEmployeeFoundException();
        }

        return employees;
    }

    @Override
    public List<Employee> read(Long id) {
        return new ArrayList<>();
    }

    @Override
    public List<Employee> filterByPincode(Integer pincode) throws NoEmployeeFoundException {
        List<Employee> employees = jdbcTemplate.query(FILTER_BY_PINCODE_QUERY, new Object[]{pincode}, new EmployeeMapper());

        if (employees.isEmpty()) {
            throw new NoEmployeeFoundException();
        }

        return employees;
    }

    protected class EmployeeMapper implements RowMapper<Employee>{

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setFirstName(rs.getString(1));
            employee.setMiddleName(rs.getString(2));
            employee.setLastName(rs.getString(3));
            employee.setPhone(rs.getLong(4));
            employee.setEmail(rs.getString(5));
            employee.setEmployeeID(rs.getLong(6));

            Address permanentAddress = new Address();
            permanentAddress.setEmployeeID(rs.getLong(6));
            permanentAddress.setHouseName(rs.getString(7));
            permanentAddress.setStreetName(rs.getString(8));
            permanentAddress.setCity(rs.getString(9));
            permanentAddress.setState(rs.getString(10));
            permanentAddress.setPincode(rs.getInt(11));

            Address temporaryAddress = new Address();
            temporaryAddress.setEmployeeID(rs.getLong(6));
            temporaryAddress.setHouseName(rs.getString(7));
            temporaryAddress.setStreetName(rs.getString(8));
            temporaryAddress.setCity(rs.getString(9));
            temporaryAddress.setState(rs.getString(10));
            temporaryAddress.setPincode(rs.getInt(11));

            employee.setPermanentAddress(permanentAddress);
            employee.setTemporaryAddress(temporaryAddress);

            return employee;
        }
    }
}
