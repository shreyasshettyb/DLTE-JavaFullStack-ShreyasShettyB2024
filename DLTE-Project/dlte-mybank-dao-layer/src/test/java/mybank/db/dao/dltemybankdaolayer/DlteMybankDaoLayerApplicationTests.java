package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.service.RepositoryMyBank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DlteMybankDaoLayerApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private RepositoryMyBank service;

    //Pass
//    @Test
    void testFindAllDeposits1() throws DepositsException, SQLException {
        DepositsAvailable depositsAvailable1 = new DepositsAvailable(1000001, "FD", "Lump Sum", "Standard Fixed Deposit", 10.2);
        DepositsAvailable depositsAvailable2 = new DepositsAvailable(1000002, "RD", "Recurring", "Standard Recurring Deposit", 9.2);
        DepositsAvailable depositsAvailable3 = new DepositsAvailable(1000003, "Senior Citizen FD", "Lump Sum", "Senior Citizen Fixed Deposit", 11.5);
        DepositsAvailable depositsAvailable4 = new DepositsAvailable(1000004, "Employee FD", "Lump Sum", "Employee Fixed Deposit", 10.7);
        DepositsAvailable depositsAvailable5 = new DepositsAvailable(1000005, "Women Empowerment RD", "Recurring", "Women Empowerment Recurring Deposit", 10.2);

        List<DepositsAvailable> depositsAvailableList = Stream.of(depositsAvailable1, depositsAvailable2, depositsAvailable3, depositsAvailable4, depositsAvailable5).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(), any(RepositoryMyBank.DepositsAvailableMapper.class))).thenReturn(depositsAvailableList);

        assertEquals(depositsAvailableList, service.availableDeposits());
    }

//    //Fail deposit 5 is missing
//    @Test
    void testFindAllDeposits2() throws DepositsException, SQLException {
        DepositsAvailable depositsAvailable1=new DepositsAvailable(1000001,"FD","Lump Sum","Standard Fixed Deposit",10.2);
        DepositsAvailable depositsAvailable2=new DepositsAvailable(1000002,"RD","Recurring","Standard Recurring Deposit",9.2);
        DepositsAvailable depositsAvailable3=new DepositsAvailable(1000003,"Senior Citizen FD","Lump Sum","Senior Citizen Fixed Deposit",11.5);
        DepositsAvailable depositsAvailable4=new DepositsAvailable(1000004,"Employee FD","Lump Sum","Employee Fixed Deposit",10.7);
        DepositsAvailable depositsAvailable5=new DepositsAvailable(1000005,"Women Empowerment RD","Recurring","Women Empowerment Recurring Deposit",10.2);

        List<DepositsAvailable> depositsAvailableList= Stream.of(depositsAvailable1,depositsAvailable2,depositsAvailable3,depositsAvailable4,depositsAvailable5).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(RepositoryMyBank.DepositsAvailableMapper.class))).thenReturn(depositsAvailableList);

        assertEquals(depositsAvailableList.subList(0,4),service.availableDeposits());
    }

    // Pass DepositsException is thrown
//    @Test
    void testDepositException(){

        List<DepositsAvailable> depositsAvailableList=new ArrayList<>();

        when(jdbcTemplate.query(anyString(),any(RepositoryMyBank.DepositsAvailableMapper.class))).thenReturn(depositsAvailableList);

        assertThrows(DepositsException.class,()-> service.availableDeposits());
    }

}
