//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.15 at 08:57:50 AM IST 
//


package services.deposits;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.deposits package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.deposits
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ViewAllDepositsAvailableResponse }
     * 
     */
    public ViewAllDepositsAvailableResponse createViewAllDepositsAvailableResponse() {
        return new ViewAllDepositsAvailableResponse();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link DepositsAvailable }
     * 
     */
    public DepositsAvailable createDepositsAvailable() {
        return new DepositsAvailable();
    }

    /**
     * Create an instance of {@link ViewAllDepositsAvailableRequest }
     * 
     */
    public ViewAllDepositsAvailableRequest createViewAllDepositsAvailableRequest() {
        return new ViewAllDepositsAvailableRequest();
    }

}
