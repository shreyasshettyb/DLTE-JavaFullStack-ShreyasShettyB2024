//package mybank.backend.service;
//
//import mybank.backend.service.rest.MyBankRestController;
//import mybank.backend.service.soap.SoapService;
//import mybank.db.dao.dltemybankdaolayer.MyBankRemote;
//import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
//import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import services.deposits.ViewAllDepositsAvailableRequest;
//import services.deposits.ViewAllDepositsAvailableResponse;
//
//import javax.servlet.http.HttpServletResponse;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//class ServiceApplicationTests {
//
//    @MockBean
//    MyBankRemote service;
//
//    @InjectMocks
//    SoapService soap;
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    MyBankRestController myBankRestController;
//    @Autowired
//    private MockMvc mockMvc;
//
//    //Endpoint Testing for testListAllDeposits -Pass
//    @Test
//    public void testListAllDeposits() throws DepositsException, SQLException {
//        DepositsAvailable depositsAvailable1 = new DepositsAvailable(1000001, "FD", "Lump Sum", "Standard Fixed Deposit", 10.2);
//        DepositsAvailable depositsAvailable2 = new DepositsAvailable(1000002, "RD", "Recurring", "Standard Recurring Deposit", 9.2);
//        DepositsAvailable depositsAvailable3 = new DepositsAvailable(1000003, "Senior Citizen FD", "Lump Sum", "Senior Citizen Fixed Deposit", 11.5);
//        DepositsAvailable depositsAvailable4 = new DepositsAvailable(1000004, "Employee FD", "Lump Sum", "Employee Fixed Deposit", 10.7);
//        DepositsAvailable depositsAvailable5 = new DepositsAvailable(1000005, "Women Empowerment RD", "Recurring", "Women Empowerment Recurring Deposit", 10.2);
//
//        List<DepositsAvailable> depositsAvailableList = Stream.of(depositsAvailable1, depositsAvailable2, depositsAvailable3, depositsAvailable4, depositsAvailable5).collect(Collectors.toList());
//
//        when(service.availableDeposits()).thenReturn(depositsAvailableList);
//
//        ViewAllDepositsAvailableRequest request = new ViewAllDepositsAvailableRequest();
//        ViewAllDepositsAvailableResponse response = soap.ViewAllDepositsAvailable(request);
//
//        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
//        assertTrue(depositsAvailableList.get(1).getDepositId() == response.getDepositsAvailable().get(1).getDepositId());
//    }
//
//    //DepositException Testing -Pass
////    @Test
//    public void testDepositException() throws DepositsException, SQLException {
//        when(service.availableDeposits()).thenThrow(DepositsException.class);
//
//        ViewAllDepositsAvailableRequest request = new ViewAllDepositsAvailableRequest();
//        ViewAllDepositsAvailableResponse response = soap.ViewAllDepositsAvailable(request);
//
//        assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getServiceStatus().getStatus());
//    }
//
//
//    //EndPoint Test - Pass
//    @Test
//    @WithMockUser(username = "shreyas12")
//    public void testRestDepositAvailed() throws Exception {
//        String request = "{\n " +
//                "\"depositAvailId\": 2," +
//                "\n\"customerId\": 100002," +
//                "\n\"depositId\": 1000002," +
//                "\n\"depositAmount\": 40000.0," +
//                "\n\"depositDuration\": 13," +
//                "\n\"depositMaturity\": \"2024-06-09\"\n" +
//                "}";
//        mockMvc.perform(post("/mybank/deposits/avail").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isOk());
//    }
//
//    //Fail Bean Validation
////    @Test
////    @WithMockUser(username = "shreyas12")
//    public void testBeanValidation() throws Exception {
//        String request = "{\n " +
//                "\"depositAvailId\": 2," +
//                "\n\"customerId\": 100002," +
//                "\n\"depositId\": null," +
//                "\n\"depositAmount\": 40000.0," +
//                "\n\"depositDuration\": 13," +
//                "\n\"depositMaturity\": \"2024-06-09\"\n" +
//                "}";
//        mockMvc.perform(post("/mybank/deposits/avail").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isOk());
//    }
//
//
//}
