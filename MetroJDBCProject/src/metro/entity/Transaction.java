package metro.entity;
 
import java.sql.Timestamp;
 
public class Transaction {
    private int transactionId;
    private int cardNo;
    private double amount;
    private Timestamp transactionDate;
 
    // Getters and setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
 
    public int getCardNo() { return cardNo; }
    public void setCardNo(int cardNo) { this.cardNo = cardNo; }
 
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
 
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
}