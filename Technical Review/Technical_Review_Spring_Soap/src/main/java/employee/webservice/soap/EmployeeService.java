package employee.webservice.soap;

import employeebackend.service.exceptions.EmployeeExistException;
import employeebackend.service.exceptions.NoEmployeeFoundException;
import employeebackend.service.exceptions.ValidationException;
import employeebackend.service.interfaces.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ComponentScan("employeebackend.service")
@Endpoint
public class EmployeeService {

    private final String url = "http://employee.services";
    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private Operations operations;

    @PayloadRoot(namespace = url, localPart = "readRequest")
    @ResponsePayload
    public ReadResponse readEmployee(@RequestPayload ReadRequest readRequest) {
        ReadResponse readEmployeeResponse = new ReadResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> returnEmployee = new ArrayList<>();

        List<employeebackend.service.entity.Employee> received = null;
        try {
            received = operations.read();
        } catch (SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage("Failed To read Employee Details");
            logger.error(e.getMessage());
        } catch (NoEmployeeFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("No Employee record is present");
            logger.error(e.getMessage());
        }

        Iterator<employeebackend.service.entity.Employee> iterator = received.iterator();

        while (iterator.hasNext()) {
            Employee currentEmployee = new Employee();
            BeanUtils.copyProperties(iterator.next(), currentEmployee);
            returnEmployee.add(currentEmployee);
        }

        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee Details were fetched");

        readEmployeeResponse.setServiceStatus(serviceStatus);
        readEmployeeResponse.getEmployee().addAll(returnEmployee);

        return readEmployeeResponse;
    }

    @PayloadRoot(namespace = url, localPart = "readByIdRequest")
    @ResponsePayload
    public ReadByIdResponse readById(@RequestPayload ReadByIdRequest readByIdRequest) {
        ReadByIdResponse readByIdResponse = new ReadByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> returnEmployee = new ArrayList<>();

        List<employeebackend.service.entity.Employee> received = null;
        try {
            received = operations.read();
            received = received.stream().filter(employee -> employee.getEmployeeID().equals(readByIdRequest.getId())).collect(Collectors.toList());
        } catch (SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage("Failed To read Employee Details");
            logger.error(e.getMessage());
        } catch (NoEmployeeFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("No Employee record is present");
            logger.error(e.getMessage());
        }

        //fix no need iterator
        Iterator<employeebackend.service.entity.Employee> iterator = received.iterator();

        while (iterator.hasNext()) {
            Employee currentEmployee = new Employee();
            BeanUtils.copyProperties(iterator.next(), currentEmployee);
            returnEmployee.add(currentEmployee);
        }

        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee Details were fetched");

        readByIdResponse.setServiceStatus(serviceStatus);
        readByIdResponse.setEmployee(returnEmployee.get(0));

        return readByIdResponse;
    }

    @PayloadRoot(namespace = url, localPart = "filterByPincodeRequest")
    @ResponsePayload
    public FilterByPincodeResponse filterByPincode(@RequestPayload FilterByPincodeRequest filterByPincodeRequest) {
        FilterByPincodeResponse filterByPincodeResponse = new FilterByPincodeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> returnEmployee = new ArrayList<>();

        List<employeebackend.service.entity.Employee> received = null;
        try {
            received = operations.filterByPincode(filterByPincodeRequest.getPincode());
        } catch (SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage("Failed To read Employee Details");
            logger.error(e.getMessage());
        } catch (NoEmployeeFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("No Employee record is present");
            logger.error(e.getMessage());
        }

        Iterator<employeebackend.service.entity.Employee> iterator = received.iterator();

        while (iterator.hasNext()) {
            Employee currentEmployee = new Employee();
            BeanUtils.copyProperties(iterator.next(), currentEmployee);
            returnEmployee.add(currentEmployee);
        }

        serviceStatus.setStatus(HttpServletResponse.SC_OK);
        serviceStatus.setMessage("Employee Details were fetched");

        filterByPincodeResponse.setServiceStatus(serviceStatus);
        filterByPincodeResponse.getEmployee().addAll(returnEmployee);

        return filterByPincodeResponse;
    }

    @PayloadRoot(namespace = url, localPart = "createRequest")
    @ResponsePayload
    public CreateResponse addNewEmployee(@RequestPayload CreateRequest createRequest) {
        CreateResponse createResponse = new CreateResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Employee actual = createRequest.getEmployee();
        employeebackend.service.entity.Employee daoEmployee = new employeebackend.service.entity.Employee();
        employeebackend.service.entity.Address temporaryAddress = new employeebackend.service.entity.Address();
        employeebackend.service.entity.Address permanentAddress = new employeebackend.service.entity.Address();
        BeanUtils.copyProperties(actual, daoEmployee, "permanentAddress", "temporaryAddress");
        BeanUtils.copyProperties(actual.getTemporaryAddress(),temporaryAddress);
        BeanUtils.copyProperties(actual.getPermanentAddress(),permanentAddress);
        daoEmployee.setPermanentAddress(permanentAddress);
        daoEmployee.setTemporaryAddress(temporaryAddress);
        String result = "";
        try {
            result = operations.create(daoEmployee);
        } catch (ValidationException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_CONFLICT);
            createResponse.setResult(e.getMessage());
            serviceStatus.setMessage(actual.getEmployeeID() + " not inserted");
            logger.error(e.getMessage());
        } catch (SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            createResponse.setResult("Error!!! Employee was not Inserted");
            serviceStatus.setMessage(actual.getEmployeeID() + " not inserted");
            logger.error(e.getMessage());
        } catch (EmployeeExistException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            createResponse.setResult("Error!!! Employee already exits");
            serviceStatus.setMessage(actual.getEmployeeID() + " is already present");
            logger.error(e.getMessage());
        }

        if (result == "SQL-000") {
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            BeanUtils.copyProperties(daoEmployee, actual);
            createResponse.setResult("Employee Added Successfully");
            serviceStatus.setMessage(actual.getEmployeeID() + " has been inserted");
            logger.info(actual.getEmployeeID() + "inserted");
        } else {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            createResponse.setResult(result + ":Error!!! Employee was not Inserted");
            serviceStatus.setMessage(actual.getEmployeeID() + " not inserted");
            logger.info(result);
        }
        createResponse.setServiceStatus(serviceStatus);
        return createResponse;
    }
}
