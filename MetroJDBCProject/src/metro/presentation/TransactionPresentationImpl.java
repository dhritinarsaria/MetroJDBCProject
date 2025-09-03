package metro.presentation;
 
import metro.entity.Transaction;
import metro.exceptions.DatabaseConnectionException;
import metro.service.TransactionService;
import metro.service.TransactionServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
 
public class TransactionPresentationImpl implements TransactionPresentation {
    private TransactionService transactionService = new TransactionServiceImpl();
    private Scanner scanner = new Scanner(System.in);
 
    public void showTransactions(int cardNo) throws DatabaseConnectionException, SQLException {
   
        List<Transaction> transactions = transactionService.viewTransactions(cardNo);
 
       
            System.out.println("Transaction History for Card " + cardNo + ":");
            for (Transaction txn : transactions) {
                System.out.println("ID: " + txn.getTransactionId() +
                                   ", Amount: " + txn.getAmount() +
                                   ", Date: " + txn.getTransactionDate());
            
            }
            if(transactions==null || transactions.isEmpty()) {
            	System.out.println("No Recharge History.");
            }
    }
}