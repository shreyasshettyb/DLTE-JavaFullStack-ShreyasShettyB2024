package task.jdbctemplate.demo.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    MyBankService service;

    Logger logger = LoggerFactory.getLogger(CustomerSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankUsers customer = (MyBankUsers) authentication.getPrincipal();
        if (customer.getStatus().equalsIgnoreCase("active")) {
            if (customer.getAttempts() >= 1) {
                customer.setAttempts(1);
                service.updateAttempts(customer);
            }
            if (customer.getRole().equalsIgnoreCase("customer")) {
                super.setDefaultTargetUrl("/customer");
            } else if (customer.getRole().equalsIgnoreCase("admin")) {
                super.setDefaultTargetUrl("/admin");
            }
        } else {
            logger.warn("Max attempts reached contact admin");
            super.setDefaultTargetUrl("/index");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
