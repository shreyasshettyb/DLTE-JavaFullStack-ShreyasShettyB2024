package employee.webservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;

public class EmployeeEndpoint {
    private static String url="http://localhost:1234/employee";
    public static void main(String[] args)  {
        Logger logger = LoggerFactory.getLogger(EmployeeEndpoint.class);
        EmployeeSoap employeeSoap =new EmployeeSoap();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url, employeeSoap);
        logger.info("Endpoint started");
    }
}
