package metro.presentation;

import java.sql.SQLException;

import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;

public interface SwipePresentation {
    void showSwipeHistory(int cardNo) throws DatabaseConnectionException;

	void swipeIn(int cardNo) throws DatabaseConnectionException, SQLException, CardNotFoundException;

	void swipeOut(int cardNo) throws DatabaseConnectionException, SQLException, CardNotFoundException, InvalidAmountException;
}
