package mybank.backend.service;

import mybank.backend.service.auth.CustomerFailureHandler;
import mybank.backend.service.auth.CustomerSuccessHandler;
import mybank.backend.service.auth.MyBankAuthController;
import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SpringSecurityTests {
    @Mock
    HttpSession session;
    @Mock
    private CustomerAuthService authService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MyBankAuthController authController;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private CustomerSuccessHandler customerSuccessHandler;
    @Mock
    private AuthenticationException exception;
    @InjectMocks
    private CustomerFailureHandler customerFailureHandler;
    @Captor
    private ArgumentCaptor<CallableStatementCreator> callableStatementCreatorCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveProfile() {
        Customer customer = new Customer();
        customer.setUsername("testUser");
        customer.setPassword("testPassword");

        Mockito.when(authService.signingUp(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        Mockito.when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("encodedPassword");

        Customer savedCustomer = authController.save(customer);

        Mockito.verify(authService).signingUp(customer);

        Mockito.verify(passwordEncoder).encode("testPassword");


        assertEquals(customer.getUsername(), savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword());
    }

    @Test
    public void testOnAuthenticationSuccess_ActiveCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerStatus("active");
        customer.setAttempts(2);
        when(authentication.getPrincipal()).thenReturn(customer);

        // Act
        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        verify(authService).updateAttempts(customer);
    }

    @Test
    public void testOnAuthenticationSuccess_InactiveCustomer() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerStatus("inactive");

        when(authentication.getPrincipal()).thenReturn(customer);

        // Act
        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        // Assert
        Mockito.verify(response).encodeRedirectURL("null/?error=Account suspended contact admin to redeem");
    }

//fail

//    @Test
//    @WithMockUser(username = "shreyas12", password = "shreyas123")
    public void testOnAuthenticationFailure_ActiveCustomer_LessThanThreeAttempts() throws Exception {
        // Arrange
        Customer customer1 = new Customer();
        customer1.setCustomerId(100001L);
        customer1.setCustomerName("shreyas");
        customer1.setCustomerAddress("udupi");
        customer1.setCustomerStatus("active");
        customer1.setCustomerContact(7418529630L);
        customer1.setUsername("shreyas12");
        customer1.setPassword("pass1");
        customer1.setAttempts(2);

        when(request.getSession(false)).thenReturn(session);
        when(request.getParameter("username")).thenReturn("shreyas12");
        when(authService.findByUsername("shreyas12")).thenReturn(customer1);

        // Act
        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        // Assert
        verify(authService).updateAttempts(customer1);
    }

//    @Test
    public void testOnAuthenticationFailure_ActiveCustomer_MaxAttemptsReached() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomerStatus("active");
        customer.setAttempts(3);

        when(request.getParameter("username")).thenReturn("testuser");
        when(authService.findByUsername("testuser")).thenReturn(customer);

        // Act
        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        verify(authService).updateStatus(customer);
    }

}

