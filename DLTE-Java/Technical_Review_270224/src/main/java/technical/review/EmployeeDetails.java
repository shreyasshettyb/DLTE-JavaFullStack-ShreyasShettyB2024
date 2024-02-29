package technical.review;

import java.util.Scanner;

public class EmployeeDetails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        try {
            System.out.println("Enter Number of Employees");
            count = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Enter Number, Retry");
            main(args);
        }
        Employee[] employee = new Employee[count];
        System.out.println("Enter Employee Details :");
        for(int index = 0; index < employee.length; index++) {
            System.out.println("Enter Employee " + (index + 1) + " Details :");
            employee[index] = new Employee(null,null,null,null);
            collectEmployeeName(employee[index]);
            collectEmployeeAddress(employee[index]);
            collectEmployeeCommunication(employee[index]);
        }
            displayEmployeeDetails(employee);
        scanner.close();
    }

    static void collectEmployeeName(Employee employee) {
        Validation validation = new Validation();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee First Name");
        String firstName = scanner.nextLine();
        System.out.println(firstName+"Enter Employee Middle Name");
        String middleName = scanner.nextLine();
        System.out.println("Enter Employee Last Name");
        String lastName = scanner.nextLine();
        validation.validateName(firstName,middleName,lastName,employee);
        //employee.setName(new Name(firstName, middleName, lastName));

    }


    static void collectEmployeeAddress(Employee employee) {
        Validation validation = new Validation();
        Scanner scanner = new Scanner(System.in);
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
        Integer permanentPincode = scanner.nextInt();
        //validation.validateAddress(permanentHouseName,permanentStreetName,permanentCity,permanentState,permanentPincode,employee);
        try {
            employee.setPermanentAddress(new Address(permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode));
        }catch (Exception e){}
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
        //validation.validateAddress(temporaryHouseName,temporaryStreetName,temporaryCity,temporaryState,temporaryPincode,employee);
        try {
            employee.setTemporaryAddress(new Address(temporaryHouseName,temporaryStreetName,temporaryCity,temporaryState,temporaryPincode));
        }catch (Exception e){}
        //scanner.close();
    }
    static void collectEmployeeCommunication(Employee employee) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee Phone");
        Long phone = scanner.nextLong();
        System.out.println("Enter Employee Email ");
        String email = scanner.next();
        employee.setCommunication(new Communication(phone,email));

//        scanner.close();
    }

    //Employee Details Are Displayed
    static void displayEmployeeDetails(Employee[] employee){
//        System.out.println("Employee Details Are");
        for(Employee each:employee){
            System.out.println("Employee  Details Are");
            System.out.println(each);
        }
    }
}
