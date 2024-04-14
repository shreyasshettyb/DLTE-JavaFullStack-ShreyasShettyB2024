package mybank.backend.service.soap;


import mybank.db.dao.dltemybankdaolayer.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.deposits.ServiceStatus;
import services.deposits.ViewAllDepositsAvailableRequest;
import services.deposits.ViewAllDepositsAvailableResponse;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@ComponentScan("mybank.db.dao.dltemybankdaolayer")
@Endpoint
public class SoapService {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private final String url = "http://deposits.services";

    @Autowired
    MyBankRemote soapService;

    private Logger logger = LoggerFactory.getLogger(SoapService.class);


    //Payload for Getting lis of all Available deposits
    @PayloadRoot(namespace = url, localPart = "viewAllDepositsAvailableRequest")
    @ResponsePayload
    public ViewAllDepositsAvailableResponse ViewAllDepositsAvailable(@RequestPayload ViewAllDepositsAvailableRequest viewAllDepositsAvailableRequest) {
        ViewAllDepositsAvailableResponse viewAllDepositsAvailableResponse = new ViewAllDepositsAvailableResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            List<DepositsAvailable> depositList = soapService.availableDeposits();
            List<services.deposits.DepositsAvailable> depositListOutput = new ArrayList<>();
            depositList.forEach(each -> {
                services.deposits.DepositsAvailable depositAvailable = new services.deposits.DepositsAvailable();
                BeanUtils.copyProperties(each, depositAvailable);
                depositListOutput.add(depositAvailable);
            });
            viewAllDepositsAvailableResponse.getDepositsAvailable().addAll(depositListOutput);
            logger.info(resourceBundle.getString("app.soap.success"));
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(resourceBundle.getString("app.soap.error.access"));
            logger.error(e.toString());
        } catch (DepositsException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(resourceBundle.getString("app.soap.error.empty"));
            logger.error(e.toString());
        }
        viewAllDepositsAvailableResponse.setServiceStatus(serviceStatus);
        return viewAllDepositsAvailableResponse;
    }

}
