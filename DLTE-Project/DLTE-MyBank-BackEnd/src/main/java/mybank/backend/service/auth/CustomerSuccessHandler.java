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

@Component
public class CustomerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    CustomerAuthInterface service;

    Logger logger = LoggerFactory.getLogger(CustomerSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Customer customer = (Customer) authentication.getPrincipal();
        if (customer.getCustomerStatus().equalsIgnoreCase("active")) {
            if (customer.getAttempts() > 1) {
                customer.setAttempts(1);
                service.updateAttempts(customer);
            }
            logger.info("login successful");
            super.setDefaultTargetUrl("/dashboard");
        } else {
            logger.error("Account suspended contact admin to redeem");
            super.setDefaultTargetUrl("/?error="+new LockedException("Account suspended contact admin to redeem").getMessage());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
