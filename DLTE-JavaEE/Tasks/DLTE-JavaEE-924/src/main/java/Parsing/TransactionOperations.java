package Parsing;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TransactionOperations {

    public void create(List<Transaction> transactionList) {
        TransactionsWrapper wrapper = new TransactionsWrapper();
        wrapper.setTransactions(transactionList);

        try {
            JAXBContext context = JAXBContext.newInstance(TransactionsWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(wrapper, new File("Transactions.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void filter(String user) {
        try {
            File file = new File("Transactions.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(TransactionsWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
           TransactionsWrapper transactionsWrapper = (TransactionsWrapper) jaxbUnmarshaller.unmarshal(file);

            List<Transaction> transactions = transactionsWrapper.getTransactions();
            for (Transaction transaction : transactions) {
                if (transaction.getUser().equals(user)) {
                    System.out.println("User: " + transaction.getUser());
                    System.out.println("Date: " + transaction.getDate());
                    System.out.println("Amount: " + transaction.getAmount());
                    System.out.println();
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @XmlRootElement
    public static class TransactionsWrapper {
        private List<Transaction> transactions;

        @XmlElement(name = "transaction")
        public List<Transaction> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
        }
    }

}
