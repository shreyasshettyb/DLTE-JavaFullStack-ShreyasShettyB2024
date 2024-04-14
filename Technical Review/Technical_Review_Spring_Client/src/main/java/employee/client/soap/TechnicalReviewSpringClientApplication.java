package employee.client.soap;

import employee.client.soap.console.EmployeeConsole;
import employee.client.soap.service.SOAPConfig;
import employee.client.soap.service.SOAPConnector;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import soap.webservice.ReadRequest;
import soap.webservice.ReadResponse;

//import services.employee.EmployeePort;
//import services.employee.EmployeePortService;
//import services.employee.ReadRequest;
//import services.employee.ReadResponse;

@SpringBootApplication
public class TechnicalReviewSpringClientApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TechnicalReviewSpringClientApplication.class, args);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(SOAPConfig.class);
//        context.refresh();
//        EmployeeConsole employeeConsole = new EmployeeConsole();
//        employeeConsole.run(args);
    }

        @Bean
        CommandLineRunner lookup(SOAPConnector soapConnector) {
            return args -> {
               EmployeeConsole employeeConsole = new EmployeeConsole();
               employeeConsole.console(soapConnector);
            };
        }
}
