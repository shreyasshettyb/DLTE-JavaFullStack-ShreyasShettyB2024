package mybank.db.dao.dltemybankdaolayer.remotes;

import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

//Interface of Dao
@Repository
public interface MyBankRemote {
    List<DepositsAvailable> availableDeposits() throws SQLException, DepositsException;
    String availDeposits(DepositsAvailed depositsAvailed) throws DepositsException, SQLException;

}
