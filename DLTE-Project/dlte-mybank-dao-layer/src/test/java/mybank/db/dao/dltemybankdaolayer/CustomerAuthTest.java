package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerAuthTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerAuthService authService;


    @Test
    void testSigningUp_Success() {
        Customer customer = new Customer(); // Set up your test customer
        // Mock the behavior of jdbcTemplate.update()
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        Customer signedUpCustomer = authService.signingUp(customer);

        assertNotNull(signedUpCustomer);
        // Add more assertions as needed
    }

    @Test
    void testFindByUsername_ExistingUsername() {
        String username = "testUser";
        Customer testCustomer = new Customer(); // Set up a test customer with the given username
        when(authService.listAllCustomer()).thenReturn(Arrays.asList(testCustomer));

        Customer foundCustomer = authService.findByUsername(username);

        assertNotNull(foundCustomer);
        // Add more assertions as needed
    }

    @Test
    void testFindByUsername_NonExistingUsername() {
        String username = "nonExistingUser";
        when(authService.listAllCustomer()).thenReturn(Arrays.asList(new Customer())); // Assuming there are no customers in the list

        Customer foundCustomer = authService.findByUsername(username);

        assertNull(foundCustomer);
    }

    @Test
    void testListAllCustomer() {
        // Prepare test data
        Customer customer1 = new Customer();
        customer1.setCustomerId(100001L);
        customer1.setCustomerName("shreyas");
        customer1.setCustomerAddress("udupi");
        customer1.setCustomerStatus("active");
        customer1.setCustomerContact(7418529630L);
        customer1.setUsername("shreyas12");
        customer1.setPassword("pass1");
        customer1.setAttempts(1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(100002L);
        customer2.setCustomerName("varun");
        customer2.setCustomerAddress("moodbidri");
        customer2.setCustomerStatus("active");
        customer2.setCustomerContact(9638527410L);
        customer2.setUsername("varun12");
        customer2.setPassword("pass2");
        customer2.setAttempts(1);
        List<Customer> customers = Arrays.asList(customer1, customer2);
        // Mock jdbcTemplate query method
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(customers);

        List<Customer> allCustomers = authService.listAllCustomer();

        assertNotNull(allCustomers);
        assertEquals(2, allCustomers.size());
        // Add more assertions as needed
    }

    @Test
    void testUpdateAttempts() {
        Customer customer = new Customer(); // Set up your test customer
        customer.setUsername("testUser");
        customer.setAttempts(3);
        // Mock the behavior of jdbcTemplate.update()
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        authService.updateAttempts(customer);

        // Verify that jdbcTemplate.update was called with the correct parameters
        verify(jdbcTemplate).update("update MYBANK_APP_CUSTOMER set attempts=? where username=?", 3, "testUser");
    }

    @Test
    void testUpdateStatus() {
        Customer customer = new Customer(); // Set up your test customer
        customer.setUsername("testUser");
        // Mock the behavior of jdbcTemplate.update()
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);

        authService.updateStatus(customer);

        // Verify that jdbcTemplate.update was called with the correct parameters
        verify(jdbcTemplate).update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?", "testUser");
    }

    @Test
    void testLoadUserByUsername_ExistingUsername() {
        String username = "testUser";
        Customer testCustomer = new Customer(); // Set up a test customer with the given username
        when(authService.findByUsername(username)).thenReturn(testCustomer);

        UserDetails userDetails = authService.loadUserByUsername(username);

        assertNotNull(userDetails);
        // Add more assertions as needed
    }

    @Test
    void testLoadUserByUsername_NonExistingUsername() {
        String username = "nonExistingUser";
        when(authService.findByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername(username));
    }
}
