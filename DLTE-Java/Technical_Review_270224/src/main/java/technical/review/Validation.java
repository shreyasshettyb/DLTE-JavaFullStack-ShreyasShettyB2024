package technical.review;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {
    public void  validateName(String firstName, String middleName, String lastName, Employee employee) {
        Scanner scanner = new Scanner(System.in);
        while(!Pattern.matches("^[A-Za-z]+$",firstName)){
            System.out.println("Re-Enter your First Name(Name can only contain alphabets)");
            firstName = scanner.nextLine();
        }

        while(!Pattern.matches("^[A-Za-z]*$",middleName)){
            System.out.println("Re-Enter your Middle Name(Name can only contain alphabets)");
            middleName = scanner.nextLine();
        }

        while(!Pattern.matches("^[A-Za-z]*$",lastName)){
            System.out.println("Re-Enter your Last Name(Name can only contain alphabets)");
            lastName = scanner.nextLine();
        }
        employee.setName(new Name(firstName, middleName, lastName));
        //employee.name=new Name(firstName,middleName,lastName);
    }

    public void  validateAddress(String permanentHouseName, String permanentStreetName, String permanentCity,String permanentState,Integer permanentPincode,Employee employee){
        Scanner scanner = new Scanner(System.in);
        while(permanentHouseName.length()==0){
            System.out.println("Re-Enter your Permanent House Name");
            permanentHouseName = scanner.nextLine();
        }
        while(permanentStreetName.length()==0){
            System.out.println("Re-Enter your Permanent Street Name");
            permanentStreetName = scanner.nextLine();
        }
        while(permanentCity.length()==0){
            System.out.println("Re-Enter your Permanent City ");
            permanentCity = scanner.nextLine();
        }
        while(permanentState.length()==0){
            System.out.println("Re-Enter your Permanent State ");
            permanentState = scanner.nextLine();
        }
        while(!Pattern.matches("^[0-9]${6}",permanentPincode.toString())){
            System.out.println("Re-Enter your Permanent Pincode");
            permanentPincode = scanner.nextInt();
        }
        //employee.permanentAddress=new Address(permanentHouseName,permanentStreetName,permanentCity,permanentState,permanentPincode);

    }

    public void  validateCommunication(String firstName,String middleName, String lastName,Employee employee){
        Scanner scanner = new Scanner(System.in);
        while(!Pattern.matches("^[A-Za-z]+$",firstName)){
            System.out.println("Re-Enter your First Name(Name can only contain alphabets)");
            firstName = scanner.nextLine();
        }

        while(!Pattern.matches("^[A-Za-z]*$",middleName)){
            System.out.println("Re-Enter your First Name(Name can only contain alphabets)");
            middleName = scanner.nextLine();
        }

        while(!Pattern.matches("^[A-Za-z]*$",lastName)){
            System.out.println("Re-Enter your First Name(Name can only contain alphabets)");
            lastName = scanner.nextLine();
        }
       // employee.name=new Name(firstName,middleName,lastName);
    }
}
