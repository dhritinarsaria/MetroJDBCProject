package metro.presentation;

import metro.exceptions.DatabaseConnectionException;

public interface SwipePresentation {
    void showSwipeHistory(int cardNo) throws DatabaseConnectionException;
}
