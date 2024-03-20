package mybank;

import org.example.entity.Transaction;
import org.example.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class MyBankTest {
    @Mock
    private AccountService service;
    private Transactions transactions;
    List<Transaction> transactionList = new ArrayList<>();
    @Before
    public void settingUp(){
        MockitoAnnotations.initMocks(this);
        transactions=new Transactions();
        transactions.service=service;
        transactionList.add(new Transaction(new Date("12/03/2024"),1000000008,"shreyas12",500.0,84500.0));
        transactionList.add(new Transaction(new Date("13/03/2024"),1000000009,"varun12",1500.0,4200.0));
        transactionList.add(new Transaction(new Date("14/03/2024"),1000000010,"rakesh12",500.0,8500.0));
    }

    @Test
    public void testFindAll(){
        when(service.callFindAll()).thenReturn(transactionList);
        GroupOfTransactions transaction=transactions.findAll();
        System.out.println(transaction.toString());
        assertNotNull(transaction);
    }

    @Test
    public void testFindAllByUsername(){
        Transaction transaction1=new Transaction(new Date("12/03/2024"),1000000008,"shreyas12",500.0,84500.0);
        Transaction transaction2=new Transaction(new Date("13/03/2024"),1000000009,"varun12",1500.0,4200.0);

        List<Transaction> transactionList1= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        List<Transaction> transactionList2= Stream.of(transaction2).collect(Collectors.toList());
        when(service.callFindAllUser("varun12")).thenReturn(transactionList2);
        GroupOfTransactions groupOfTransactions=transactions.findAllByUser("varun12");
        assertNotNull(groupOfTransactions);
        assertEquals(4200.0,groupOfTransactions.getTransactionsList().get(0).getBalance(),0);
    }
    @Test
    public void testAddTransaction(){
        Transaction transaction1=new Transaction(new Date("14/03/2024"),1000000010,"rakesh12",500.0,8500.0);
        doNothing().when(service).createTransaction(transaction1.getUser(),transaction1.getAmount(),transaction1.getBalance());
        transactions.create(transaction1.getUser(),transaction1.getAmount(),transaction1.getBalance());
        verify(service, times(1)).createTransaction(transaction1.getUser(),transaction1.getAmount(),transaction1.getBalance());

    }
}
