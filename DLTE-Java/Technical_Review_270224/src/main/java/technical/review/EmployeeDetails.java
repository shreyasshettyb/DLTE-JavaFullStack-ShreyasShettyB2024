package technical.review;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeDetails {
    private static ArrayList<Employee> employeeArrayList = new ArrayList<>();
    static Validation validation = new Validation();
    static int count = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("-------------------- Welcome to Employee Dashboard --------------------");
            System.out.println("Enter You choice\n1.Add Employee Details\n2.View Employee Details");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                   do{ System.out.println("Enter Employee " + (count + 1) + " Details :");
                   Employee employee = new Employee();
                    collectEmployeeName(employee);
                    collectEmployeeAddress(employee);
                    collectEmployeeCommunication(employee);
                    employeeArrayList.add(employee);
                    count++;
                       System.out.println("Do you want to add another employee?");
                   }while(scanner.next().equalsIgnoreCase("yes"));
                       break;
                case 2:
                    displayEmployeeDetails(employeeArrayList);
                    break;

            }

        }

    }

    static void collectEmployeeName(Employee employee) {

        System.out.println("Enter Employee First Name");
        scanner.nextLine();
        String firstName = scanner.nextLine();
        System.out.println("Enter Employee Middle Name");
        String middleName = scanner.nextLine();
        System.out.println("Enter Employee Last Name");
        String lastName = scanner.nextLine();
        validation.validateName(firstName, middleName, lastName, employee);
        employee.setName(new Name(firstName, middleName, lastName));

    }


    static void collectEmployeeAddress(Employee employee) {
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
        }catch (InputMismatchException exception){
            System.out.println("Re-Enter your Permanent Pincode");
            permanentPincode = scanner.nextInt();
        }
        validation.validateAddress(permanentHouseName,permanentStreetName,permanentCity,permanentState,permanentPincode,employee,0);
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
        validation.validateAddress(temporaryHouseName,temporaryStreetName,temporaryCity,temporaryState,temporaryPincode,employee,1);
    }

    static void collectEmployeeCommunication(Employee employee) {
        System.out.println("Enter Employee Phone");
        Long phone;
       try {
           phone = scanner.nextLong();
       }catch (Exception exception){
           System.out.println("Re-Enter your Employee Phone");
           phone = scanner.nextLong();
       }
        System.out.println("Enter Employee Email ");
        String email = scanner.next();
        validation.validateCommunication(phone,email,employee);
    }

    //Employee Details Are Displayed
    static void displayEmployeeDetails(ArrayList<Employee> employee) {
//        System.out.println("Employee Details Are");
        int temp=0;
        for (Employee each : employee) {
            System.out.println("Employee "+each.getName().getFirstName()+" Details Are");
            System.out.println("First Name: "+each.getName().getFirstName()+"\nMiddle Name: "+each.getName().getMiddleName()+"\nLast Name: "+each.getName().getLastName());
            System.out.println("Permanent Address: \n"+each.getPermanentAddress());
            System.out.println("Temporary Address: \n"+each.getTemporaryAddress());
            System.out.println("Phone: "+each.getCommunication().getPhone()+"\nEmail: "+each.getCommunication().getEmail());
        }
    }
}
