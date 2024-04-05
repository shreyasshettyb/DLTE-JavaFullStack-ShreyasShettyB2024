package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

//Interface of Dao
@Repository
public interface MyBankRemote {
   List<DepositsAvailable> availableDeposits() throws SQLException, DepositsException;
   List<DepositsAvailable> findDepositsByRoi(double roi);
   List<DepositsAvailable> findDepositsById(long deposits_id);
   String availDeposits(DepositsAvailed depositsAvailed);
}
