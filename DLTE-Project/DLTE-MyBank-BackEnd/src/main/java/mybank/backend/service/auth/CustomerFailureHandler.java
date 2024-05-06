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
import java.util.ResourceBundle;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    CustomerAuthInterface service;

    Logger logger = LoggerFactory.getLogger(CustomerFailureHandler.class);

    ResourceBundle resourceBundle = ResourceBundle.getBundle("backend");
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        Customer customer = service.findByUsername(username);
        if (customer != null) {
            if (customer.getCustomerStatus().equalsIgnoreCase("active")) {
                if (customer.getAttempts() < customer.getMaxAttempt()) {
                    customer.setAttempts(customer.getAttempts() + 1);
                    service.updateAttempts(customer);
                    logger.warn(resourceBundle.getString("invalid.credentials"));
                    exception = new LockedException("Invalid credentials "+(customer.getMaxAttempt()-customer.getAttempts()+1)+" attempts left");
                } else {
                    logger.error(resourceBundle.getString("max.attempts"));
                    service.updateStatus(customer);
                    exception = new LockedException(resourceBundle.getString("max.attempts"));
                }
            } else {
                logger.error(resourceBundle.getString("account.suspended"));
                exception = new LockedException(resourceBundle.getString("account.suspended"));
            }
        }else {
            logger.error(resourceBundle.getString("user.notfound"));
            exception = new LockedException(resourceBundle.getString("user.notfound"));
        }
        super.setDefaultFailureUrl("/?error="+exception.getMessage());
        super.onAuthenticationFailure(request, response, exception);
    }
}
