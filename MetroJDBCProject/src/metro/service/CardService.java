package metro.service;

import java.sql.SQLException;

import metro.entity.Card;
import metro.entity.SwipeRecord;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;

public interface CardService {
    Card createCard(String name, double initialBalance) throws DatabaseConnectionException, InvalidAmountException, SQLException;  // create new card for a user
    Card getCardById(int cardNo) throws CardNotFoundException, Exception;                        // fetch card
//    double checkBalance(int cardNo);                     // check balance
    void rechargeCard(Card card, double amount) throws DatabaseConnectionException, SQLException, CardNotFoundException;        // add balance
    public void deductFare(SwipeRecord record) throws DatabaseConnectionException, CardNotFoundException, SQLException;
}