package metro.presentation;

import metro.exceptions.DatabaseConnectionException;
import java.sql.SQLException;

public interface TransactionPresentation {
    void showTransactions(int cardNo) throws DatabaseConnectionException, SQLException;
}