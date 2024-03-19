package employee.backend;

import employee.entity.Address;
import employee.entity.Employee;
import employee.validation.Validation;
import oracle.jdbc.OracleDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

//import employee.middleware.remote.Operations;

public class DataBaseRepository implements Operations {
    private Connection connection;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Validation validation = new Validation();

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DataBaseRepository() {
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
    }

    @Override
    public void create(Employee employee) {
//        if(validation.validateEmployee(employee)) {
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
//            if (result != 0) {
//                logger.log(Level.INFO, resourceBundle.getString("record.push.ok"));
//                System.out.println(resourceBundle.getString("record.push.ok"));
//            } else {
//                logger.log(Level.INFO, resourceBundle.getString("record.push.fail"));
//                System.out.println(resourceBundle.getString("record.push.fail"));
//            }
                String query1;
                query1 = "insert into PERMANENT_ADDRESS values(?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query1);
                preparedStatement.setLong(1, employee.getEmployeeID());
                preparedStatement.setString(2, employee.getPermanentAddress().getHouseName());
                preparedStatement.setString(3, employee.getPermanentAddress().getStreetName());
                preparedStatement.setString(4, employee.getPermanentAddress().getCity());
                preparedStatement.setString(5, employee.getPermanentAddress().getState());
                preparedStatement.setInt(6, employee.getPermanentAddress().getPincode());
                preparedStatement.executeUpdate();

                String query2;
                query2 = "insert into TEMPORARY_ADDRESS values(?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setLong(1, employee.getTemporaryAddress().getEmployeeID());
                preparedStatement.setString(2, employee.getTemporaryAddress().getHouseName());
                preparedStatement.setString(3, employee.getTemporaryAddress().getStreetName());
                preparedStatement.setString(4, employee.getTemporaryAddress().getCity());
                preparedStatement.setString(5, employee.getTemporaryAddress().getState());
                preparedStatement.setInt(6, employee.getTemporaryAddress().getPincode());
                preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException sqlException) {
                System.out.println(sqlException);
            }
//        }else {
//            System.out.println("Could not Validate Date in the Backend");
//        }


    }

    @Override
    public ArrayList<Employee> read() {
        ArrayList<Employee> list = new ArrayList<>();
        try {
            String query = "select * from employee_personal join permanent_address on employee_personal.EMPLOYEEID=permanent_address.EMPLOYEEID Join temporary_address on employee_personal.EMPLOYEEID=temporary_address.EMPLOYEEID ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6),
                        new Address(resultSet.getLong(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getInt(12)),
                        new Address(resultSet.getLong(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getString(17), resultSet.getInt(18))));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }


    @Override
    public Object[] read(Long aLong) {
        return new Object[0];
    }

    @Override
    public Object[] filterAddress(String s, Object o) {
        return new Object[0];
    }


}
