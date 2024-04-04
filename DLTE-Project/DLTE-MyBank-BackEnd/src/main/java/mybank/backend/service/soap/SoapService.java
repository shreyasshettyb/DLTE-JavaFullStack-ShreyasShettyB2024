package mybank.backend.service.soap;


import mybank.db.dao.dltemybankdaolayer.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.service.RepositoryMyBank;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.deposits.ServiceStatus;
import services.deposits.ViewAllDepositsAvailableRequest;
import services.deposits.ViewAllDepositsAvailableResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;

@ComponentScan("mybank.db.dao.dltemybankdaolayer")
@Endpoint
public class SoapService {

    private final String url = "http://deposits.services";

    @Autowired
    MyBankRemote soapService;

    @PayloadRoot(namespace = url, localPart = "viewAllDepositsAvailableRequest")
    @ResponsePayload
    public ViewAllDepositsAvailableResponse ViewAllDepositsAvailable(@RequestPayload ViewAllDepositsAvailableRequest viewAllDepositsAvailableRequest) {
        ViewAllDepositsAvailableResponse viewAllDepositsAvailableResponse = new ViewAllDepositsAvailableResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            String message = Arrays.toString(soapService.availableDeposits().toArray());
        } catch (SQLSyntaxErrorException e) {
//            serviceStatus.setStatus(Response.SC_INTERNAL_SERVER_ERROR);
        } catch (DepositsException e) {
//            serviceStatus.setStatus(ResponseEntity.);
        }
//        if (message.contains("Invalid loan"))

            serviceStatus.setStatus("SUCCESS");
//        serviceStatus.setMessage(message);
        viewAllDepositsAvailableResponse.setServiceStatus(serviceStatus);
        return viewAllDepositsAvailableResponse;
    }

}
