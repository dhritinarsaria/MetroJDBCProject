package metro.presentation;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;

import java.sql.SQLException;

public interface CardPresentation {

    Card isValidCard(int cardNo) throws CardNotFoundException, DatabaseConnectionException, Exception;

    Card createCard() throws DatabaseConnectionException, InvalidAmountException, SQLException;

    void startJourney(Card card) throws CardNotFoundException, DatabaseConnectionException, Exception;

    void rechargeCard(Card card) throws CardNotFoundException, DatabaseConnectionException, Exception;
}
