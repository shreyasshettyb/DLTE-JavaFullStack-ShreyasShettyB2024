package mybank.db.dao.dltemybankdaolayer.service;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.remotes.CustomerAuthInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ResourceBundle;

@Service
public class CustomerAuthService implements UserDetailsService, CustomerAuthInterface {
    Logger logger = LoggerFactory.getLogger(CustomerAuthService.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer signingUp(Customer customer) {
        jdbcTemplate.update("insert into MYBANK_APP_CUSTOMER values(?,?,?,?,?,?,?,?)", new Object[]{
                customer.getCustomerId(), customer.getCustomerName(),
                customer.getCustomerAddress(), customer.getCustomerStatus(), customer.getCustomerContact(),
                customer.getUsername(), customer.getPassword(), customer.getAttempts()
        });
        logger.info(resourceBundle.getString("db.signUp.called"));
        return customer;
    }

    public Customer findByUsername(String username) {
        Customer customer = listAllCustomer().stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
        logger.info(resourceBundle.getString("db.findByUser.called"));
        return customer;
    }

    public List<Customer> listAllCustomer() {
        logger.info(resourceBundle.getString("db.listAll.called"));
        return jdbcTemplate.query("select * from MYBANK_APP_CUSTOMER", new BeanPropertyRowMapper<>(Customer.class));
    }

    public void updateAttempts(Customer customer) {
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{customer.getAttempts(), customer.getUsername()});
        logger.info(resourceBundle.getString("db.attempts.updated"));

    }

    public void updateStatus(Customer customer) {
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{customer.getUsername()});
        logger.info(resourceBundle.getString("db.status.changed"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer officials = findByUsername(username);
        if (officials == null) {
            logger.info(resourceBundle.getString("db.customer.notfound"));
            throw new UsernameNotFoundException(username);
        }
        return officials;
    }

}
