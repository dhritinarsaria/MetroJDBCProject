package metro.service;

import java.sql.SQLException;

import metro.entity.Card;
import metro.entity.SwipeRecord;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;

public interface CardService {
	 public boolean hasEnoughBalance(int cardNo) throws CardNotFoundException, Exception ;
    Card createCard(String name, double initialBalance) throws DatabaseConnectionException, InvalidAmountException, SQLException;  // create new card for a user
    Card getCardById(int cardNo) throws CardNotFoundException, Exception;                        
    void rechargeCard(Card card, double amount) throws DatabaseConnectionException, SQLException, CardNotFoundException;        // add balance
    public void deductFare(SwipeRecord record) throws DatabaseConnectionException, CardNotFoundException, SQLException, Exception;
    public boolean isValidCard(int cardNo) throws CardNotFoundException, Exception;
}