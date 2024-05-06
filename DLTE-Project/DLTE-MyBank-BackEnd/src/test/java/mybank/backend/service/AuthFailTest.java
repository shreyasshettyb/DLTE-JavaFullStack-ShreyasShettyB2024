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

}

