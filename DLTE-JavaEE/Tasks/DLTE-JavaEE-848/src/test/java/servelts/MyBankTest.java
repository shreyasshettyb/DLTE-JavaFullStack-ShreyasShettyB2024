package servelts;

import org.example.entity.Transaction;
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
import java.util.logging.Logger;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyBankTest {

    @Mock
    private AccountService accountService;

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
    public void testFindByUserAndDate() throws ServletException, IOException {
        Transaction transaction1 = new Transaction(new Date(2024, 4, 24), 10000009, "rakesh12", 500.0, 4500.0);
        MyBank myBank = new MyBank();
        myBank.service = accountService;
        when(request.getParameter("username")).thenReturn("rakesh12");
        when(request.getParameter("date")).thenReturn(String.valueOf(new Date(2024,4,24)));
//        when(accountService.callFindAllDate((new Date(anyInt(),anyInt(),anyInt())),anyString())).thenReturn((List<Transaction>) transaction1);
//        myBank.doGet(request, response);
//        verify(accountService).callFindAllDate((new Date(anyInt(),anyInt(),anyInt())),anyString());
//
//
//       assertEquals("Transaction{date=2024-03-17, transactionID=10000009, user='rakesh12', amount=500.0, balance=4500.0} ",stringWriter.toString().trim());
        verify(response).getWriter();
    }

    @Test
    public void testFindByUser() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("testUser");
        new MyBank().doGet(request, response);

//        Transaction{date=2024-03-17, transactionID=10000009, user='rakesh12', amount=500.0, balance=4500.0}
//        Transaction{date=2024-03-17, transactionID=10000010, user='rakesh12', amount=500.0, balance=4000.0}
        verify(response).getWriter();
    }

    @Test
    public void testFindAll() throws IOException, ServletException {
        new MyBank().doGet(request, response);

//        Transaction{date=2024-03-12, transactionID=10000002, user='shreyas12', amount=500.0, balance=848652.0}
//        Transaction{date=2024-03-12, transactionID=10000003, user='shreyas12', amount=500.0, balance=846652.0}
//        Transaction{date=2024-03-12, transactionID=10000004, user='shreyas12', amount=1000.0, balance=845652.0}
//        Transaction{date=2024-03-12, transactionID=10000005, user='shreyas12', amount=1000.0, balance=844652.0}
//        Transaction{date=2024-03-14, transactionID=10000007, user='shreyas12', amount=500.0, balance=843652.0}
//        Transaction{date=2024-03-17, transactionID=10000009, user='rakesh12', amount=500.0, balance=4500.0}
//        Transaction{date=2024-03-14, transactionID=10000006, user='shreyas12', amount=500.0, balance=844152.0}
//        Transaction{date=2024-03-14, transactionID=10000008, user='shreyas12', amount=500.0, balance=843152.0}
//        Transaction{date=2024-03-17, transactionID=10000010, user='rakesh12', amount=500.0, balance=4000.0}

        verify(response).getWriter();
    }
}
