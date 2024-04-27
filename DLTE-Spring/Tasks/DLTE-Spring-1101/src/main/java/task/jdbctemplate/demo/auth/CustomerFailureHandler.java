package task.jdbctemplate.demo.auth;

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
    MyBankService service;

    Logger logger = LoggerFactory.getLogger(CustomerFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyBankUsers customer = service.findByUsername(username);
        if (customer != null) {
            if (customer.getStatus().equalsIgnoreCase("active")) {
                if (customer.getAttempts() < 3) {
                    customer.setAttempts(customer.getAttempts() + 1);
                    service.updateAttempts(customer);
                    logger.warn("Invalid credentials and attempts taken");
                    exception = new LockedException("Attempts are taken");
                    String err = customer.getAttempts()+" "+exception.getMessage();
                    logger.warn(err);
                    super.setDefaultFailureUrl("/web/?error="+err);
                } else {
                    service.updateStatus(customer);
                    exception = new LockedException("Max Attempts reached account is suspended");
                    super.setDefaultFailureUrl("/web/?error="+exception.getMessage());
                }
            } else {
                logger.warn("Account suspended contact admin to redeem");
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}
