
package mybank;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Transactions", targetNamespace = "http://mybank/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Transactions {


    /**
     * 
     * @param date
     * @param string
     * @return
     *     returns mybank.GroupOfTransactions
     */
    @WebMethod
    @WebResult(name = "GroupOfTransaction", partName = "GroupOfTransaction")
    @Action(input = "http://mybank/Transactions/findAllByUserDateRequest", output = "http://mybank/Transactions/findAllByUserDateResponse")
    public GroupOfTransactions findAllByUserDate(
        @WebParam(name = "String", partName = "String")
        String string,
        @WebParam(name = "Date", partName = "Date")
        XMLGregorianCalendar date);

}
