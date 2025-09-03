package metro.persistence;

import metro.entity.SwipeRecord;
import metro.exceptions.DatabaseConnectionException;

import java.sql.SQLException;
import java.util.List;

public interface SwipeRecordsDao {
    void swipeIn(int cardNo, int startStationId) throws DatabaseConnectionException;
    void swipeOut(int cardNo, int endStationId, double fare) throws DatabaseConnectionException;
    SwipeRecord getOpenJourneyByCard(int cardNo) throws DatabaseConnectionException;
    List<SwipeRecord> getRecordsByCard(int cardNo) throws DatabaseConnectionException;}
