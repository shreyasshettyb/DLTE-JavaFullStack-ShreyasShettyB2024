package mybank;

import javax.xml.ws.Endpoint;

public class myBankEndpoint {
    private static String url="http://localhost:1011/mybank";
    public static void main(String[] args) {
        App myBank=new App();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,myBank);
    }
}
