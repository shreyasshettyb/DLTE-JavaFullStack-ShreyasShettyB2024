package mybank.backend.service;

import mybank.backend.service.auth.CustomerFailureHandler;
import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.remotes.CustomerAuthInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthFailTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler;

    @InjectMocks
    private CustomerFailureHandler customerFailureHandler;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @Mock
    private AuthenticationException exception;

    @Mock
    private CustomerAuthInterface customerAuthInterface;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void onAuthenticationFailureTest() throws Exception {
        customerFailureHandler.setUseForward(false); // Disable the use of forward for testing
        Customer customer = new Customer();
        customer.setCustomerStatus("active");
        customer.setUsername("testUser");
        customer.setPassword("password");
        customer.setAttempts(2); // Assuming previous failed attempts
        when(customerAuthInterface.findByUsername("testUser")).thenReturn(customer);

        // Mocking the request parameters
        mockMvc.perform(MockMvcRequestBuilders.post("/login/")
                .param("username", "testUser")
                .param("password", "password"))
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/?error=Username not found/Does not Exist"));

    }


    @Test
    public void testOnAuthenticationFailure_ActiveCustomer_MaxAttemptsNotReached() throws Exception {
        // Arrange
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException exception = mock(AuthenticationException.class);
        Customer customer = new Customer();
        customer.setCustomerStatus("active");
        customer.setAttempts(2); // Max attempts not reached

        when(request.getParameter("username")).thenReturn("testuser");
        when(customerAuthInterface.findByUsername("testuser")).thenReturn(customer);

        // Act
        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        // Verify
        verify(customerAuthInterface, times(1)).findByUsername("testuser");
        verify(customerAuthInterface, times(1)).updateAttempts(customer);
        verify(simpleUrlAuthenticationFailureHandler, never()).setDefaultFailureUrl(anyString());
        verify(simpleUrlAuthenticationFailureHandler, never()).onAuthenticationFailure(request, response, exception);
    }

    @Test
    public void testOnAuthenticationFailure_ActiveCustomer_MaxAttemptsReached() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException exception = mock(AuthenticationException.class);
        Customer customer = new Customer();
        customer.setCustomerStatus("active");
        customer.setAttempts(3); // Max attempts reached

        when(request.getParameter("username")).thenReturn("testuser");
        when(customerAuthInterface.findByUsername("testuser")).thenReturn(customer);

        // Act
        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        // Verify
        verify(customerAuthInterface, times(1)).findByUsername("testuser");
        verify(customerAuthInterface, times(1)).updateStatus(customer);
    }

    @Test
    public void testOnAuthenticationFailure_SuspendedAccount() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException exception = mock(AuthenticationException.class);
        Customer customer = new Customer();
        customer.setCustomerStatus("suspended");

        when(request.getParameter("username")).thenReturn("testuser");
        when(customerAuthInterface.findByUsername("testuser")).thenReturn(customer);

        // Act
        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        // Verify
        verify(customerAuthInterface, times(1)).findByUsername("testuser");
//            verifyZeroInteractions(customerAuthInterface); // No interactions expected because the account is suspended
    }

    @Test
    public void testOnAuthenticationFailure_UserNotFound() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException exception = mock(AuthenticationException.class);

        when(request.getParameter("username")).thenReturn("testuser");
        when(customerAuthInterface.findByUsername("testuser")).thenReturn(null);

        // Act
        customerFailureHandler.onAuthenticationFailure(request, response, exception);

        // Verify
        verify(customerAuthInterface, times(1)).findByUsername("testuser");
//            verifyZeroInteractions(customerAuthInterface); // No interactions expected because user is not found
    }
}

