package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.service.CustomerAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerAuthTest {
    private static Customer customer1, customer2;
    private static List<Customer> customerList;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private CustomerAuthService authService;

    @BeforeEach
    void setUp() {
        // Prepare test data
        customer1 = new Customer();
        customer1.setCustomerId(100001L);
        customer1.setCustomerName("shreyas");
        customer1.setCustomerAddress("udupi");
        customer1.setCustomerStatus("active");
        customer1.setCustomerContact(7418529630L);
        customer1.setUsername("shreyas12");
        customer1.setPassword("pass1");
        customer1.setAttempts(1);

        customer2 = new Customer();
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

        customerList = authService.listAllCustomer();

    }

    @Test
    void testSigningUp_Success() {
        // Mock the behavior of jdbcTemplate.update()
        when(jdbcTemplate.update("insert into MYBANK_APP_CUSTOMER values(?,?,?,?,?,?,?,?)", new Object[]{
                customer1.getCustomerId(), customer1.getCustomerName(),
                customer1.getCustomerAddress(), customer1.getCustomerStatus(), customer1.getCustomerContact(),
                customer1.getUsername(), customer1.getPassword(), customer1.getAttempts()
        })).thenReturn(1);

        Customer signedUpCustomer = authService.signingUp(customer1);

        assertNotNull(signedUpCustomer);
        // Add more assertions as needed
    }

    @Test
    void testListAllCustomer() {
        lenient().when(jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER", new BeanPropertyRowMapper<>(Customer.class))).thenReturn(customerList);
        List<Customer> list = authService.listAllCustomer();
        assertEquals(2, list.size());
    }

    @Test
    void testUpdateAttempts() {
        // Mock the behavior of jdbcTemplate.update()
        when(jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?", new Object[]{1, "shreyas12"})).thenReturn(1);

        authService.updateAttempts(customer1);

        // Verify that jdbcTemplate.update was called with the correct parameters
        verify(jdbcTemplate).update("update MYBANK_APP_CUSTOMER set attempts=? where username=?", 1, "shreyas12");
    }

    @Test
    void testUpdateStatus() {
        // Mock the behavior of jdbcTemplate.update()
        when(jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{"shreyas12"})).thenReturn(1);

        authService.updateStatus(customer1);

        // Verify that jdbcTemplate.update was called with the correct parameters
        verify(jdbcTemplate).update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?", "shreyas12");
    }

    @Test
    void testLoadUserByUsername_ExistingUsername() {
        lenient().when(authService.listAllCustomer()).thenReturn(Collections.singletonList(customer1)); // Adjust this line

        UserDetails userDetails = authService.loadUserByUsername("shreyas12");

        assertNotNull(userDetails);
        // Add more assertions as needed
    }


    @Test
    void testLoadUserByUsername_NonExistingUsername() {
        lenient().when(authService.listAllCustomer()).thenReturn(Collections.singletonList(customer1)); // Adjust this line

        assertThrows(UsernameNotFoundException.class, () -> authService.loadUserByUsername("shreyas1"));
    }

//

    @Test
    public void testFindByUsername() {
        lenient().when(authService.listAllCustomer()).thenReturn(customerList);
        Customer customer = authService.findByUsername("shreyas12");
        assertEquals(customer, customer1);
    }

}
