package mybank.backend.service.auth;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.remotes.CustomerAuthInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    CustomerAuthInterface service;

    Logger logger = LoggerFactory.getLogger(CustomerFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        Customer customer = service.findByUsername(username);
        if (customer != null) {
            if (customer.getCustomerStatus().equalsIgnoreCase("active")) {
                if (customer.getAttempts() < customer.getMaxAttempt()) {
                    customer.setAttempts(customer.getAttempts() + 1);
                    service.updateAttempts(customer);
                    logger.warn("Invalid credentials and attempts taken");
                    exception = new LockedException("Invalid credentials "+(customer.getMaxAttempt()-customer.getAttempts()+1)+" attempts left");
                } else {
                    logger.error("Max Attempts reached");
                    service.updateStatus(customer);
                    exception = new LockedException("Max Attempts reached account is suspended");
                }
            } else {
                logger.error("Account suspended contact admin to redeem");
                exception = new LockedException("Account suspended contact admin to redeem");
            }
        }else {
            logger.error("Username not found/Does not Exist");
            exception = new LockedException("Username not found/Does not Exist");
        }
        super.setDefaultFailureUrl("/?error="+exception.getMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}
