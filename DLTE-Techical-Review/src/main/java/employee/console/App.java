package employee.console;

import employee.filehandler.FileRepository;
import employee.middleware.entity.Address;
import employee.middleware.entity.Personal;
import employee.middleware.remote.Operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static ArrayList<Personal> personalArrayList = new ArrayList<>();
    private static ArrayList<Address> permanentAddressList = new ArrayList<>();
    private static ArrayList<Address> temporaryAddressList = new ArrayList<>();
    private static FileRepository fileRepository;

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
        while (true) {
            System.out.println("-------------------- Welcome to Employee Dashboard --------------------");
            System.out.println("Enter You choice\n1.Add Employee Details\n2.View Employee Details");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    do {
                        System.out.println("Enter Employee " + (count + 1) + " Details :");
//                        Employee employee = new Employee();
                        collectEmployeeName();
                        collectEmployeeAddress();
                        count++;
                        System.out.println("Do you want to add another employee?");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    fileRepository.create(personalArrayList, permanentAddressList, temporaryAddressList);
                    break;
                case 2:
                    displayEmployeeDetails();
                    break;
                default:System.exit(0);
            }

        }

    }

    static void collectEmployeeName() {
        System.out.println("Enter Employee ID ");
        employeeID = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter Employee First Name");
        String firstName = scanner.nextLine();
        System.out.println("Enter Employee Middle Name");
        String middleName = scanner.nextLine();
        System.out.println("Enter Employee Last Name");
        String lastName = scanner.nextLine();
        System.out.println("Enter Employee Phone");
        Long phone;
        try {
            phone = scanner.nextLong();
        } catch (Exception exception) {
            System.out.println("Re-Enter your Employee Phone");
            phone = scanner.nextLong();
        }
        System.out.println("Enter Employee Email ");
        String email = scanner.next();
        personalArrayList.add(new Personal(firstName, middleName, lastName, phone, email, employeeID));

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
        try {
            permanentPincode = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Re-Enter your Permanent Pincode");
            permanentPincode = scanner.nextInt();
        }
        permanentAddressList.add(new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode));
//        validation.validateAddress(permanentHouseName,permanentStreetName,permanentCity,permanentState,permanentPincode,employee,0);
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
        Integer temporaryPincode = scanner.nextInt();
//        validation.validateAddress(temporaryHouseName,temporaryStreetName,temporaryCity,temporaryState,temporaryPincode,employee,1);
        temporaryAddressList.add(new Address(employeeID, temporaryHouseName, temporaryStreetName, temporaryCity, temporaryState, temporaryPincode));

    }


    //Employee Details Are Displayed
    static void displayEmployeeDetails() {
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
}
