package employee.client;

import employee.webservice.Address;
import employee.webservice.Employee;
import employee.webservice.EmployeeSoapService;
import employee.webservice.EmployeeSoapServiceService;

import java.util.List;
import java.util.Scanner;

public class EmployeeClient {
    public static void main(String[] args) {
        EmployeeSoapServiceService employeeSoapService = new EmployeeSoapServiceService();
        EmployeeSoapService soap = employeeSoapService.getEmployeeSoapServicePort();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Option:1.create\n2.read");
        int option=scanner.nextInt();
        switch (option) {
            case 1: Employee employee=new Employee();
         employee.setFirstName("akash");
         employee.setMiddleName("d");
         employee.setLastName("h");
         employee.setEmployeeID(9631L);
         employee.setEmail("akash@gmail.com");
         employee.setPhone(9871236540L);
        Address address=new Address();
        address.setCity("koxhikode");
        address.setHouseName("new house");
        address.setStreetName("street");
        address.setState("kerala");
        address.setPincode(987456);
        employee.setPermanentAddress(address);
        employee.setTemporaryAddress(address);
        soap.createEmployee(employee);
                break;
            case 2:
                List<Employee> emp = soap.readEmployee().getEmployeeArrayList();
                for (
                        Employee each : emp) {
                    System.out.println("\nEmployee :");
                    System.out.println("Employee details\n" + "name :" + each.getFirstName() + " \nEmployee id :" + each.getEmployeeID());
                    System.out.println("Permenant address\nHouse Name:" + each.getPermanentAddress().getHouseName() + "\nStreet Name :" + each.getPermanentAddress().getStreetName() + " \nCity name :" + each.getPermanentAddress().getCity() + "\nPincode :" + each.getPermanentAddress().getPincode());
                    System.out.println("Temporary address\nHouse Name:" + each.getTemporaryAddress().getHouseName() + "\nStreet Name :" + each.getTemporaryAddress().getStreetName() + " \nCity name :" + each.getTemporaryAddress().getCity() + "\nPincode :" + each.getTemporaryAddress().getPincode());

                }
                break;

        }
    }
}
