package mybank.db.dao.dltemybankdaolayer.service;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger= LoggerFactory.getLogger(CustomerAuthService.class);


    public Customer signingUp(Customer customer){
        int ack = jdbcTemplate.update("insert into MYBANK_APP_CUSTOMER values(?,?,?,?,?,?,?,?)",new Object[]{
                customer.getCustomerId(),customer.getCustomerName(),
                customer.getCustomerAddress(),customer.getCustomerStatus(),customer.getCustomerContact(),
                customer.getUsername(),customer.getPassword(),customer.getAttempts()
        });
        return customer;
    }

    public Customer findByUsername(String username){
        Customer customer = jdbcTemplate.queryForObject("select * from MYBANK_APP_CUSTOMER where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }

    public void updateAttempts(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set attempts=? where username=?",
                new Object[]{customer.getAttempts(),customer.getUsername()});
        logger.info("Attempts are updated");
    }

    public void updateStatus(Customer customer){
        jdbcTemplate.update("update MYBANK_APP_CUSTOMER set CUSTOMER_STATUS='inactive' where username=?",
                new Object[]{customer.getUsername()});
        logger.info("Status has changed");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer officials = findByUsername(username);
        if(officials==null)
            throw new UsernameNotFoundException(username);
        return officials;
    }
}
