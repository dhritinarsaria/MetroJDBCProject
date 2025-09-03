package metro.service;

import java.sql.SQLException;
import java.util.List;

import metro.entity.SwipeRecord;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;

public interface SwipeService {
    void swipeIn(int cardNo, int startStationId) throws DatabaseConnectionException;
    void swipeOut(int cardNo, int endStationId, double fare) throws DatabaseConnectionException;
    SwipeRecord getOpenJourneyByCard(int cardNo) throws DatabaseConnectionException;
    List<SwipeRecord> getSwipeHistoryByCard(int cardNo) throws DatabaseConnectionException;
}

