package mybank.dao.repository;

import mybank.dao.repository.entity.DepositsAvailable;
import mybank.dao.repository.entity.DepositsAvailed;
import mybank.dao.repository.exception.DepositsException;
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