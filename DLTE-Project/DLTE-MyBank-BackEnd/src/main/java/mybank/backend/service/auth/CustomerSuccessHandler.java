package mybank.backend.service.auth;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.remotes.CustomerAuthInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    CustomerAuthInterface service;

    Logger logger = LoggerFactory.getLogger(CustomerSuccessHandler.class);

    ResourceBundle resourceBundle = ResourceBundle.getBundle("backend");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Customer customer = (Customer) authentication.getPrincipal();
        if (customer.getCustomerStatus().equalsIgnoreCase("active")) {
            if (customer.getAttempts() > 1) {
                customer.setAttempts(1);
                service.updateAttempts(customer);
            }
            logger.info(resourceBundle.getString("login.success"));
            super.setDefaultTargetUrl("/dashboard");
        } else {
            logger.error(resourceBundle.getString("account.suspended"));
            super.setDefaultTargetUrl("/?error="+new LockedException(resourceBundle.getString("account.suspended")).getMessage());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
