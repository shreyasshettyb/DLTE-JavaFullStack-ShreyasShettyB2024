package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.entity.Customer;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.service.RepositoryMyBank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DlteMybankDaoLayerApplicationTests {

    private static List<DepositsAvailable> depositsAvailableList;
    private static DepositsAvailed depositsAvailed;
    @Mock
    private CallableStatementCreator callableStatementCreator;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private RepositoryMyBank service;
    @Mock
    private ResultSet resultSet;
    @Captor
    private ArgumentCaptor<CallableStatementCreator> callableStatementCreatorCaptor;


    @BeforeAll
    static void setUp() {
        DepositsAvailable depositsAvailable1 = new DepositsAvailable();
        depositsAvailable1.setDepositId(1000001);
        depositsAvailable1.setDepositName("FD");
        depositsAvailable1.setDepositType("Lump Sum");
        depositsAvailable1.setDepositDescription("Standard Fixed Deposit");
        depositsAvailable1.setDepositRoi(10.2);

        DepositsAvailable depositsAvailable2 = new DepositsAvailable();
        depositsAvailable2.setDepositId(1000002);
        depositsAvailable2.setDepositName("RD");
        depositsAvailable2.setDepositType("Recurring");
        depositsAvailable2.setDepositDescription("Standard Recurring Deposit");
        depositsAvailable2.setDepositRoi(9.2);

        DepositsAvailable depositsAvailable3 = new DepositsAvailable();
        depositsAvailable3.setDepositId(1000003);
        depositsAvailable3.setDepositName("Senior Citizen FD");
        depositsAvailable3.setDepositType("Lump Sum");
        depositsAvailable3.setDepositDescription("Senior Citizen Fixed Deposit");
        depositsAvailable3.setDepositRoi(11.5);

        DepositsAvailable depositsAvailable4 = new DepositsAvailable();
        depositsAvailable4.setDepositId(1000004);
        depositsAvailable4.setDepositName("Employee FD");
        depositsAvailable4.setDepositType("Lump Sum");
        depositsAvailable4.setDepositDescription("Employee Fixed Deposit");
        depositsAvailable4.setDepositRoi(10.7);

        DepositsAvailable depositsAvailable5 = new DepositsAvailable();
        depositsAvailable5.setDepositId(1000005);
        depositsAvailable5.setDepositName("Women Empowerment RD");
        depositsAvailable5.setDepositType("Recurring");
        depositsAvailable5.setDepositDescription("Women Empowerment Recurring Deposit");
        depositsAvailable5.setDepositRoi(10.2);
        depositsAvailableList = Stream.of(depositsAvailable1, depositsAvailable2, depositsAvailable3, depositsAvailable4, depositsAvailable5).collect(Collectors.toList());

        depositsAvailed = new DepositsAvailed();
        depositsAvailed.setDepositAvailId(2L);
        depositsAvailed.setDepositId(1000002L);
        depositsAvailed.setCustomerId(100002L);
        depositsAvailed.setDepositAmount(400.0);
        depositsAvailed.setDepositDuration(1);
        depositsAvailed.setDepositMaturity(new Date("04/12/2024"));
    }


    //Pass
    @Test
    void testFindAllDeposits1() throws DepositsException, SQLException {
        when(jdbcTemplate.query(anyString(), any(RepositoryMyBank.DepositsAvailableMapper.class))).thenReturn(depositsAvailableList);

        assertEquals(depositsAvailableList, service.availableDeposits());
    }

    //Pass deposit 5 is missing
    @Test
    void testFindAllDeposits2() throws DepositsException, SQLException {
        when(jdbcTemplate.query(anyString(), any(RepositoryMyBank.DepositsAvailableMapper.class))).thenReturn(depositsAvailableList);

        assertNotEquals(depositsAvailableList.subList(0, 4), service.availableDeposits());
    }

    // Pass DepositsException is thrown
    @Test
    void testDepositException() {
        List<DepositsAvailable> depositsAvailableList = new ArrayList<>();

        when(jdbcTemplate.query(anyString(), any(RepositoryMyBank.DepositsAvailableMapper.class))).thenReturn(depositsAvailableList);

        assertThrows(DepositsException.class, () -> service.availableDeposits());
    }

    //fix
    @Test
    void testAvailDeposits_Success() throws Exception {
        // Mocking
        DepositsAvailed depositsAvailed = new DepositsAvailed();
        depositsAvailed.setCustomerId(1L);
        depositsAvailed.setDepositId(2L);
        depositsAvailed.setDepositAmount(1000.00);
        depositsAvailed.setDepositDuration(2);
        depositsAvailed.setDepositMaturity(java.sql.Date.valueOf(LocalDate.now()));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("p_result", "Success");

        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
                .thenReturn(resultMap);

        // Test
        String result = service.availDeposits(depositsAvailed);

        // Verification
        assertEquals("Success", result);
        verify(jdbcTemplate, times(1)).call(any(CallableStatementCreator.class), anyList());

        // Capturing the CallableStatementCreator
        verify(jdbcTemplate).call(callableStatementCreatorCaptor.capture(), anyList());
        CallableStatementCreator capturedCreator = callableStatementCreatorCaptor.getValue();

        // Mocking the connection and callable statement
        Connection mockConnection = mock(Connection.class);
        CallableStatement mockCallableStatement = mock(CallableStatement.class);
        when(mockConnection.prepareCall(anyString())).thenReturn(mockCallableStatement);

        // Creating the CallableStatement using the captured CallableStatementCreator
        capturedCreator.createCallableStatement(mockConnection);

        // Verifying the parameters are set correctly on the CallableStatement
        verify(mockCallableStatement).setLong(1, depositsAvailed.getCustomerId());
        verify(mockCallableStatement).setLong(2, depositsAvailed.getDepositId());
        verify(mockCallableStatement).setDouble(3, depositsAvailed.getDepositAmount());
        verify(mockCallableStatement).setInt(4, depositsAvailed.getDepositDuration());
        verify(mockCallableStatement).setDate(5, any(java.sql.Date.class));
        verify(mockCallableStatement).registerOutParameter(6, Types.VARCHAR);
    }

    @Test
    void testAvailDeposits_Failure() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("p_result", "Fail");
        Mockito.when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
                .thenReturn(resultMap);
        assertThrows(DepositsException.class, () -> service.availDeposits(depositsAvailed));
    }

    @Test
    public void testAvailableDeposits() throws SQLException {
        // Mocking behavior for jdbcTemplate.query() method
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RepositoryMyBank.DepositsAvailableMapper.class)))
                .thenReturn(depositsAvailableList);

        try {
            List<DepositsAvailable> result = service.availableDeposits();
            assertNotNull(result);
            assertFalse(result.isEmpty());
        } catch (DepositsException e) {
            fail("Exception not expected here");
        }
    }


    @Test
    public void testAvailableDepositsSQLException() throws SQLException, DepositsException {
        Mockito.when(jdbcTemplate.query(Mockito.anyString(), Mockito.any(RepositoryMyBank.DepositsAvailableMapper.class)))
                .thenThrow(new DataAccessException("Test SQLException") {
                });
        assertThrows(SQLException.class, () -> service.availableDeposits());

    }


    @Test
    public void testAvailDepositsUncategorizedSQLException1() {

        Mockito.when(jdbcTemplate.call(any(CallableStatementCreator.class), Mockito.anyList()))
                .thenThrow(new UncategorizedSQLException("test", "", new SQLException()));

        assertThrows(SQLException.class, () -> service.availDeposits(depositsAvailed));

    }

    @Test
    public void testAvailDepositsUncategorizedSQLException2() {

        Mockito.when(jdbcTemplate.call(Mockito.any(), Mockito.anyList()))
                .thenThrow(new UncategorizedSQLException("test", "", new SQLException("Test", "SQL", 20003)));

        assertThrows(DepositsException.class, () -> service.availDeposits(depositsAvailed));

    }

    @Test
    void testMapRow() throws SQLException {
        RepositoryMyBank.DepositsAvailableMapper mapper = new RepositoryMyBank.DepositsAvailableMapper();
        // Mock the ResultSet
        long depositId = 1000001L;
        String depositName = "FD";
        double depositRoi = 10.2;
        String depositType = "Lump Sum";
        String depositDescription = "Standard Fixed Deposit";

        // Set up the ResultSet to return the mocked values
        Mockito.when(resultSet.getLong(1)).thenReturn(depositId);
        Mockito.when(resultSet.getString(2)).thenReturn(depositName);
        Mockito.when(resultSet.getDouble(3)).thenReturn(depositRoi);
        Mockito.when(resultSet.getString(4)).thenReturn(depositType);
        Mockito.when(resultSet.getString(5)).thenReturn(depositDescription);

        // Call the mapRow method
        DepositsAvailable depositsAvailable = mapper.mapRow(resultSet, 0);

        // Verify that the mapping is correct
        assertEquals(depositsAvailable.getDepositId(), depositId);
        assertEquals(depositsAvailable.getDepositName(), depositName);
        assertEquals(depositsAvailable.getDepositRoi(), depositRoi);
        assertEquals(depositsAvailable.getDepositType(), depositType);
        assertEquals(depositsAvailable.getDepositDescription(), depositDescription);
    }

}

