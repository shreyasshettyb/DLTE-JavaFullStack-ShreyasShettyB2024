package mybank.db.dao.dltemybankdaolayer;

import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailable;
import mybank.db.dao.dltemybankdaolayer.entity.DepositsAvailed;
import mybank.db.dao.dltemybankdaolayer.exception.DepositsException;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface MyBankRemote {

   List<DepositsAvailable> availableDeposits() throws SQLSyntaxErrorException, DepositsException;
   List<DepositsAvailable> findDepositsByRoi(double roi);
   List<DepositsAvailable> findDepositsById(long deposits_id);
   String availDeposits(DepositsAvailed depositsAvailed);
}
