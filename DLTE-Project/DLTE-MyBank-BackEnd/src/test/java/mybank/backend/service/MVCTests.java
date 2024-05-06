package mybank.backend.service;


import mybank.backend.service.mvc.MVCController;
import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.remotes.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MVCTests {

    @Autowired
    MVCController mvcController;

    @Mock
    MyBankRemote repositoryMyBank;
    @Mock
    CustomerAuthService customerAuthService;
    @Mock
    PasswordEncoder passwordEncoder;
    private MockMvc mockMvc2;

    @BeforeEach
    void setup() {
        mockMvc2 = MockMvcBuilders.standaloneSetup(mvcController).build();
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testDashBoardView() {
        String viewName = mvcController.dashboard();
        assertEquals("dashboard", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testIndexView() {
        String viewName = mvcController.index();
        assertEquals("index", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testRedirectLoginView() {
        String viewName = mvcController.redirectLogin();
        assertEquals("index", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testViewDepositsView() {
        String viewName = mvcController.viewDeposits();
        assertEquals("viewDeposits", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testAvailDepositView() throws DepositsException, SQLException {
        Model model = mock(Model.class);
        String result = mvcController.availDeposit("FD", model);
        assertEquals("depositForm", result);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testErrorView() {
        String viewName = mvcController.errorPage();
        assertEquals("error", viewName);
    }

    @Test
    @WithMockUser(username = "shreyas12")
    public void testAvailDepositError1View() {
        Model model = mock(Model.class);
        String result = mvcController.availDeposit("", model);
        assertEquals("error?code=404&msg=Could not find Deposit Information", result);
    }

//    @Test
    void testCustomerName() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        customer.setCustomerName("Akash");
        customer.setCustomerAddress("banglore");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8277263396L);
        customer.setUsername("user");
        customer.setPassword("1234");
        customer.setAttempts(1);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("username");

        when(customerAuthService.findByUsername("username")).thenReturn(customer);

        mockMvc2.perform(get("/name"))
                .andExpect(status().isOk())
                .andExpect(content().string("Akash"));
    }

    @Test
    public void testPasswordMatch() {
        CustomerAuthService customerAuthServices = mock(CustomerAuthService.class);
        passwordEncoder = new BCryptPasswordEncoder();
        String username = "Akash";
        String rawPassword = "akash123";

        String encodedPassword = passwordEncoder.encode(rawPassword);

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(customerAuthServices.loadUserByUsername(username))
                .thenReturn(customer);

        UserDetails userDetails = customerAuthServices.loadUserByUsername(username);

        String enteredPassword = "akash123";

        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));
    }


}
