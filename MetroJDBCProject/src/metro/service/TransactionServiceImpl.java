package metro.service;
 
import metro.entity.Transaction;
import metro.exceptions.DatabaseConnectionException;
import metro.persistence.TransactionDao;
import metro.persistence.TransactionDaoImpl;

import java.sql.SQLException;
import java.util.List;
 
public class TransactionServiceImpl implements TransactionService {
    private TransactionDao transactionDao = new TransactionDaoImpl();
 
    @Override
    public void recordTransaction(Transaction transaction) throws DatabaseConnectionException, SQLException {
        transactionDao.saveTransaction(transaction);
    }
 
    @Override
    public List<Transaction> viewTransactions(int cardNo) throws DatabaseConnectionException, SQLException {
        return transactionDao.getTransactionsByCard(cardNo);
    }
}