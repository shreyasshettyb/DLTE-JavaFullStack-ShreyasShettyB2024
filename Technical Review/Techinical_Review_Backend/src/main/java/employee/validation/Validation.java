package employee.validation;

import employee.backend.DataBaseRepository;
import employee.backend.Operations;
import employee.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {
    static Logger logger = LoggerFactory.getLogger(Validation.class);
    private static Operations operations = new DataBaseRepository();
    public boolean validateEmployee(Employee employee) {
        operations = new DataBaseRepository();
        Scanner scanner = new Scanner(System.in);
        while (!Pattern.matches("^[A-Za-z]+$", employee.getFirstName())) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your First Name(Name can only contain alphabets)");
            employee.setFirstName(scanner.nextLine()) ;
        }

        while (!Pattern.matches("^[A-Za-z]*$", employee.getMiddleName())) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your Middle Name(Name can only contain alphabets)");
            employee.setMiddleName(scanner.nextLine());
        }

        while (!Pattern.matches("^[A-Za-z]*$", employee.getLastName())) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your Last Name(Name can only contain alphabets)");
            employee.setLastName(scanner.nextLine());
        }
        while (!Pattern.matches("[0-9]{10}", employee.getPhone().toString())) {
            logger.warn("Invalid Phone Format");
            System.out.println("Re-Enter your Phone(Phone Can contain only number with count 10)");
            try {
                employee.setPhone(scanner.nextLong());
            } catch (InputMismatchException exception) {
            }
        }

        while (!Pattern.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", employee.getEmail())) {
            logger.warn("Invalid Email Format");
            System.out.println("Re-Enter your Email address");
            employee.setEmail( scanner.nextLine());
        }
//      Scanner scanner = new Scanner(System.in);

        while (employee.getPermanentAddress().getHouseName().length() == 0) {
            logger.warn("House Name Must be provided");
            System.out.println("Re-Enter your Permanent House Name");
            employee.getPermanentAddress().setHouseName( scanner.nextLine());
        }
        while (employee.getPermanentAddress().getStreetName().length()  == 0) {
            logger.warn("Street Name Must be provided");
            System.out.println("Re-Enter your Permanent Street Name");
            employee.getPermanentAddress().setStreetName(scanner.nextLine());
        }
        while (employee.getPermanentAddress().getCity().length()== 0) {
            logger.warn("City Must be provided");
            System.out.println("Re-Enter your Permanent City ");
            employee.getPermanentAddress().setCity(scanner.nextLine());
        }
        while (employee.getPermanentAddress().getState().length() == 0) {
            logger.warn("State Must be provided");
            System.out.println("Re-Enter your Permanent State ");
            employee.getPermanentAddress().setState( scanner.nextLine());
        }
        while (!Pattern.matches("[0-9]{6}", employee.getPermanentAddress().getPincode().toString())) {
            logger.warn("Invalid Pincode Format");
            System.out.println("Re-Enter your Permanent Pincode");
            try {
                employee.getPermanentAddress().setPincode( scanner.nextInt());
            } catch (InputMismatchException exception) {
            }
        }
//        return new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode);
        return true;
    }

}
