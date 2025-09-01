package metro.persistence;

import metro.entity.SwipeRecord;
import metro.exceptions.DatabaseConnectionException;

import java.util.List;

public interface SwipeRecordsDao {
    void addSwipeRecord(SwipeRecord record) throws DatabaseConnectionException;
    List<SwipeRecord> getRecordsByCard(int cardNo) throws DatabaseConnectionException;
    List<SwipeRecord> getAllRecords() throws DatabaseConnectionException;
}