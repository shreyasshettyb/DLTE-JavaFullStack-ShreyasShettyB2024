package mybank.backend.service.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.deposits.ServiceStatus;
import services.deposits.ViewAllDepositsAvailableRequest;
import services.deposits.ViewAllDepositsAvailableResponse;

@Endpoint
public class SoapService {

        private final String url="http://deposits.services";
        @Autowired
    com.example.demo.DemoApplication

        @PayloadRoot(namespace = url,localPart = "ViewAllDepositsAvailableRequest")
        @ResponsePayload
//        public ViewAllDepositsAvailableResponse ViewAllDepositsAvailable(@RequestPayload ViewAllDepositsAvailableRequest viewAllDepositsAvailableRequest){
                ViewAllDepositsAvailableResponse viewAllDepositsAvailableResponse=new ViewAllDepositsAvailableResponse();
//            ServiceStatus serviceStatus=new ServiceStatus();
            String message = soapService.ViewAllDepositsAvailable(callDeletionRequest.getLoanId());
            if(message.contains("Invalid loan"))
                serviceStatus.setStatus("FAILURE");
            else
                serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(message);
            deletionResponse.setServiceStatus(serviceStatus);
            return deletionResponse;
        }

}
