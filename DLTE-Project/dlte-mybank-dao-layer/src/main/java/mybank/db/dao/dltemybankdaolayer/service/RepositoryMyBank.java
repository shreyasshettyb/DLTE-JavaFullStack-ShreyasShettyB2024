package mybank.db.dao.dltemybankdaolayer.service;

import mybank.db.dao.dltemybankdaolayer.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class RepositoryMyBank implements MyBankRemote {

    @Autowired
    JdbcTemplate jdbcTemplate;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(RepositoryMyBank.class);

    //Return All Deposits in Deposits Available Table
    @Override
    public List<DepositsAvailable> availableDeposits() throws SQLException, DepositsException {
        List<DepositsAvailable> depositsAvailableList = null;
        try {
            depositsAvailableList = jdbcTemplate.query("select * from mybank_app_depositsavailable", new DepositsAvailableMapper());
            logger.info(resourceBundle.getString("app.execute.success"));
        } catch (DataAccessException sqlException) {
            logger.error(sqlException + resourceBundle.getString("app.error.access"));
            throw new SQLException();
        }
        if (depositsAvailableList.size() == 0) {
            logger.error(resourceBundle.getString("app.error.empty"));
            throw new DepositsException(resourceBundle.getString("app.codes.empty"));
        }
        return depositsAvailableList;
    }

    @Override
    public List<DepositsAvailable> findDepositsByRoi(double roi) {
        return null;
    }

    @Override
    public List<DepositsAvailable> findDepositsById(long deposits_id) {
        return null;
    }

    //Return Success If Avail Deposit is added to table
    @Override
    public String availDeposits(DepositsAvailed depositsAvailed) throws DepositsException, SQLException {
//        try {
//            int ack = jdbcTemplate.update("insert into MYBANK_APP_DEPOSITSAVAILED values(MY_BANK_APP_SEQ_DEPOSITSGIVEN.nextval,?,?,?,?,?)",
//                    new Object[]{
//                            depositsAvailed.getCustomerId(),
//                            depositsAvailed.getDepositId(),
//                            depositsAvailed.getDepositAmount(),
//                            depositsAvailed.getDepositDuration(),
//                            depositsAvailed.getDepositMaturity(),
//                    });
//
//            if (ack != 0)
//                return "Success";
//        } catch (DataAccessException e) {
//            logger.error(e + resourceBundle.getString("app.error.access"));
//            throw new DepositsException("Creation Failed");
//        }
//        return "Fail";
        CallableStatementCreator creator= con -> {
            CallableStatement statement=con.prepareCall("{call mybank_loans_deletion(?,?)}");
            statement.setLong(1,loanId);
            statement.registerOutParameter(2, Types.VARCHAR);
            return statement;
        };

        Map<String,Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                new SqlParameter[]{
                        new SqlParameter(Types.NUMERIC),
                        new SqlOutParameter("errOrInfo",Types.VARCHAR),
                }
        ));

        return returnedExecution.get("errOrInfo").toString();
    }

    //Maps the query output to Entity/Model
    public class DepositsAvailableMapper implements RowMapper<DepositsAvailable> {

        @Override
        public DepositsAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            DepositsAvailable depositsAvailable = new DepositsAvailable();
            depositsAvailable.setDepositId(rs.getLong(1));
            depositsAvailable.setDepositName(rs.getString(2));
            depositsAvailable.setDepositRoi(rs.getDouble(3));
            depositsAvailable.setDepositType(rs.getString(4));
            depositsAvailable.setDepositDescription(rs.getString(5));
            System.out.println(depositsAvailable.toString());
            return depositsAvailable;
        }
    }
}
