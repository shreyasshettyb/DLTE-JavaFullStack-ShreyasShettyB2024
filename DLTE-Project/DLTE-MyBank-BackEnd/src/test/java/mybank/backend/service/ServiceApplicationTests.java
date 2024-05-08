package mybank.backend.service;

import mybank.backend.service.rest.MyBankRestController;
import mybank.backend.service.soap.SoapService;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.remotes.MyBankRemote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import services.deposits.ViewAllDepositsAvailableRequest;
import services.deposits.ViewAllDepositsAvailableResponse;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static mybank.db.dao.dltemybankdaolayer.DlteMybankDaoLayerApplication.myBankRemote;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ServiceApplicationTests {

    private static DepositsAvailable depositsAvailable1 = new DepositsAvailable();
    private static DepositsAvailable depositsAvailable2 = new DepositsAvailable();
    private static DepositsAvailable depositsAvailable3 = new DepositsAvailable();
    private static List<DepositsAvailable> depositsAvailableList = Stream.of(depositsAvailable1, depositsAvailable2, depositsAvailable3).collect(Collectors.toList());
    @Mock
    MyBankRemote service;
    @InjectMocks
    SoapService soap;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    MyBankRestController myBankRestController;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private SpringApplicationBuilder springApplicationBuilder;

    @BeforeEach
    public void setup() {
        depositsAvailable1 = new DepositsAvailable();
        depositsAvailable1.setDepositId(1000001);
        depositsAvailable1.setDepositName("FD");
        depositsAvailable1.setDepositType("Lump Sum");
        depositsAvailable1.setDepositDescription("Standard Fixed Deposit");
        depositsAvailable1.setDepositRoi(10.2);

        depositsAvailable2 = new DepositsAvailable();
        depositsAvailable2.setDepositId(1000002);
        depositsAvailable2.setDepositName("RD");
        depositsAvailable2.setDepositType("Recurring");
        depositsAvailable2.setDepositDescription("Standard Recurring Deposit");
        depositsAvailable2.setDepositRoi(9.2);

        depositsAvailable3 = new DepositsAvailable();
        depositsAvailable3.setDepositId(1000003);
        depositsAvailable3.setDepositName("Senior Citizen FD");
        depositsAvailable3.setDepositType("Lump Sum");
        depositsAvailable3.setDepositDescription("Senior Citizen Fixed Deposit");
        depositsAvailable3.setDepositRoi(11.5);
        depositsAvailableList = Stream.of(depositsAvailable1, depositsAvailable2, depositsAvailable3).collect(Collectors.toList());

    }

    //Endpoint Testing for testListAllDeposits -Pass
    @Test
    public void testListAllDeposits() throws DepositsException, SQLException {
        when(service.availableDeposits()).thenReturn(depositsAvailableList);

        ViewAllDepositsAvailableRequest request = new ViewAllDepositsAvailableRequest();
        ViewAllDepositsAvailableResponse response = soap.ViewAllDepositsAvailable(request);

        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        assertTrue(depositsAvailableList.get(1).getDepositId() == response.getDepositsAvailable().get(1).getDepositId());
    }

    //DepositException Testing -Pass
    @Test
    public void testDepositException() throws DepositsException, SQLException {
        when(service.availableDeposits()).thenThrow(DepositsException.class);

        ViewAllDepositsAvailableRequest request = new ViewAllDepositsAvailableRequest();
        ViewAllDepositsAvailableResponse response = soap.ViewAllDepositsAvailable(request);

        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
    }

    //EndPoint Test - Pass
    @Test
    @WithMockUser(username = "shreyas12")
    public void testRestDepositAvailed() throws Exception {
        String request = "{\n " +
                "\n\"customerId\": 100002," +
                "\n\"depositId\": 1000002," +
                "\n\"depositAmount\": 40000.0," +
                "\n\"depositDuration\": 13," +
                "\n\"depositMaturity\": \"2024-06-09\","+
                "\n\"depositMaturityAmt\": 48000.0\n"+
                "}";

        mockMvc.perform(post("/mybank/deposits/avail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isOk());
    }

    //Fail Bean Validation
    @Test
    @WithMockUser(username = "shreyas12")
    public void testBeanValidation() throws Exception {
        String request = "{\n " +
                "\"depositAvailId\": 2," +
                "\n\"customerId\": 100002," +
                "\n\"depositId\": null," +
                "\n\"depositAmount\": 40000.0," +
                "\n\"depositDuration\": 13," +
                "\n\"depositMaturity\": \"2024-06-09\"\n" +
                "}";
        mockMvc.perform(post("/mybank/deposits/avail").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk());
    }

    @Test
    public void testConfigure() {
        openMocks(this);

        ServletInitializer servletInitializer = new ServletInitializer();

        Class<ServiceApplication> applicationClass = ServiceApplication.class;
        when(springApplicationBuilder.sources(applicationClass)).thenReturn(springApplicationBuilder);

        SpringApplicationBuilder returnedBuilder = servletInitializer.configure(springApplicationBuilder);

        verify(springApplicationBuilder).sources(applicationClass);

        assert returnedBuilder == springApplicationBuilder;
    }

}
