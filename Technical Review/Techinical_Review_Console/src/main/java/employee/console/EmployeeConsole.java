package employee.console;

import employee.entity.Address;
import employee.entity.Employee;
import employee.validation.Validation;
import employeebackend.exceptions.ConnectionException;
import employeebackend.exceptions.EmployeeExistException;
import employeebackend.exceptions.NoEmployeeFoundException;
import employeebackend.exceptions.ValidationException;
import employeebackend.interfaces.Operations;
import employeebackend.repository.DataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;


public class EmployeeConsole {
    private static Operations operations;
    private static Validation validation = new Validation();
    static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);

    static int count = 0;
    private static Long employeeID;
    static Scanner scanner = new Scanner(System.in);
    public static employeebackend.entity.Employee employeeSend = new employeebackend.entity.Employee();
    public static employeebackend.entity.Address permanentAddressSend, temporaryAddressSend;

    public static Address permanentAddress, temporaryAddress;
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public static ResourceBundle resourceBundleError = ResourceBundle.getBundle("error");

    // Main Function
    public static void main(String[] args) throws EmployeeExistException {
        try {
            while (true) {
                operations = new DataBaseRepository();
                System.out.println(resourceBundle.getString("app.greet"));
                System.out.println(resourceBundle.getString("app.menu"));
                char option = scanner.next().charAt(0);
                switch (option) { // option based action
                    case '1':
                        do {
                            Employee employee;
                            System.out.println("Enter Employee " + (count + 1) + " Details :");
                            employee = collectEmployeeDetails();
                            count++;
                            employee = validation.validateEmployee(employee);
                            try {
                                translateAndSend(employee);
                                System.out.println(resourceBundle.getString("app.employee.addAnother"));
                            } catch (ValidationException e) {
                                logger.error(resourceBundleError.getString(e.getMessage()));
                                System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                boolean flag = false; // verify if validation as been successful in console
                                do {
                                    try {
                                        employee = validation.validateEmployee(employee);
                                        translateAndSend(employee);
                                        flag = true;
                                    } catch (ValidationException ex) {
                                        logger.error(resourceBundleError.getString(e.getMessage()));
                                        System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                    }
                                } while (!flag);
                                System.out.println(resourceBundle.getString("app.employee.addAnother"));
                            } catch (EmployeeExistException e) {
                                logger.warn(e.getMessage());
                                System.out.println(resourceBundle.getString("app.error.employeeIdExists"));
                            }
                        } while (scanner.next().equalsIgnoreCase("yes"));
                        break;
                    case '2':
                        displayEmployeeDetails();
                        logger.info("Displayed Employee Details");
                        break;
                    case '3':
                        System.out.println(resourceBundle.getString("app.employee.enterPincodeFilter"));
                        Integer pincode = scanner.nextInt();
//                    displayEmployeeDetails(pincode);
                        logger.info("Displayed Employee Details With Pincode as filter");
                        break;
                    default:
                        System.out.println(resourceBundle.getString("app.error.invalidOption"));
                        logger.warn("Invalid Option");
                }
            }
        } catch (ConnectionException e) {
            System.out.println(e.getMessage() + " Contact Support");
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    //Collect Employee Details
    static Employee collectEmployeeDetails() {
        while (true) {
            try {
                System.out.println(resourceBundle.getString("app.employee.enterId"));
                employeeID = scanner.nextLong();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid employeeID");
                System.out.println(resourceBundle.getString("app.validation.invalidEmployeeId"));
            }
        }
        scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterFirstName"));
        String firstName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterMiddleName"));
        String middleName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterLastName"));
        String lastName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPhone"));
        Long phone;
        while (true) {
            try {
                phone = scanner.nextLong();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Phone Number");
                System.out.println(resourceBundle.getString("app.validation.invalidPhoneNumber"));
            }
        }
        scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterEmail"));
        String email = scanner.next();
        scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPermanentAddress"));
        System.out.println(resourceBundle.getString("app.employee.enterHouseName"));
        String permanentHouseName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterStreetName"));
        String permanentStreetName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterCity"));
        String permanentCity = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterState"));
        String permanentState = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPincode"));
        Integer permanentPincode;
        while (true) {
            try {
                permanentPincode = scanner.nextInt();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Pincode");
                System.out.println(resourceBundle.getString("app.validation.invalidPincode"));
            }
        }
        permanentAddress = new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode);
        System.out.println(resourceBundle.getString("app.employee.enterTemporaryAddress"));
        System.out.println(resourceBundle.getString("app.employee.enterHouseName"));
        scanner.nextLine();
        String temporaryHouseName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterStreetName"));
        String temporaryStreetName = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterCity"));
        String temporaryCity = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterState"));
        String temporaryState = scanner.nextLine();
        System.out.println(resourceBundle.getString("app.employee.enterPincode"));
        Integer temporaryPincode;
        while (true) {
            try {
                temporaryPincode = scanner.nextInt();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Pincode");
                System.out.println("Re-Enter your Permanent Pincode");
            }
        }
        temporaryAddress = new Address(employeeID, temporaryHouseName, temporaryStreetName, temporaryCity, temporaryState, temporaryPincode);
        return new Employee(firstName, middleName, lastName, phone, email, employeeID, permanentAddress, temporaryAddress);
    }

    //Translate Object into Backend Entities
    static void translateAndSend(Employee employee) throws SQLException, ValidationException, EmployeeExistException {
        permanentAddress = employee.getPermanentAddress();
        temporaryAddress = employee.getTemporaryAddress();
        permanentAddressSend = new employeebackend.entity.Address();
        temporaryAddressSend = new employeebackend.entity.Address();
        permanentAddressSend.setHouseName(permanentAddress.getHouseName());
        permanentAddressSend.setCity(permanentAddress.getCity());
        permanentAddressSend.setState(permanentAddress.getState());
        permanentAddressSend.setStreetName(permanentAddress.getStreetName());
        permanentAddressSend.setEmployeeID(permanentAddress.getEmployeeID());
        permanentAddressSend.setPincode(permanentAddress.getPincode());
        temporaryAddressSend.setHouseName(temporaryAddress.getHouseName());
        temporaryAddressSend.setCity(temporaryAddress.getCity());
        temporaryAddressSend.setState(temporaryAddress.getState());
        temporaryAddressSend.setStreetName(temporaryAddress.getStreetName());
        temporaryAddressSend.setEmployeeID(temporaryAddress.getEmployeeID());
        temporaryAddressSend.setPincode(temporaryAddress.getPincode());
        employeeSend.setFirstName(employee.getFirstName());
        employeeSend.setMiddleName(employee.getMiddleName());
        employeeSend.setLastName(employee.getLastName());
        employeeSend.setPhone(employee.getPhone());
        employeeSend.setEmail(employee.getEmail());
        employeeSend.setEmployeeID(employee.getEmployeeID());
        employeeSend.setPermanentAddress(permanentAddressSend);
        employeeSend.setTemporaryAddress(temporaryAddressSend);
        String result = operations.create(employeeSend);
        logger.info(resourceBundleError.getString(result));
    }

    //Employee Details Are Displayed
    static void displayEmployeeDetails() throws ConnectionException {
        operations = new DataBaseRepository();
        System.out.println(resourceBundle.getString("app.employee.details"));
        ArrayList<employeebackend.entity.Employee> employeeList = null;
        try {
            employeeList = operations.read();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoEmployeeFoundException e) {
            logger.warn(e.getMessage());
            System.out.println(e.getMessage());
        }
        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            employeebackend.entity.Employee employee = employeeList.get(index);
            System.out.println("Employee " + employee.getEmployeeID() + " Details:");
            System.out.println("  First Name: " + employee.getFirstName());
            System.out.println("  Middle Name: " + employee.getMiddleName());
            System.out.println("  Last Name: " + employee.getLastName());
            System.out.println("  Phone: " + employee.getPhone());
            System.out.println("  Email: " + employee.getEmail());
            System.out.println("  Permanent Address:");
            if (employee.getPermanentAddress() != null) {
                printAddressDetails(employee.getPermanentAddress());
            } else {
                System.out.println("  Not Available");
            }
            System.out.println("  Temporary Address:");
            if (employee.getTemporaryAddress() != null) {
                printAddressDetails(employee.getTemporaryAddress());
            } else {
                System.out.println("  Not Available");
            }
        }
    }

    //Prints Address object
    static public void printAddressDetails(employeebackend.entity.Address address) {
        System.out.println("    House Name: " + address.getHouseName());
        System.out.println("    Street Name: " + address.getStreetName());
        System.out.println("    City: " + address.getCity());
        System.out.println("    State: " + address.getState());
        System.out.println("    Pincode: " + address.getPincode());
    }

    //Print Employee Based On Pincode
//    static void displayEmployeeDetails(Integer pincode) {
//        while (true) {
//            logger.info("Displayed Employee Details Based On Pincode");
//        operations = new DataBaseRepository();
//        System.out.println("Employee Details by pincode Are");
//        ArrayList<employeebackend.entity.Employee> employeeList = operations.read(pincode);
//        int size = employeeList.size();
//        for (int index = 0; index < size; index++) {
//            System.out.println(employeeList.get(index).toString());
//        }
//        }
//    }
}
