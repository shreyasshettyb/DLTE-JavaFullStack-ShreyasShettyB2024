package employee.console;

//import employee.backend.DataBaseRepository;
//import employee.backend.Operations;
import employee.entity.Address;
import employee.entity.Employee;
import employee.validation.Validation;
import employee_backend.backend.DataBaseRepository;
import employee_backend.backend.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;


public class EmployeeConsole {
    //    private static FileRepository fileRepository;
    private static Operations operations = new DataBaseRepository();
    //        private static DataBaseRepository dataBaseRepository = new DataBaseRepository();
    private static Validation validation = new Validation();
    static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);

    //    static {
//        try {
//            fileRepository = new FileRepository();
//            dataBaseRepository = new DataBaseRepository();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    static {
//        dataBaseRepository = new DataBaseRepository();
    }

    static int count = 0;
    private static Long employeeID;
    static Scanner scanner = new Scanner(System.in);
    public static Employee employee;

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        while (true) {
            System.out.println(resourceBundle.getString("app.greet"));
            System.out.println(resourceBundle.getString("app.menu"));
            char option = scanner.next().charAt(0);
            switch (option) {
                case '1':
                    do {
                        System.out.println("Enter Employee " + (count + 1) + " Details :");
                        collectEmployeeDetails();
                        count++;
//                        validation.validateEmployee(employee);
                        operations.create(employee);
                        System.out.println("Do you want to add another employee?");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;
                case '2':
                    displayEmployeeDetails();
                    logger.info("Displayed Employee Details");
                    break;
                case '3':
                    System.out.println("Enter Pincode ");
                    Integer pincode = scanner.nextInt();
//                    displayEmployeeDetails(pincode);
                    logger.info("Displayed Employee Details With Pincode as filter");
                    break;
                default:
                    System.out.println("Invalid Option\nPlease Try Again");
                    logger.warn("Invalid Option");
            }

        }

    }

    static void collectEmployeeDetails() {
        while (true) {
            try {
                System.out.println("Enter Employee ID ");
                employeeID = scanner.nextLong();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid employeeID");
                System.out.println("Invalid Employee Id\n Number are only allowed");
            }
        }
        scanner.nextLine();
        System.out.println("Enter Employee First Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter Employee Middle Name");
        String middleName = scanner.nextLine();
        System.out.println("Enter Employee Last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter Employee Phone");
        Long phone;
        while (true) {
            try {
                phone = scanner.nextLong();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Phone Number");
                System.out.println("Re-Enter your Employee Phone");
            }
        }
        scanner.nextLine();
        System.out.println("Enter Employee Email ");
        String email = scanner.next();
//        Personal personal = validation.validatePersonal(firstName, middleName, lastName, phone, email, employeeID);
        scanner.nextLine();
        System.out.println("Enter Employee Permanent Address");
        System.out.println("Enter Employee  House Name");
        String permanentHouseName = scanner.nextLine();
        System.out.println("Enter Employee Street Name");
        String permanentStreetName = scanner.nextLine();
        System.out.println("Enter Employee City");
        String permanentCity = scanner.nextLine();
        System.out.println("Enter Employee State");
        String permanentState = scanner.nextLine();
        System.out.println("Enter Employee Pincode");
        Integer permanentPincode;
        while (true) {
            try {
                permanentPincode = scanner.nextInt();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Pincode");
                System.out.println("Re-Enter your Permanent Pincode");
            }
        }
        Address permanentAddress = new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode);
        System.out.println("Enter Employee Temporary Address");
        System.out.println("Enter Employee House Name");
        scanner.nextLine();
        String temporaryHouseName = scanner.nextLine();
        System.out.println("Enter Employee Street Name");
        String temporaryStreetName = scanner.nextLine();
        System.out.println("Enter Employee City");
        String temporaryCity = scanner.nextLine();
        System.out.println("Enter Employee State");
        String temporaryState = scanner.nextLine();
        System.out.println("Enter Employee Pincode");
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
        Address temporaryAddress = new Address(employeeID, temporaryHouseName, temporaryStreetName, temporaryCity, temporaryState, temporaryPincode);
        employee = new Employee(firstName, middleName, lastName, phone, email, employeeID, permanentAddress, temporaryAddress);
    }


    //Employee Details Are Displayed
    static void displayEmployeeDetails() {
        operations = new DataBaseRepository();
        System.out.println("Employee Details Are");
        ArrayList<employee_backend.entity.Employee> employeeList = operations.read();

        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            System.out.println(employeeList.get(index).toString());
        }
    }

//    static void displayEmployeeDetails(Integer pincode) {
////        logger.info("Displayed Employee Details Based On Pincode");
//        System.out.println("Employee Details Are");
//        List<Employee> employeeList= operations.readByPincode(pincode);
//
//        int size = employeeList.size();
//        if (size == 0) {
//            System.out.println("No Employees were found for the given pincode");
//            logger.info("Empty filtered list");
//            return;
//        }
//        for (int index = 0; index < size; index++) {
//            System.out.println(employeeList.get(index).toString());
//        }
//    }
}
