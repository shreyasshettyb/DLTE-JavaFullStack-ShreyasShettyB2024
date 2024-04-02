
package employee.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EmployeeSoapService", targetNamespace = "http://webservice.employee/", wsdlLocation = "http://localhost:1234/employee?wsdl")
public class EmployeeSoapService
    extends Service
{

    private final static URL EMPLOYEESOAPSERVICE_WSDL_LOCATION;
    private final static WebServiceException EMPLOYEESOAPSERVICE_EXCEPTION;
    private final static QName EMPLOYEESOAPSERVICE_QNAME = new QName("http://webservice.employee/", "EmployeeSoapService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:1234/employee?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EMPLOYEESOAPSERVICE_WSDL_LOCATION = url;
        EMPLOYEESOAPSERVICE_EXCEPTION = e;
    }

    public EmployeeSoapService() {
        super(__getWsdlLocation(), EMPLOYEESOAPSERVICE_QNAME);
    }

    public EmployeeSoapService(WebServiceFeature... features) {
        super(__getWsdlLocation(), EMPLOYEESOAPSERVICE_QNAME, features);
    }

    public EmployeeSoapService(URL wsdlLocation) {
        super(wsdlLocation, EMPLOYEESOAPSERVICE_QNAME);
    }

    public EmployeeSoapService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EMPLOYEESOAPSERVICE_QNAME, features);
    }

    public EmployeeSoapService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EmployeeSoapService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EmployeeSoap
     */
    @WebEndpoint(name = "EmployeeSoapPort")
    public EmployeeSoap getEmployeeSoapPort() {
        return super.getPort(new QName("http://webservice.employee/", "EmployeeSoapPort"), EmployeeSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EmployeeSoap
     */
    @WebEndpoint(name = "EmployeeSoapPort")
    public EmployeeSoap getEmployeeSoapPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.employee/", "EmployeeSoapPort"), EmployeeSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EMPLOYEESOAPSERVICE_EXCEPTION!= null) {
            throw EMPLOYEESOAPSERVICE_EXCEPTION;
        }
        return EMPLOYEESOAPSERVICE_WSDL_LOCATION;
    }

}
