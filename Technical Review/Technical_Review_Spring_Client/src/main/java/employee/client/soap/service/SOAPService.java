package employee.client.soap.service;

import soap.webservice.*;

import java.util.HashMap;
import java.util.List;

public class SOAPService {

    public CreateResponse callCreateEmployee(SOAPConnector soapConnector, Employee employee){
        CreateRequest createRequest = new CreateRequest();
        createRequest.setEmployee(employee);
        return (CreateResponse) soapConnector.callWebService("http://localhost:8082/employeerepo",createRequest);
    }

    public ReadResponse callReadAll(SOAPConnector soapConnector){
        ReadRequest readRequest = new ReadRequest();
        return (ReadResponse) soapConnector.callWebService("http://localhost:8082/employeerepo",readRequest);
    }

    public ReadByIdResponse callReadById(SOAPConnector soapConnector,long Id){
        ReadByIdRequest readByIdRequest = new ReadByIdRequest();
        readByIdRequest.setId(Id);
        return (ReadByIdResponse) soapConnector.callWebService("http://localhost:8082/employeerepo",readByIdRequest);
    }

    public FilterByPincodeResponse filterByPincode(SOAPConnector soapConnector,Integer pincode){
        FilterByPincodeRequest filterByPincodeRequest = new FilterByPincodeRequest();
        filterByPincodeRequest.setPincode(pincode);
        return (FilterByPincodeResponse) soapConnector.callWebService("http://localhost:8082/employeerepo",filterByPincodeRequest);
    }

}
