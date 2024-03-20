package servelts;

import org.example.entity.Transaction;
//import org.example.middleware.DatabaseTarget;
//import org.example.remotes.StorageTarget;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyBankTest {

    @Mock
    private AccountService service;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private StringWriter stringWriter;
    @Mock
    private PrintWriter printWriter;
    @Mock
    private Logger logger;


    @Before
    public void initiate() throws IOException {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

    }

    @Test
    public void testFindAllDate() throws IOException, ServletException {
        MyBank myBank = new MyBank();
        myBank.service=service;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Date.valueOf("2024-04-24"), 10000009, "rakesh12", 500.0, 4500.0));
        transactions.add(new Transaction(Date.valueOf("2024-03-21"), 10000008, "shreyas12", 1500.0, 5500.0));
        transactions.add(new Transaction(Date.valueOf("2024-03-14"), 10000007, "varun12", 100.0, 4400.0));
        when(request.getParameter("username")).thenReturn("rakesh12");
        when(request.getParameter("date")).thenReturn("2024-04-24");
        when(service.callFindAllDate(any(Date.class),anyString())).thenReturn(transactions.subList(0,1));

        myBank.doGet(request,response);
        verify(service).callFindAllDate(any(Date.class),anyString());
        String expectedOutput = "Transaction{date=2024-03-24, transactionID=10000009, user='rakesh12', amount=500.0, balance=4500.0}";
        String actualOutput = stringWriter.toString().trim().replace(System.lineSeparator(),"\n");//output is correct,but their is a mismatch in terms of line separators used
        // test fails beacuse of in matched date
        assertEquals("Response content should match", expectedOutput, actualOutput);
    }


    @Test
    public void testFindByUser() throws ServletException, IOException {
        MyBank myBank = new MyBank();
        myBank.service=service;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Date.valueOf("2024-04-24"), 10000009, "rakesh12", 500.0, 4500.0));
        transactions.add(new Transaction(Date.valueOf("2024-03-21"), 10000008, "shreyas12", 1500.0, 5500.0));
        transactions.add(new Transaction(Date.valueOf("2024-03-14"), 10000007, "varun12", 100.0, 4400.0));

        when(request.getParameter("username")).thenReturn("rakesh12");
        when(service.callFindAllUser(anyString())).thenReturn(transactions.subList(0,1));

        myBank.doGet(request,response);
        verify(service).callFindAllUser(anyString());
        String expectedOutput = "Transaction{date=2024-04-24, transactionID=10000009, user='rakesh12', amount=500.0, balance=4500.0}";
        String actualOutput = stringWriter.toString().trim().replace(System.lineSeparator(),"\n");//output is correct,but their is a mismatch in terms of line separators used

        assertEquals("Response content should match", expectedOutput, actualOutput);
    }

    @Test
    public void testFindAll() throws IOException, ServletException {
        MyBank myBank = new MyBank();
        myBank.service=service;
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Date.valueOf("2024-04-24"), 10000009, "rakesh12", 500.0, 4500.0));
        transactions.add(new Transaction(Date.valueOf("2024-03-21"), 10000008, "shreyas12", 1500.0, 5500.0));
        transactions.add(new Transaction(Date.valueOf("2024-03-14"), 10000007, "varun12", 100.0, 4400.0));

        when(service.callFindAll()).thenReturn(transactions);

        myBank.doGet(request,response);
        verify(service).callFindAll();
        String expectedOutput = "Transaction{date=2024-04-24, transactionID=10000009, user='rakesh12', amount=500.0, balance=4500.0}\n" +
                "Transaction{date=2024-03-21, transactionID=10000008, user='shreyas12', amount=1500.0, balance=5500.0}\n" +
                "Transaction{date=2024-03-14, transactionID=10000007, user='varun12', amount=100.0, balance=4400.0}";
        String actualOutput = stringWriter.toString().trim().replace(System.lineSeparator(),"\n");//output is correct,but their is a mismatch in terms of line separators used

        assertEquals("Response content should match", expectedOutput, actualOutput);

    }
}
