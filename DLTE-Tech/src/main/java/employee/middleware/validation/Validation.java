package employee.middleware.validation;

import employee.middleware.entity.Address;
import employee.middleware.entity.Personal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {
    static Logger logger = LoggerFactory.getLogger(Validation.class);
    public Personal validatePersonal(String firstName, String middleName, String lastName, Long phone, String email, Long employeeID) {
        Scanner scanner = new Scanner(System.in);
        while (!Pattern.matches("^[A-Za-z]+$", firstName)) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your First Name(Name can only contain alphabets)");
            firstName = scanner.nextLine();
        }

        while (!Pattern.matches("^[A-Za-z]*$", middleName)) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your Middle Name(Name can only contain alphabets)");
            middleName = scanner.nextLine();
        }

        while (!Pattern.matches("^[A-Za-z]*$", lastName)) {
            logger.warn("Invalid Name Format");
            System.out.println("Re-Enter your Last Name(Name can only contain alphabets)");
            lastName = scanner.nextLine();
        }
        while (!Pattern.matches("[0-9]{10}", phone.toString())) {
            logger.warn("Invalid Phone Format");
            System.out.println("Re-Enter your Phone(Phone Can contain only number with count 10)");
            try {
                phone = scanner.nextLong();
            } catch (InputMismatchException exception) {
            }
        }

        while (!Pattern.matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$", email)) {
            logger.warn("Invalid Email Format");
            System.out.println("Re-Enter your Email address");
            email = scanner.nextLine();
        }
        return new Personal(firstName, middleName, lastName, phone, email, employeeID);
    }

    public Address validateAddress(String permanentHouseName, String permanentStreetName, String permanentCity, String permanentState, Integer permanentPincode, Long employeeID) {
        Scanner scanner = new Scanner(System.in);

        while (permanentHouseName.length() == 0) {
            logger.warn("House Name Must be provided");
            System.out.println("Re-Enter your Permanent House Name");
            permanentHouseName = scanner.nextLine();
        }
        while (permanentStreetName.length() == 0) {
            logger.warn("Street Name Must be provided");
            System.out.println("Re-Enter your Permanent Street Name");
            permanentStreetName = scanner.nextLine();
        }
        while (permanentCity.length() == 0) {
            logger.warn("City Must be provided");
            System.out.println("Re-Enter your Permanent City ");
            permanentCity = scanner.nextLine();
        }
        while (permanentState.length() == 0) {
            logger.warn("State Must be provided");
            System.out.println("Re-Enter your Permanent State ");
            permanentState = scanner.nextLine();
        }
        while (!Pattern.matches("[0-9]{6}", permanentPincode.toString())) {
            logger.warn("Invalid Pincode Format");
            System.out.println("Re-Enter your Permanent Pincode");
            try {
                permanentPincode = scanner.nextInt();
            } catch (InputMismatchException exception) {
            }
        }
        return new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode);
    }

}
