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
import java.sql.SQLException;
import java.util.ArrayList;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeSoapService {
    private Operations operations;

    public EmployeeSoapService() throws ConnectionException {
        operations = new DataBaseRepository();
    }

    @WebMethod
    public String createEmployee(@WebParam(name = "Employee") Employee employee) {
        String output = "";
        try {
            operations = new DataBaseRepository();
            output = operations.create(employee);
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeExistException e) {
            e.printStackTrace();
        }
        return output;
    }

    @WebMethod
    @WebResult(name = "GroupOfEmployee")
    public GroupOfEmployee readEmployee() {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.read();
            groupOfEmployee.setEmployeeArrayList(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoEmployeeFoundException e) {
            e.printStackTrace();
        }
        return groupOfEmployee;
    }

    @WebMethod
    @WebResult(name = "GroupOfEmployee")
    public GroupOfEmployee readEmployeeByPincode(@WebParam(name = "pincode")Integer pincode) {
        GroupOfEmployee groupOfEmployee = new GroupOfEmployee();
        try {
            operations = new DataBaseRepository();
            ArrayList<Employee> employees = operations.filterByPincode(pincode);
            groupOfEmployee.setEmployeeArrayList(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoEmployeeFoundException e) {
            e.printStackTrace();
        }
        return groupOfEmployee;
    }

}
