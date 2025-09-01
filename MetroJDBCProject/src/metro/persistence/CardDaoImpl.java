package metro.persistence;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;
import metro.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {

    @Override
    public Card getCardById(int cardNo) throws CardNotFoundException, DatabaseConnectionException, SQLException {
        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT * FROM Card WHERE cardNo = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, cardNo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Card(
                    rs.getInt("cardNo"),
                    rs.getDouble("balance"),
                    rs.getInt("userId")
            );
        } else {
            throw new CardNotFoundException("Card with number " + cardNo + " not found.");
        }
    }

    @Override
    public List<Card> getAllCards() throws DatabaseConnectionException, SQLException {
        Connection connection = DBConnectionUtil.getConnection();
        List<Card> cards = new ArrayList<>();

        String query = "SELECT * FROM Card";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            cards.add(new Card(
                    rs.getInt("cardNo"),
                    rs.getDouble("balance"),
                    rs.getInt("userId")
            ));
        }
        return cards;
    }

    @Override
    public Card createCard(String name, double balance)
            throws DatabaseConnectionException, InvalidAmountException, SQLException {

        if (balance < 100) {
            throw new InvalidAmountException("Minimum balance for creating a card is 100");
        }

        Connection connection = DBConnectionUtil.getConnection();
        connection.setAutoCommit(false);

        // Insert into User table
        String userSql = "INSERT INTO User (name, email) VALUES (?, ?)";
        PreparedStatement userStmt = connection.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
        userStmt.setString(1, name);
        userStmt.setString(2, name.toLowerCase().replaceAll("\\s+", "") + "@metro.com"); // simple email
        userStmt.executeUpdate();

        ResultSet userRs = userStmt.getGeneratedKeys();
        int userId = 0;
        if (userRs.next()) {
            userId = userRs.getInt(1);
        }

        // Insert into Card table
        String cardSql = "INSERT INTO Card (balance, userId) VALUES (?, ?)";
        PreparedStatement cardStmt = connection.prepareStatement(cardSql, PreparedStatement.RETURN_GENERATED_KEYS);
        cardStmt.setDouble(1, balance);
        cardStmt.setInt(2, userId);
        cardStmt.executeUpdate();

        ResultSet cardRs = cardStmt.getGeneratedKeys();
        int cardNo = 0;
        if (cardRs.next()) {
            cardNo = cardRs.getInt(1);
        }

        connection.commit();
        connection.setAutoCommit(true);

        return new Card(cardNo, balance, userId);
    }

    @Override
    public void updateBalance(int cardNo, double newBalance)
            throws DatabaseConnectionException, CardNotFoundException, SQLException {

        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Card SET balance = ? WHERE cardNo = ?");

        stmt.setDouble(1, newBalance);
        stmt.setInt(2, cardNo);

        int rows = stmt.executeUpdate();
        if (rows == 0) {
            throw new CardNotFoundException("Card with number " + cardNo + " not found.");
        }
    }

    @Override
    public void alterBalance(int cardNo, double deductionAmount)
            throws DatabaseConnectionException, CardNotFoundException, SQLException {

        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Card SET balance = balance - ? WHERE cardNo = ?");

        stmt.setDouble(1, deductionAmount);
        stmt.setInt(2, cardNo);

        int rows = stmt.executeUpdate();
        if (rows == 0) {
            throw new CardNotFoundException("Card with number " + cardNo + " not found.");
        }
    }


  

}
