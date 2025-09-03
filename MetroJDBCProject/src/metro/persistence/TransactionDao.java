package metro.persistence;

import java.sql.SQLException;
import java.util.List;
import metro.entity.Transaction;
import metro.exceptions.DatabaseConnectionException;

public interface TransactionDao {
   void saveTransaction(Transaction transaction) throws DatabaseConnectionException, SQLException;
   List<Transaction> getTransactionsByCard(int cardNo) throws DatabaseConnectionException, SQLException;
}
