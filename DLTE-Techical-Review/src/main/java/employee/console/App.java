package employee.console;

import employee.filehandler.FileRepository;
import employee.middleware.entity.Address;
import employee.middleware.entity.Personal;
import employee.middleware.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    private static ArrayList<Personal> personalArrayList = new ArrayList<>();
    private static ArrayList<Address> permanentAddressList = new ArrayList<>();
    private static ArrayList<Address> temporaryAddressList = new ArrayList<>();
    private static FileRepository fileRepository;
    private static Validation validation = new Validation();
    static Logger logger = LoggerFactory.getLogger(App.class);

    static {
        try {
            fileRepository = new FileRepository();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int count = 0;
    private static Long employeeID;
    static Scanner scanner = new Scanner(System.in);

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
                        collectEmployeeName();
                        collectEmployeeAddress();
                        count++;
                        System.out.println("Do you want to add another employee?");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    fileRepository.create(personalArrayList, permanentAddressList, temporaryAddressList);
                    break;
                case '2':
                    displayEmployeeDetails();
                    logger.info("Displayed Employee Details");
                    break;
                case '3':
                    System.out.println("Enter Pincode ");
                    Integer pincode = scanner.nextInt();
                    displayEmployeeDetails( pincode);
                    logger.info("Displayed Employee Details With Pincode as filter");
                    break;
                default:
                    System.out.println("Invalid Option\nPlease Try Again");
                    logger.warn("Invalid Option");
            }

        }

    }

    static void collectEmployeeName() {
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
        Personal personal = validation.validatePersonal(firstName, middleName, lastName, phone, email, employeeID);
        personalArrayList.add(personal);

    }


    static void collectEmployeeAddress() {
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
        Address address = validation.validateAddress(permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode, employeeID);
        permanentAddressList.add(address);
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
        address = validation.validateAddress(temporaryHouseName, temporaryStreetName, temporaryCity, temporaryState, temporaryPincode, employeeID);
        temporaryAddressList.add(address);
    }


    //Employee Details Are Displayed
    static void displayEmployeeDetails() {
        logger.info("Displayed Employee Details");
        System.out.println("Employee Details Are");
        personalArrayList = (ArrayList<Personal>) fileRepository.read()[0];
        permanentAddressList = (ArrayList<Address>) fileRepository.read()[1];
        temporaryAddressList = (ArrayList<Address>) fileRepository.read()[2];
        int size = personalArrayList.size();
        for (int index = 0; index < size; index++) {
            System.out.println(personalArrayList.get(index).toString());
            System.out.println(permanentAddressList.get(index).toString());
            System.out.println(temporaryAddressList.get(index).toString());
        }
    }

    static void displayEmployeeDetails(Integer pincode) {
        logger.info("Displayed Employee Details Based On Pincode");
        System.out.println("Employee Details Are");
        personalArrayList = (ArrayList<Personal>) fileRepository.read()[0];
        permanentAddressList = (ArrayList<Address>) fileRepository.read()[1];
        permanentAddressList = (ArrayList<Address>) permanentAddressList.stream().filter(each ->each.getPincode().equals(pincode)).collect(Collectors.toList());
        temporaryAddressList = (ArrayList<Address>) fileRepository.read()[2];
        int size = permanentAddressList.size();
        if(size==0){
            System.out.println("No Employees were found for the given pincode");
            logger.info("Empty filtered list");
            return;
        }
        for(Personal personal:personalArrayList){
            for (Address address:permanentAddressList){
                if(personal.getEmployeeID().equals(address.getEmployeeID())){
                    System.out.println(personal.toString());
                    System.out.println(address.toString());
                }
            }
        }
    }
}
