package employee.webservice;

import employeebackend.exceptions.ConnectionException;

import javax.xml.ws.Endpoint;

public class EmployeeEndpoint {
    private static String url="http://localhost:1234/employee";
    public static void main(String[] args) throws ConnectionException {
        EmployeeSoap employeeSoap =new EmployeeSoap();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url, employeeSoap);
    }
}
