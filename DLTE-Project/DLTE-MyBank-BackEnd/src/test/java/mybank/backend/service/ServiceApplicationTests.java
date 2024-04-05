package mybank.backend.service;

import mybank.backend.service.soap.SoapService;
import mybank.db.dao.dltemybankdaolayer.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.deposits.ViewAllDepositsAvailableRequest;
import services.deposits.ViewAllDepositsAvailableResponse;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ServiceApplicationTests {

    @MockBean
    MyBankRemote soapService;

    @InjectMocks
    SoapService soap;

    @Test
    public void testListAllDeposits() throws DepositsException, SQLException {
        DepositsAvailable depositsAvailable1 = new DepositsAvailable(1000001, "FD", "Lump Sum", "Standard Fixed Deposit", 10.2);
        DepositsAvailable depositsAvailable2 = new DepositsAvailable(1000002, "RD", "Recurring", "Standard Recurring Deposit", 9.2);
        DepositsAvailable depositsAvailable3 = new DepositsAvailable(1000003, "Senior Citizen FD", "Lump Sum", "Senior Citizen Fixed Deposit", 11.5);
        DepositsAvailable depositsAvailable4 = new DepositsAvailable(1000004, "Employee FD", "Lump Sum", "Employee Fixed Deposit", 10.7);
        DepositsAvailable depositsAvailable5 = new DepositsAvailable(1000005, "Women Empowerment RD", "Recurring", "Women Empowerment Recurring Deposit", 10.2);

        List<DepositsAvailable> depositsAvailableList = Stream.of(depositsAvailable1, depositsAvailable2, depositsAvailable3, depositsAvailable4, depositsAvailable5).collect(Collectors.toList());

        when(soapService.availableDeposits()).thenReturn(depositsAvailableList);

        ViewAllDepositsAvailableRequest request = new ViewAllDepositsAvailableRequest();
        ViewAllDepositsAvailableResponse response = soap.ViewAllDepositsAvailable(request);

        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        assertTrue(depositsAvailableList.get(1).getDepositId() == response.getDepositsAvailable().get(1).getDepositId());
    }


}
