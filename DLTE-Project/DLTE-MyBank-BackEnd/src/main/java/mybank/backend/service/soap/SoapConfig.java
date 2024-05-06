package mybank.backend.service.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.ResourceBundle;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("backend");

    // conversion xsd to wsdl
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet, "/depositsrepo/*");
    }

    // wsdl properties
    @Bean(name = "deposit")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName(resourceBundle.getString("app.soap.port"));
        defaultWsdl11Definition.setTargetNamespace(resourceBundle.getString("app.soap.namespace"));
        defaultWsdl11Definition.setLocationUri(resourceBundle.getString("app.soap.uri"));
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }

    // identify the xsd
    @Bean
    public XsdSchema depositsSchema() {
        return new SimpleXsdSchema(new ClassPathResource(resourceBundle.getString("app.soap.xsd")));
    }
}

