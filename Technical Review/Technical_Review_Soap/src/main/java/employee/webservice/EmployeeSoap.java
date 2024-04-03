package employee.webservice;

import employeebackend.entity.Employee;
import employeebackend.exceptions.ConnectionException;
import employeebackend.exceptions.EmployeeExistException;
import employeebackend.exceptions.NoEmployeeFoundException;
import employeebackend.exceptions.ValidationException;
import employeebackend.interfaces.Operations;
import employeebackend.repository.DataBaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class  EmployeeSoap {
    private Logger logger = LoggerFactory.getLogger(EmployeeSoap.class);
    private Operations operations;
    private SOAPFault soapFault;

    private String exceptionHandler(Exception e) {
        if (e.getClass().equals(ValidationException.class)) {
            return "ValidationException";
        } else if (e.getClass().equals(EmployeeExistException.class)) {
            return "EmployeeExistException";
        } else if (e.getClass().equals(ConnectionException.class)) {
            return "ConnectionException";
        } else if (e.getClass().equals(SQLException.class)) {
            return "SQLException";
        } else if (e.getClass().equals(NoEmployeeFoundException.class)) {
            return "NoEmployeeFoundException";
        } else {
            return "Unknown Exception";
        }
    }

    //SOAP wethod which creates Employee
    @WebMethod
    public String createEmployee(@WebParam(name = "Employee") Employee employee) {
        String output = "";
        try {
            operations = new DataBaseRepository();
            output = operations.create(employee);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return output;
    }

    //SOAP wethod which reads Employee
    @WebMethod
    @WebResult(name = "GroupOfEmployee")
    public GroupOfEmployee readEmployee() {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.read();
            groupOfEmployee.setEmployeeArrayList(employees);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return groupOfEmployee;
    }

    //SOAP wethod which reads Employee based on pincode
    @WebMethod
    @WebResult(name = "GroupOfEmployee")
    public GroupOfEmployee readEmployeeByPincode(@WebParam(name = "pincode") Integer pincode) {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.read();
            employees = (ArrayList<Employee>) employees.stream().filter(each -> (each.getTemporaryAddress().getPincode().equals(pincode)) || (each.getPermanentAddress().getPincode().equals(pincode))).collect(Collectors.toList());
            groupOfEmployee.setEmployeeArrayList(employees);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return groupOfEmployee;
    }

    @WebMethod
    @WebResult(name = "Employee")
    public Employee getEmployee(@WebParam(name = "employee_id") long employee_id) {
        Employee employee;
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.read();
            employee = employees.stream().filter(each -> (each.getEmployeeID().equals(employee_id))).findFirst().orElse(null);
        } catch (Exception e) {
            logger.error(e.getMessage() + e.getClass().getName());
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(exceptionHandler(e));
                soapFault.setFaultString(e.getMessage());
            } catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return employee;
    }

}
