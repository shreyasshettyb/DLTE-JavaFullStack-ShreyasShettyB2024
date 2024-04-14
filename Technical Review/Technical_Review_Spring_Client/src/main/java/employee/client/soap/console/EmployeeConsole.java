package employee.client.soap.console;

import employee.client.soap.entity.Address;
import employee.client.soap.entity.Employee;
import employee.client.soap.service.SOAPConnector;
import employee.client.soap.service.SOAPService;
import employee.client.soap.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.soap.client.SoapFaultClientException;
import soap.webservice.CreateResponse;
import soap.webservice.FilterByPincodeResponse;
import soap.webservice.ReadByIdResponse;
import soap.webservice.ReadResponse;

import javax.xml.ws.soap.SOAPFaultException;
import java.util.*;

public class EmployeeConsole {

    public static soap.webservice.Employee employeeSend = new soap.webservice.Employee();
    public static soap.webservice.Address permanentAddressSend = new soap.webservice.Address();
    ;
    public static soap.webservice.Address temporaryAddressSend = new soap.webservice.Address();
    public static Address permanentAddress, temporaryAddress;
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public static ResourceBundle resourceBundleError = ResourceBundle.getBundle("error");
    static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);
    static int count = 0;
    static Scanner scanner = new Scanner(System.in);
    //    private static EmployeeSoapService employeeSoapService = new EmployeeSoapService();
    private static SOAPConnector soapConnector;
    private static SOAPService operations = new SOAPService();
    private static Validation validation = new Validation();
    private static Long employeeID;

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
        permanentAddress = new Address(employeeID, permanentHouseName, permanentStreetName, permanentCity, permanentState, permanentPincode, "permanent");
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
                scanner.nextLine();
                break;
            } catch (InputMismatchException exception) {
                scanner.nextLine();
                logger.warn("Invalid Pincode");
                System.out.println("Re-Enter your Permanent Pincode");
            }
        }
        temporaryAddress = new Address(employeeID, temporaryHouseName, temporaryStreetName, temporaryCity, temporaryState, temporaryPincode, "temporary");
        return new Employee(firstName, middleName, lastName, phone, email, employeeID, permanentAddress, temporaryAddress);
    }

    //Translate Object into Backend Entities
    static void translateAndSend(Employee employee) {
        BeanUtils.copyProperties(employee, employeeSend);
        BeanUtils.copyProperties(employee.getPermanentAddress(), permanentAddressSend);
        BeanUtils.copyProperties(employee.getTemporaryAddress(), temporaryAddressSend);
        employeeSend.setPermanentAddress(permanentAddressSend);
        employeeSend.setTemporaryAddress(temporaryAddressSend);
        logger.info(employee.getPermanentAddress().toString() + "\n" + employee.toString());
        CreateResponse response = operations.callCreateEmployee(soapConnector, employeeSend);
        System.out.println(response.getResult());
        logger.info(response.getServiceStatus().getStatus() + " : " + response.getServiceStatus().getMessage());
    }

    //Employee Details Are Displayed
    static void displayEmployeeDetails() {
//        operations = new DataBaseRepository();
        ArrayList<soap.webservice.Employee> employeeList = null;
        try {
            ReadResponse response = operations.callReadAll(soapConnector);
            employeeList = (ArrayList<soap.webservice.Employee>) response.getEmployee();
            logger.info(response.getServiceStatus().getStatus() + " : " + response.getServiceStatus().getMessage());
        } catch (SoapFaultClientException e) {
            logger.error(e.getFaultStringOrReason());
            return;
        }
        System.out.println(resourceBundle.getString("app.employee.details"));
        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            soap.webservice.Employee employee = employeeList.get(index);
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
        logger.info("Displayed Employee Details");

    }

    //Prints Address object
    static public void printAddressDetails(soap.webservice.Address address) {
        System.out.println("    House Name: " + address.getHouseName());
        System.out.println("    Street Name: " + address.getStreetName());
        System.out.println("    City: " + address.getCity());
        System.out.println("    State: " + address.getState());
        System.out.println("    Pincode: " + address.getPincode());
    }

    //    Print Employee Based On Pincode
    static void displayEmployeeDetails(Integer pincode) {
//        operations = new DataBaseRepository();
        ArrayList<soap.webservice.Employee> employeeList = null;
        try {
            FilterByPincodeResponse response = operations.filterByPincode(soapConnector, pincode);
            employeeList = (ArrayList<soap.webservice.Employee>) response.getEmployee();
            logger.info(response.getServiceStatus().getStatus() + " : " + response.getServiceStatus().getMessage());
        } catch (SoapFaultClientException e) {
            logger.error(e.getFaultStringOrReason());
            return;
        }
        System.out.println("Employee Details by pincode Are");
        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            soap.webservice.Employee employee = employeeList.get(index);
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
        logger.info("Displayed Employee Details Based On Pincode");

    }

    static void displayEmployeeDetails(long employeeID) {
//        operations = new DataBaseRepository();
        soap.webservice.Employee employee = new soap.webservice.Employee();
        try {
            ReadByIdResponse response = operations.callReadById(soapConnector, employeeID);
            employee = response.getEmployee();
            logger.info(response.getServiceStatus().getStatus() + " : " + response.getServiceStatus().getMessage());
        } catch (SoapFaultClientException e) {
            logger.error(e.getFaultStringOrReason());
            return;
        }
        System.out.println(resourceBundle.getString("app.employee.details"));
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
        logger.info("Displayed Employee details based on Id");
    }

    public void console(SOAPConnector connector) {
        soapConnector = connector;
        try {
            while (true) {
//                operations = new DataBaseRepository();
                String choice = "";
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
                            } catch (SoapFaultClientException e) {
                                if (e.getFaultStringOrReason().equalsIgnoreCase("ValidationException")) {
                                    logger.error(e.getFaultStringOrReason());
                                    System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                    boolean flag = false; // verify if validation as been successful in console
                                    do {
                                        try {
                                            employee = validation.validateEmployee(employee);
                                            translateAndSend(employee);
                                            flag = true;
                                        } catch (SoapFaultClientException ex) {
                                            logger.error(ex.getFaultStringOrReason());
                                            System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                        }
                                    } while (!flag);
                                    scanner.nextLine();
                                    System.out.println(resourceBundle.getString("app.employee.addAnother"));
                                } else if (e.getFaultStringOrReason().equalsIgnoreCase("EmployeeExistException")) {
                                    logger.warn(e.getFaultStringOrReason());
                                    System.out.println(resourceBundle.getString("app.error.employeeIdExists"));
                                } else {
                                    logger.warn(e.getFaultStringOrReason());
                                    System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                    break;
                                }
                            }
                        } while (scanner.next().equalsIgnoreCase("yes"));
                        break;
                    case '2':
                        displayEmployeeDetails();
                        break;
                    case '3':
                        System.out.println(resourceBundle.getString("app.employee.enterPincodeFilter"));
                        Integer pincode = scanner.nextInt();
                        displayEmployeeDetails(pincode);
                        break;
                    case '4':
                        System.out.println("Enter Employee Id");
                        long employee_id = scanner.nextLong();
                        displayEmployeeDetails(employee_id);

                        break;
                    default:
                        System.out.println(resourceBundle.getString("app.error.invalidOption"));
                        logger.warn("Invalid Option");
                }
            }
        } catch (SoapFaultClientException e) {
            logger.error(e.getFaultStringOrReason());
            System.out.println("Critical System Failure!!! Please Contact Support");
            return;
        }
    }

}

