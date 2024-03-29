package task.soapxsd.soapxssd;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import service.transaction.*;
import task.soapxsd.soapxssd.config.SoapPhase;
import task.soapxsd.soapxssd.dao.model.Transaction;
import task.soapxsd.soapxssd.dao.service.TransactionService;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SoapEndpointTesting {

    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void testAddTransaction() {

        Transaction transaction = new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills");
        when(transactionService.apiAddTransaction(any(Transaction.class))).thenReturn(transaction);

        AddTransactionRequest request = new AddTransactionRequest();
        service.transaction.Transaction result = new service.transaction.Transaction();
        request.setTransaction(result);

        result.setTransactionId(71651L);
        result.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024, 3, 1, 0));
        result.setSentTo("rakesh");
        result.setReceivedBy("elroy");
        result.setAmount(6131.0);
        result.setRemarks("bills");

        AddTransactionResponse response = soapPhase.addTransactionResponse(request);

        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
    }

    @Test
    public void testFilterSender() {

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills"));
        when(transactionService.apiFindBySender("rakesh")).thenReturn(transactionList);
        FindBySenderRequest request = new FindBySenderRequest();
        request.setSentTo("elroy");
        FindBySenderResponse response = soapPhase.findBySenderResponse(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
    }

    @Test
    public void testFilterReceiver() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills"));
        when(transactionService.apiFindByReceiver("elroy")).thenReturn(transactionList);
        FindByReceiverRequest request = new FindByReceiverRequest();
        request.setReceiver("elroy");
        FindByReceiverResponse response = soapPhase.findByReceiverResponse(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
    }

    @Test
    public void testFilterAmount() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills"));
        when(transactionService.apiFindByAmount(6131.0)).thenReturn(transactionList);
        FindByAmountRequest request = new FindByAmountRequest();
        request.setAmount(6131.0);
        FindByAmountResponse response = soapPhase.findByAmountResponse(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());

    }

    @Test
    public void testUpdatingTransaction() {
        when(transactionService.apiUpdateRemarks(anyString(), anyLong())).thenReturn("success");
        UpdateRemarksRequest request = new UpdateRemarksRequest();
        request.setRemarks(anyString());
        request.setTransactionId(anyLong());
        UpdateRemarksResponse response = soapPhase.updatingTransaction(request);
        assertEquals("updated", response.getServiceStatus().getStatus());
    }

    @Test
    public void testRemoveTransactionBetweenDates() {
        Date startDate = Date.from(Instant.parse("2024-03-03T00:00:00Z"));
        Date endDate = Date.from(Instant.parse("2024-03-31T00:00:00Z"));
        when(transactionService.apiRemoveTransactionByDates(startDate, endDate)).thenReturn("success");
        RemoveTransactionByDatesRequest request = new RemoveTransactionByDatesRequest();
        XMLGregorianCalendar start = XMLGregorianCalendarImpl.createDateTime(2024, 3, 3, 0, 0, 0, 0, 0);
        XMLGregorianCalendar end = XMLGregorianCalendarImpl.createDateTime(2024, 3, 31, 0, 0, 0, 0, 0);
        request.setStartDate(start);
        request.setEndDate(end);
        RemoveTransactionByDatesResponse response = soapPhase.removeTransactionByDatesResponse(request);
//        assertEquals(endDate,end.toGregorianCalendar().getTime());
        assertEquals("success", response.getServiceStatus().getMessage());
    }
}
