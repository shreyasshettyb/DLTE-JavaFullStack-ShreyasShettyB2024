package mybank.backend.service;

import mybank.backend.service.auth.CustomerFailureHandler;
import mybank.backend.service.auth.CustomerSuccessHandler;
import mybank.backend.service.mvc.MVCController;
import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.remotes.CustomerAuthInterface;
import mybank.db.dao.dltemybankdaolayer.remotes.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SpringSecuityTests {

        @Autowired
        CustomerAuthInterface customerAuthInterface;


        @Mock
        MyBankRemote myBankRemote;

        @InjectMocks
        CustomerFailureHandler failureHandler;

        @InjectMocks
        CustomerSuccessHandler successHandler;

        @InjectMocks
        MVCController mvcController;

        @Autowired
        private MockMvc mockMvc;

        @Test
        @WithMockUser(username = "shreyas12",password = "shreyas123")
        void onAuthenticationFailureTest1() throws Exception {
            MockHttpServletRequest request = new MockHttpServletRequest();
            MockHttpServletResponse response = new MockHttpServletResponse();


            // Mocking customer
            Customer customer = new Customer();
            customer.setCustomerId(100001L);
            customer.setCustomerName("shreyas");
            customer.setCustomerAddress("udupi");
            customer.setCustomerStatus("active");
            customer.setCustomerContact(7418529630L);
            customer.setUsername("shreyas12");
            customer.setPassword("pass1");
            customer.setAttempts(1);
            // Mocking service behavior
            when(customerAuthInterface.findByUsername("shreyas12")).thenReturn(customer);

            // Triggering the failure handler
            failureHandler.onAuthenticationFailure(request, response, new LockedException("Invalid credentials"));

            // Verifying that attempts are incremented
            verify(customerAuthInterface, times(1)).updateAttempts(customer);
        }

        @Test
        void onAuthenticationFailureTest2() throws Exception {
            // Similar to the previous test but with max attempts reached
        }

        // More tests for failure handler

        @Test
        void onAuthenticationSuccess_Test1() throws Exception {
            // Similar setup as above
        }

        @Test
        void onAuthenticationSuccess_Test2() throws Exception {
            // Similar setup as above
        }

        // More tests for success handler

//        @Test
//        void dashboard_ReturnsDashboardPage() throws Exception {
//            mockMvc = MockMvcBuilders.standaloneSetup(mvcController).build();
////            mockMvc.perform(get("/dashboard"))
////                    .andExpect(status().isOk())
////                    .andExpect(view().name("dashboard"));
//        }

        // More tests for MVCController methods
}

