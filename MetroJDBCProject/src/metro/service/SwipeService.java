package metro.service;

import java.util.List;

import metro.entity.SwipeRecord;

public interface SwipeService {
    void swipeIn(int cardNo, int stationId);     // swipe in
    double swipeOut(int cardNo, int stationId);  // swipe out (returns fare deducted)
    List<SwipeRecord> getSwipeHistoryByCard(int cardNo); // all trips for a card
    void addSwipeRecord(Record record);
}
