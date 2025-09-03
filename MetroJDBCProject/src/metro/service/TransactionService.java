package metro.service;
 
import metro.entity.Transaction;
import metro.exceptions.DatabaseConnectionException;

import java.sql.SQLException;
import java.util.List;
 
public interface TransactionService {
    void recordTransaction(Transaction transaction) throws DatabaseConnectionException, SQLException;
    List<Transaction> viewTransactions(int cardNo) throws DatabaseConnectionException, SQLException;
}