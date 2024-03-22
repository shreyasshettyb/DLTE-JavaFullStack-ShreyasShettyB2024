package task.jdbctemplate.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import task.jdbctemplate.demo.model.Transaction;
import task.jdbctemplate.demo.service.TransactionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DemoApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testAddTransaction() {
        Transaction transaction = new Transaction(74185296, new Date("03/13/2024"), "shreyas", "elroy", 12456.0, "education");

        lenient().when(jdbcTemplate.update(anyString(), any(Object.class))).thenReturn(1);

        Transaction transactionOut = transactionService.apiAddTransaction(transaction);

        assertEquals(transaction,transactionOut);

    }

    @Test
    void testFindBySender() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(74185296, new Date("03/13/2024"), "shreyas", "akash", 12456.0, "education"));
        transactionList.add(new Transaction(74185295, new Date("03/14/2024"), "rakesh", "elroy", 4152.0, "loan"));
        transactionList.add(new Transaction(74185294, new Date("03/15/2024"), "varun", "prasanth", 35431.0, "education"));
        transactionList.add(new Transaction(74185293, new Date("03/15/2024"), "rakesh", "elroy", 45632.0, "others"));

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionList.subList(0,1));

        List<Transaction> transactionList1 = transactionService.apiFindBySender("shreyas");

        assertEquals(transactionList1,transactionList.subList(0,1));
    }

    @Test
    void testFindByReceiver() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(74185296, new Date("03/13/2024"), "shreyas", "akash", 12456.0, "education"));
        transactionList.add(new Transaction(74185295, new Date("03/14/2024"), "rakesh", "elroy", 4152.0, "loan"));
        transactionList.add(new Transaction(74185294, new Date("03/15/2024"), "varun", "prasanth", 35431.0, "education"));
        transactionList.add(new Transaction(74185293, new Date("03/15/2024"), "rakesh", "elroy", 45632.0, "others"));

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionList.subList(0,1));

        List<Transaction> transactionList1 = transactionService.apiFindByReceiver("elroy");

        assertEquals(transactionList1,transactionList.subList(1,2));
    }

    @Test
    void testFindByAmount() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(74185296, new Date("03/13/2024"), "shreyas", "akash", 12456.0, "education"));
        transactionList.add(new Transaction(74185295, new Date("03/14/2024"), "rakesh", "elroy", 4152.0, "loan"));
        transactionList.add(new Transaction(74185294, new Date("03/15/2024"), "varun", "prasanth", 35431.0, "education"));
        transactionList.add(new Transaction(74185293, new Date("03/15/2024"), "rakesh", "elroy", 45632.0, "others"));

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transactionList.subList(1,2));

        List<Transaction> transactionList1 = transactionService.apiFindByAmount(4152.0);

        assertEquals(transactionList1,transactionList.subList(1,2));
    }


}
