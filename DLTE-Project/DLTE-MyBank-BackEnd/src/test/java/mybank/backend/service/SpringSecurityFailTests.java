package mybank.backend.service;

import mybank.backend.service.auth.CustomerFailureHandler;
import mybank.backend.service.auth.CustomerSuccessHandler;
import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SpringSecurityFailTests {
    @Mock
    private CustomerAuthService myBankOfficialsService;

    @InjectMocks
    private CustomerSuccessHandler successHandler;

    @InjectMocks
    private CustomerFailureHandler failureHandler;

    @Mock
    private AuthenticationException exception;

    @Test
    public void testAuthenticationFailureCredentialsWrong() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
//        AuthenticationException exception = new BadCredentialsException("Invalid credentials");

        Customer customer = new Customer();
        customer.setUsername("shreyas12");
        customer.setCustomerStatus("active");
        customer.setAttempts(2);; // Assuming maximum attempts are 3
//        when(request.getParameter("username")).thenReturn("shreyas12");
        when(myBankOfficialsService.findByUsername("shreyas12")).thenReturn(customer);

        request.setParameter("username","shreyas12");
        failureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/?error=Invalid credentials 1 attempts left",response.getRedirectedUrl());
    }

    @Test
    public void testAuthenticationFailureAttemptsExceeded() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
//        AuthenticationException exception = new BadCredentialsException("Invalid credentials");

        Customer customer = new Customer();
        customer.setUsername("shreyas12");
        customer.setCustomerStatus("active");
        customer.setAttempts(3);; // Assuming maximum attempts are 3
//        when(request.getParameter("username")).thenReturn("shreyas12");
        when(myBankOfficialsService.findByUsername("shreyas12")).thenReturn(customer);

        request.setParameter("username","shreyas12");
        failureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/?error=Max Attempts reached account is suspended",response.getRedirectedUrl());
    }
    @Test
    public void testAuthenticationFailureUserNotExists() throws IOException, ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        AuthenticationException exception = new UsernameNotFoundException("User not exists");

        String username = "nonExistingUser";
        when(myBankOfficialsService.findByUsername(username)).thenReturn(null);

        failureHandler.onAuthenticationFailure(request, response, exception);

        assertEquals("/?error=Username not found/Does not Exist", response.getRedirectedUrl());
    }

}
