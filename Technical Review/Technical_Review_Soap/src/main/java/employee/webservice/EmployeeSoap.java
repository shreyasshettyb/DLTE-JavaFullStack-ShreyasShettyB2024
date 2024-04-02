package employee.webservice;

import employeebackend.entity.Employee;
import employeebackend.exceptions.ConnectionException;
import employeebackend.exceptions.EmployeeExistException;
import employeebackend.exceptions.NoEmployeeFoundException;
import employeebackend.exceptions.ValidationException;
import employeebackend.interfaces.Operations;
import employeebackend.repository.DataBaseRepository;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.*;
import javax.xml.ws.WebFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeSoap {
    private Operations operations;
    private  SOAPFault soapFault;


    //SOAP wethod which creates Employee
    @WebMethod
    public String createEmployee(@WebParam(name = "Employee") Employee employee)  {
        String output = "";
        try {
            operations = new DataBaseRepository();
            output = operations.create(employee);
        } catch (EmployeeExistException e) {
            try {
                soapFault = SOAPFactory.newInstance().createFault();
                soapFault.setFaultCode(e.getClass().getName());
                soapFault.setFaultString(e.getMessage());
            }catch (SOAPException ex) {
                ex.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return output;
    }

    //SOAP wethod which reads Employee
    @WebMethod
    @WebResult(name = "GroupOfEmployee")
    public GroupOfEmployee readEmployee()  {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.read();
            groupOfEmployee.setEmployeeArrayList(employees);
        } catch (Exception exception) {
            try {
//                soapFault.setFaultCode(exception.getClass().getName());
                soapFault.setFaultString(exception.getMessage());
            } catch (SOAPException e) {
                e.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return groupOfEmployee;
    }

    //SOAP wethod which reads Employee based on pincode
    @WebMethod
    @WebResult(name = "GroupOfEmployee")
    public GroupOfEmployee readEmployeeByPincode(@WebParam(name = "pincode")Integer pincode)  {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.read();
            employees= (ArrayList<Employee>) employees.stream().filter(each->(each.getTemporaryAddress().getPincode().equals(pincode))||(each.getPermanentAddress().getPincode().equals(pincode))).collect(Collectors.toList());
            groupOfEmployee.setEmployeeArrayList(employees);
        }catch (Exception exception) {
            try {
//                soapFault.setFaultCode(exception.getClass().getName());
                soapFault.setFaultString(exception.getMessage());
            } catch (SOAPException e) {
                e.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return groupOfEmployee;
    }

    @WebMethod
    @WebResult(name = "Employee")
    public Employee getEmployee(@WebParam(name = "employee_id")long employee_id)  {
        Employee employee;
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.read();
            employee= employees.stream().filter(each->(each.getEmployeeID().equals(employee_id))).findFirst().orElse(new Employee());
        }catch (Exception exception) {
            try {
//                soapFault.setFaultCode(exception.getClass().getName());
                soapFault.setFaultString(exception.getMessage());
            } catch (SOAPException e) {
                e.printStackTrace();
            }
            throw new SOAPFaultException(soapFault);
        }
        return employee;
    }

}
