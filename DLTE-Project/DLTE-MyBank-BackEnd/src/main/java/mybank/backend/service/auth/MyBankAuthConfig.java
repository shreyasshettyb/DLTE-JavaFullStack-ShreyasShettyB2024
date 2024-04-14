package mybank.backend.service.auth;

import mybank.db.dao.dltemybankdaolayer.auth.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyBankAuthConfig {
    @Autowired
    CustomerAuthService service;

    AuthenticationManager authenticationManager;

    @Autowired
    CustomerFailureHandler customerFailureHandler;

    @Autowired
    CustomerSuccessHandler customerSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.formLogin().
                usernameParameter("username").
                failureHandler(customerFailureHandler).
                successHandler(customerSuccessHandler);
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().anyRequest().fullyAuthenticated();

//        httpSecurity.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/profile/register").permitAll()
//                .anyRequest().authenticated() // Allow other requests without authentication
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin().
//                usernameParameter("username").
//                failureHandler(customerFailureHandler).
//                successHandler(customerSuccessHandler);



        AuthenticationManagerBuilder builder = httpSecurity.
                getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(service);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);

        return httpSecurity.build();
    }
}
