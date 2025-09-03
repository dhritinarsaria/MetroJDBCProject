package metro.presentation;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;

public interface UserPresentation {
    void askUser() throws CardNotFoundException, DatabaseConnectionException, Exception;
    void userType(int type) throws CardNotFoundException, DatabaseConnectionException, Exception;
    void getUserDetails(int type, int cardNo, Card card) throws CardNotFoundException, Exception;
    void getDetails(Card card, int type) throws CardNotFoundException, DatabaseConnectionException, Exception;
}
