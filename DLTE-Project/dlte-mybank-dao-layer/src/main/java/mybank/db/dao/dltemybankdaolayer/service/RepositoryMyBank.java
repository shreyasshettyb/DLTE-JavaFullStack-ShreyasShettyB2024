package mybank.db.dao.dltemybankdaolayer.service;

import mybank.db.dao.dltemybankdaolayer.MyBankRemote;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class RepositoryMyBank implements MyBankRemote {

    @Autowired
    JdbcTemplate jdbcTemplate;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(RepositoryMyBank.class);

    //Return All Deposits in Deposits Available Table
    @Override
    public List<DepositsAvailable> availableDeposits() throws SQLException, DepositsException {
        List<DepositsAvailable> depositsAvailableList=null;
        try{
            depositsAvailableList=jdbcTemplate.query("select * from mybank_app_depositsavailable",new DepositsAvailableMapper());
            logger.info(resourceBundle.getString("app.execute.success"));
        }
        catch (DataAccessException sqlException){
            logger.error(resourceBundle.getString("app.error.access"));
            throw new SQLException();
        }
        if(depositsAvailableList.size()==0){
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

    @Override
    public String availDeposits(DepositsAvailed depositsAvailed) {
        return null;
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
