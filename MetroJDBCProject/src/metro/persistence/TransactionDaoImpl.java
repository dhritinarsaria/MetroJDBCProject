package metro.persistence;

import metro.entity.Transaction;
import metro.exceptions.DatabaseConnectionException;
import metro.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public void saveTransaction(Transaction transaction) throws DatabaseConnectionException, SQLException {
        String query = "INSERT INTO transactions (card_no, amount) VALUES (?, ?)";
        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, transaction.getCardNo());
            ps.setDouble(2, transaction.getAmount());
            ps.executeUpdate();
           
        } catch (SQLException e) {
        	
            throw e; // or wrap in DatabaseConnectionException if you prefer
        }
    }

    @Override
    public List<Transaction> getTransactionsByCard(int cardNo) throws DatabaseConnectionException, SQLException {
        String query = "SELECT transaction_id, card_no, amount, transaction_date " +
                       "FROM transactions WHERE card_no = ? ORDER BY transaction_date DESC";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, cardNo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction txn = new Transaction();
                    txn.setTransactionId(rs.getInt("transaction_id"));
                    txn.setCardNo(rs.getInt("card_no"));
                    txn.setAmount(rs.getDouble("amount"));
                    txn.setTransactionDate(rs.getTimestamp("transaction_date"));
                    transactions.add(txn);
                }
            }
        } catch (SQLException e) {
            throw e; // or wrap
        }

        return transactions;
    }
}
