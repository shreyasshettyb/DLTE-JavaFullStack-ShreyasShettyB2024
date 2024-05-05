package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface CustomerAuthInterface {
     Customer signingUp(Customer customer);

     Customer findByUsername(String username);

     List<Customer> listAllCustomer();

     void updateAttempts(Customer customer);

     void updateStatus(Customer customer);

     UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
