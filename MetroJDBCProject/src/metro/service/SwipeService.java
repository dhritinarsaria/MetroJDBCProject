package metro.service;

import java.util.List;

import metro.entity.SwipeRecord;
import metro.exceptions.DatabaseConnectionException;

public interface SwipeService {
	void addSwipeRecord(SwipeRecord record) throws DatabaseConnectionException;
//    void swipeIn(int cardNo, int stationId);     // swipe in
//    double swipeOut(int cardNo, int stationId);  // swipe out (returns fare deducted)
    List<SwipeRecord> getSwipeHistoryByCard(int cardNo) throws DatabaseConnectionException; // all trips for a card
    
}
