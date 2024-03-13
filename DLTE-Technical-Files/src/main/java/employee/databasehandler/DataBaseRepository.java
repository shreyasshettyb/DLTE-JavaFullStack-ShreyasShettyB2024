package employee.databasehandler;

import employee.middleware.entity.Address;
import employee.middleware.entity.Personal;
//import employee.middleware.remote.Operations;
import employee.remote.Operations;
import oracle.jdbc.OracleDriver;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DataBaseRepository implements Operations {
    private Connection connection;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
    public void create(Personal personal,Address permanentAddress,Address temporaryAddress) {
        try {
                String query;
                query = "insert into employee_personal values(?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, personal.getFirstName());
                preparedStatement.setString(2, personal.getMiddleName());
                preparedStatement.setString(3, personal.getLastName());
                preparedStatement.setLong(4, personal.getPhone());
                preparedStatement.setString(5, personal.getEmail());
                preparedStatement.setLong(6, personal.getEmployeeID());

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
                preparedStatement.setLong(1, permanentAddress.getEmployeeID());
                preparedStatement.setString(2, permanentAddress.getHouseName());
                preparedStatement.setString(3, permanentAddress.getStreetName());
                preparedStatement.setString(4, permanentAddress.getCity());
                preparedStatement.setString(5, permanentAddress.getState());
                preparedStatement.setInt(6, permanentAddress.getPincode());
                preparedStatement.executeUpdate();

                String query2;
                query2 = "insert into TEMPORARY_ADDRESS values(?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setLong(1, temporaryAddress.getEmployeeID());
                preparedStatement.setString(2, temporaryAddress.getHouseName());
                preparedStatement.setString(3, temporaryAddress.getStreetName());
                preparedStatement.setString(4, temporaryAddress.getCity());
                preparedStatement.setString(5, temporaryAddress.getState());
                preparedStatement.setInt(6, temporaryAddress.getPincode());
                preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }

    }

    @Override
    public Object[] read() {
        ArrayList<Personal> personalArrayList = new ArrayList<>();
        ArrayList<Address> permanentAddressList = new ArrayList<>();
        ArrayList<Address> temporaryAddressList = new ArrayList<>();

        try {
            String query = "select * from employee_personal join permanent_address on employee_personal.EMPLOYEEID=permanent_address.EMPLOYEEID Join temporary_address on employee_personal.EMPLOYEEID=temporary_address.EMPLOYEEID ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personalArrayList.add(new Personal(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5), resultSet.getLong(6)));
                permanentAddressList.add(new Address(resultSet.getLong(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getInt(12)));
                temporaryAddressList.add(new Address(resultSet.getLong(13),resultSet.getString(14),resultSet.getString(15),resultSet.getString(16),resultSet.getString(17),resultSet.getInt(18)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object[] objects = new Object[3];
        objects[0] = personalArrayList;
        objects[1] = permanentAddressList;
        objects[2] = temporaryAddressList;

        return objects;
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
