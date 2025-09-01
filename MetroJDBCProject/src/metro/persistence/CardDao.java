package metro.persistence;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;

import java.sql.SQLException;
import java.util.List;

public interface CardDao {
    Card getCardById(int cardNo)throws CardNotFoundException, Exception;
    List<Card> getAllCards();
    public void updateBalance(int cardNo, double newBalance) throws DatabaseConnectionException, SQLException;
    void deleteCard(int cardNo);
	Card createCard(String name, double balance)
			throws DatabaseConnectionException, InvalidAmountException, SQLException;
}
