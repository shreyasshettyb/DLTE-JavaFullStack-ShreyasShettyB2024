package task.soapxsd.soapxssd.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import task.soapxsd.soapxssd.dao.model.Transaction;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction apiAddTransaction(Transaction transaction) {
        jdbcTemplate.update("INSERT INTO transaction_new (transaction_id, amount, received_by, remarks, sent_to, transaction_date ) VALUES (?, ?, ?, ?, ?, ?)",
                transaction.getTransactionId(),
                transaction.getAmount(),
                transaction.getReceivedBy(),
                transaction.getRemarks(),
                transaction.getSentTo(),
                transaction.getTransactionDate());
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

    public String apiUpdateRemarks(String remarks,long transaction_id){
        int result=jdbcTemplate.update("UPDATE transaction_new SET remarks = ? WHERE transaction_id = ?",
                remarks, transaction_id);
        if(result!=0)
            return "success";
        else
            return "fail";
    }

    public String apiRemoveTransactionByDates(Date startDate, Date endDate) {
        System.out.println("inside transaction");
        int result =jdbcTemplate.update("DELETE FROM transaction_new WHERE transaction_date BETWEEN ? AND ?",
                startDate, endDate);
        if(result!=0)
            return "success";
        else
            return "fail";
    }

    public class TransactionMapper implements RowMapper<Transaction> {

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setTransactionId(rs.getLong(1));
            transaction.setTransactionDate(rs.getDate(2));
            transaction.setSentTo(rs.getString(3));
            transaction.setReceivedBy(rs.getString(4));
            transaction.setAmount(rs.getDouble(5));
            transaction.setRemarks(rs.getString(6));
            return transaction;
        }
    }
}
