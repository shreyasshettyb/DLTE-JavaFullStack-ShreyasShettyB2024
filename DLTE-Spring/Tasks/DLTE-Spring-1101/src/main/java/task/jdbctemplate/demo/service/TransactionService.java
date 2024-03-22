package task.jdbctemplate.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import task.jdbctemplate.demo.model.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction apiAddTransaction(Transaction transaction) {
        jdbcTemplate.update("INSERT INTO transaction_new (transaction_id, amount, received_by, remarks, sent_to, transaction_date ) VALUES (?, ?, ?, ?, ?, ?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getAmount(),
                        transaction.getReceivedBy(),
                        transaction.getRemarks(),
                        transaction.getSentTo(),
                        transaction.getTransactionDate()});
        return transaction;
    }

    public List<Transaction> apiFindBySender(String sender) {
        return jdbcTemplate.query("SELECT * FROM transaction_new WHERE sent_to = ?",
                new Object[]{sender},
                new TransactionMapper());
    }

    public List<Transaction> apiFindByReceiver(String receiver) {
        return jdbcTemplate.query("SELECT * FROM transaction_new WHERE received_by = ?",
                new Object[]{receiver},
                new TransactionMapper());
    }

    public List<Transaction> apiFindByAmount(Double amount) {
        return jdbcTemplate.query("SELECT * FROM transaction_new WHERE amount = ?",
                new Object[]{amount},
                new TransactionMapper());
    }

    private class TransactionMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction=new Transaction();
            transaction.setTransactionId(rs.getLong(1));
            transaction.setAmount(rs.getDouble(2));
            transaction.setReceivedBy(rs.getString(3));
            transaction.setRemarks(rs.getString(4));
            transaction.setSentTo(rs.getString(5));
            transaction.setTransactionDate(rs.getDate(6));
            return transaction;
        }
    }
}
