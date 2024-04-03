package employee.console;

import employee.entity.Address;
import employee.entity.Employee;
import employee.validation.Validation;
import employee.webservice.EmployeeSoap;
import employee.webservice.EmployeeSoapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.ws.spi.http.HttpHandler;
import java.sql.SQLException;
import java.util.*;

public class EmployeeConsole {
    public static employee.webservice.Employee employeeSend = new employee.webservice.Employee();
    public static employee.webservice.Address permanentAddressSend, temporaryAddressSend;
    public static Address permanentAddress, temporaryAddress;
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public static ResourceBundle resourceBundleError = ResourceBundle.getBundle("error");
    static Logger logger = LoggerFactory.getLogger(EmployeeConsole.class);
    static int count = 0;
    static Scanner scanner = new Scanner(System.in);
    private static EmployeeSoapService employeeSoapService = new EmployeeSoapService();
    private static EmployeeSoap operations = employeeSoapService.getEmployeeSoapPort();
    private static Validation validation = new Validation();
    private static Long employeeID;

    // Main Function
    public static void main(String[] args) {
        try {
            while (true) {
//                operations = new DataBaseRepository();
                BindingProvider bindingProvider = (BindingProvider) operations;
                List<javax.xml.ws.handler.Handler> handlerChain = bindingProvider.getBinding().getHandlerChain();
                handlerChain.add(new SOAPHeaderPrinter());
                bindingProvider.getBinding().setHandlerChain(handlerChain);
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
                            } catch (SOAPFaultException e) {
                                if (e.getFault().getFaultCode().equalsIgnoreCase("ValidationException")) {
                                    logger.error(resourceBundleError.getString(e.getFault().getFaultString()));
                                    System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                    boolean flag = false; // verify if validation as been successful in console
                                    do {
                                        try {
                                            employee = validation.validateEmployee(employee);
                                            translateAndSend(employee);
                                            flag = true;
                                        } catch (SOAPFaultException ex) {
                                            logger.error(resourceBundleError.getString(ex.getFault().getFaultString()));
                                            System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                        }
                                    } while (!flag);
                                    scanner.nextLine();
                                    System.out.println(resourceBundle.getString("app.employee.addAnother"));
                                } else if (e.getFault().getFaultCode().equalsIgnoreCase("EmployeeExistException")) {
                                    logger.warn(e.getFault().getFaultString());
                                    System.out.println(resourceBundle.getString("app.error.employeeIdExists"));
                                }
                                else{
                                    logger.warn(e.getFault().getFaultString());
                                    System.out.println(resourceBundle.getString("app.error.systemFailure"));
                                    break;
                                }
                            }
                        } while (scanner.next().equalsIgnoreCase("yes"));
                        break;
                    case '2':
                        displayEmployeeDetails();
                        logger.info("Displayed Employee Details");
                        break;
                    case '3':
                        System.out.println(resourceBundle.getString("app.employee.enterPincodeFilter"));
                        Integer pincode = scanner.nextInt();
                        displayEmployeeDetails(pincode);
                        logger.info("Displayed Employee Details With Pincode as filter");
                        break;
                    case '4':
                        System.out.println("Enter Employee Id");
                        long employee_id=scanner.nextLong();
                        displayEmployeeDetails(employee_id);
                        logger.info("Displayed Employee details based on Id");
                        break;
                    default:
                        System.out.println(resourceBundle.getString("app.error.invalidOption"));
                        logger.warn("Invalid Option");
                }
            }
        } catch (SOAPFaultException e) {
            if (e.getFault().getFaultCode().equalsIgnoreCase("ConnectionException"))
                System.out.println(e.getFault().getFaultString() + " Contact Support");
            else if (e.getFault().getFaultCode().equalsIgnoreCase("SQLException")) {
                logger.error(e.getFault().getFaultString());
            }
        }
    }

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
        permanentAddress = employee.getPermanentAddress();
        temporaryAddress = employee.getTemporaryAddress();
        permanentAddressSend = new employee.webservice.Address();
        temporaryAddressSend = new employee.webservice.Address();
        permanentAddressSend.setHouseName(permanentAddress.getHouseName());
        permanentAddressSend.setCity(permanentAddress.getCity());
        permanentAddressSend.setState(permanentAddress.getState());
        permanentAddressSend.setStreetName(permanentAddress.getStreetName());
        permanentAddressSend.setEmployeeID(permanentAddress.getEmployeeID());
        permanentAddressSend.setPincode(permanentAddress.getPincode());
        permanentAddressSend.setType("permanent");
        temporaryAddressSend.setHouseName(temporaryAddress.getHouseName());
        temporaryAddressSend.setCity(temporaryAddress.getCity());
        temporaryAddressSend.setState(temporaryAddress.getState());
        temporaryAddressSend.setStreetName(temporaryAddress.getStreetName());
        temporaryAddressSend.setEmployeeID(temporaryAddress.getEmployeeID());
        temporaryAddressSend.setPincode(temporaryAddress.getPincode());
        temporaryAddressSend.setType("temporary");
        employeeSend.setFirstName(employee.getFirstName());
        employeeSend.setMiddleName(employee.getMiddleName());
        employeeSend.setLastName(employee.getLastName());
        employeeSend.setPhone(employee.getPhone());
        employeeSend.setEmail(employee.getEmail());
        employeeSend.setEmployeeID(employee.getEmployeeID());
        employeeSend.setPermanentAddress(permanentAddressSend);
        employeeSend.setTemporaryAddress(temporaryAddressSend);
        logger.info(employee.getPermanentAddress().toString()+"\n"+employee.toString());
        String result = operations.createEmployee(employeeSend);
        logger.info(resourceBundleError.getString(result));
    }

    //Employee Details Are Displayed
    static void displayEmployeeDetails()  {
//        operations = new DataBaseRepository();
        System.out.println(resourceBundle.getString("app.employee.details"));
        ArrayList<employee.webservice.Employee> employeeList = null;
        try {
            employeeList = (ArrayList<employee.webservice.Employee>) operations.readEmployee().getEmployeeArrayList();
        } catch (SOAPFaultException e) {
            logger.error(resourceBundleError.getString(e.getFault().getFaultString()));
        }
        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            employee.webservice.Employee employee = employeeList.get(index);
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
    }

    //Prints Address object
    static public void printAddressDetails(employee.webservice.Address address) {
        System.out.println("    House Name: " + address.getHouseName());
        System.out.println("    Street Name: " + address.getStreetName());
        System.out.println("    City: " + address.getCity());
        System.out.println("    State: " + address.getState());
        System.out.println("    Pincode: " + address.getPincode());
    }

    //    Print Employee Based On Pincode
    static void displayEmployeeDetails(Integer pincode)  {
        logger.info("Displayed Employee Details Based On Pincode");
        System.out.println("Employee Details by pincode Are");
//        operations = new DataBaseRepository();
        ArrayList<employee.webservice.Employee> employeeList = null;
        try {
            employeeList = (ArrayList<employee.webservice.Employee>) operations.readEmployeeByPincode(pincode).getEmployeeArrayList();
        } catch (SOAPFaultException e) {
            logger.error(resourceBundleError.getString(e.getFault().getFaultString()));
        }
        int size = employeeList.size();
        for (int index = 0; index < size; index++) {
            employee.webservice.Employee employee = employeeList.get(index);
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

    }

    static void displayEmployeeDetails(long employeeID)  {
//        operations = new DataBaseRepository();
        System.out.println(resourceBundle.getString("app.employee.details"));
        employee.webservice.Employee employee= new employee.webservice.Employee();
        try {
            employee =  operations.getEmployee(employeeID);
        } catch (SOAPFaultException e) {
            logger.error(resourceBundleError.getString(e.getFault().getFaultString()));
        }
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


}
