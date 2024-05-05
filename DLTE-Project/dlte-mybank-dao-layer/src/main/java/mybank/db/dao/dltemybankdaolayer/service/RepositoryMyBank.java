package mybank.db.dao.dltemybankdaolayer.service;

import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import mybank.db.dao.dltemybankdaolayer.remotes.MyBankRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class RepositoryMyBank implements MyBankRemote {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CustomerAuthService customerAuthService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    Logger logger = LoggerFactory.getLogger(RepositoryMyBank.class);

    //Return All Deposits in Deposits Available Table
    @Override
    public List<DepositsAvailable> availableDeposits() throws DepositsException, SQLException {
        List<DepositsAvailable> depositsAvailableList = null;
        try {
            depositsAvailableList = jdbcTemplate.query("select * from mybank_app_depositsavailable", new DepositsAvailableMapper());
            logger.info(resourceBundle.getString("db.execute.success"));
        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("db.error.access")+": "+sqlException.getMessage());
            throw new SQLException(resourceBundle.getString("db.error.access.code"));
        }
        if (depositsAvailableList.size() == 0) {
            logger.error(resourceBundle.getString("db.error.empty"));
            throw new DepositsException(resourceBundle.getString("db.error.empty.code"));
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
        LocalDate nowDate = depositsAvailed.getDepositMaturity().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        nowDate = nowDate.plusYears(depositsAvailed.getDepositDuration());
        depositsAvailed.setDepositMaturity(Date.from(nowDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Date sqlDate = Date.valueOf(nowDate);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Long customerId = customerAuthService.findByUsername(authentication.getName()).getCustomerId();
//        depositsAvailed.setCustomerId(customerId);

        CallableStatementCreator creator = con -> {
            CallableStatement statement = con.prepareCall("{call avail_deposits(?,?,?,?,?,?)}");
            statement.setLong(1, depositsAvailed.getCustomerId());
            statement.setLong(2, depositsAvailed.getDepositId());
            statement.setDouble(3, depositsAvailed.getDepositAmount());
            statement.setInt(4, depositsAvailed.getDepositDuration());
            statement.setDate(5, sqlDate);
            statement.registerOutParameter(6, Types.VARCHAR);
            return statement;
        };

        try {
            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.NUMERIC),
                            new SqlParameter(Types.INTEGER),
                            new SqlParameter(Types.DATE),
                            new SqlOutParameter("p_result", Types.VARCHAR),
                    }
            ));
            // Get the result from the map
            String result = (String) returnedExecution.get("p_result");

            if (result.equals("Success")) {
                logger.info(resourceBundle.getString("db.execute.success"));
                return "Success";
            } else {
                logger.error("Fail");
                throw new DepositsException(resourceBundle.getString("db.error.fail.code"));
            }
        } catch (UncategorizedSQLException e) {
            if (e.getSQLException().getErrorCode() == 20003) {
                logger.error(resourceBundle.getString("db.error.notfound")+": "+e.getSQLException().getMessage());
                throw new DepositsException(resourceBundle.getString("db.error.notfound.code"));
            } else {
                logger.error(resourceBundle.getString("db.error.unknown")+": "+e.getSQLException().getMessage());
                throw new SQLException(resourceBundle.getString("db.error.unknown.code"));
            }
        }
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
            return depositsAvailable;
        }
    }
}
