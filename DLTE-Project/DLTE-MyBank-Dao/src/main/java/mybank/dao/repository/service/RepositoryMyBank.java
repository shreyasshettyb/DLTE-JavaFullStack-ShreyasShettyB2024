package mybank.dao.repository.service;

import mybank.dao.repository.MyBankRemote;
import mybank.dao.repository.entity.DepositsAvailable;
import mybank.dao.repository.exception.DepositsException;
import mybank.dao.repository.entity.DepositsAvailed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Service
public class RepositoryMyBank implements MyBankRemote {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<DepositsAvailable> availableDeposits() throws SQLSyntaxErrorException, DepositsException {
        List<DepositsAvailable> depositsAvailableList=null;
        try{
            depositsAvailableList=jdbcTemplate.query("select * from mybank_app_depositsavailable",new DepositsAvailableMapper());
        }
        catch (DataAccessException sqlException){
            throw new SQLSyntaxErrorException();
        }
        if(depositsAvailableList.size()==0){
            throw new DepositsException("No Deposits Are Available");
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

    protected class DepositsAvailableMapper implements RowMapper<DepositsAvailable> {

        @Override
        public DepositsAvailable mapRow(ResultSet rs, int rowNum) throws SQLException {
            DepositsAvailable depositsAvailable = new DepositsAvailable();
            depositsAvailable.setDeposit_id(rs.getLong(1));
            depositsAvailable.setDeposit_name(rs.getString(2));
            depositsAvailable.setDeposit_roi(rs.getDouble(3));
            depositsAvailable.setDeposit_type(rs.getString(4));
            depositsAvailable.setDeposit_description(rs.getString(5));
            return depositsAvailable;
        }
    }
}
