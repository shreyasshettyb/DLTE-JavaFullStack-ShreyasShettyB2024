package task.soapxsd.soapxssd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import task.soapxsd.soapxssd.dao.model.Transaction;
import task.soapxsd.soapxssd.dao.service.TransactionService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SoapxssdApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testApiAddTransaction() {
        Transaction transaction1 = new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills");
        Transaction transaction2 = new Transaction(85263L, new Date("03/03/2024"), "varun", "akash", 5451.0, "education");

        when(jdbcTemplate.update(anyString(), anyLong(), anyDouble(), anyString(), anyString(), anyString(), any(Date.class))).thenReturn(1);
        Transaction result = transactionService.apiAddTransaction(transaction1);
        assertEquals(transaction1, result);
    }

    @Test
    void testApiFindBySender() {
        Transaction transaction1 = new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills");
        Transaction transaction2 = new Transaction(85263L, new Date("03/03/2024"), "varun", "akash", 5451.0, "education");
        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(expectedList);

        List<Transaction> result = transactionService.apiFindBySender("elroy");
        assertEquals(expectedList, result);
    }

//fail
    @Test
    void testApiFindByReceiver() {
        Transaction transaction1 = new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills");
        Transaction transaction2 = new Transaction(85263L, new Date("03/03/2024"), "varun", "akash", 5451.0, "education");
        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(expectedList);
        List<Transaction> result = transactionService.apiFindByReceiver("ram");
        assertEquals(expectedList.get(0), result);
    }

    @Test
    void testApiFindByAmount() {
        Transaction transaction1 = new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills");
        Transaction transaction2 = new Transaction(85263L, new Date("03/03/2024"), "varun", "akash", 5451.0, "education");
        List<Transaction> expectedList = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(TransactionService.TransactionMapper.class)))
                .thenReturn(expectedList);
        List<Transaction> result = transactionService.apiFindByAmount(100.0);
        assertEquals(expectedList, result);
    }

    @Test
    void testApiUpdateRemarks() {
        Transaction transaction1 = new Transaction(71651L, new Date("01/03/2024"), "rakesh", "elroy", 6131.0, "bills");
        Transaction transaction2 = new Transaction(85263L, new Date("03/03/2024"), "varun", "akash", 5451.0, "education");
        when(jdbcTemplate.update(anyString(), anyString(), anyLong())).thenReturn(1);
        String result = transactionService.apiUpdateRemarks(transaction1.getRemarks(), transaction2.getTransactionId());
        assertEquals("success", result);
    }

    @Test
    void testRemoveTransactionBetweenDates() {
        Date startDate = new Date("01/03/2024");
        Date endDate = new Date("01/03/2024");
        when(jdbcTemplate.update(anyString(), any(Date.class), any(Date.class))).thenReturn(1);
        String result = transactionService.apiRemoveTransactionByDates(startDate, endDate);
        assertEquals("success", result);
    }

}
