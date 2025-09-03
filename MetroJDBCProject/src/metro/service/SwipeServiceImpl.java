package metro.service;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import metro.entity.SwipeRecord;
import metro.persistence.SwipeRecordsDao;
import metro.persistence.SwipeRecordsDaoImpl;
import metro.exceptions.*;

public class SwipeServiceImpl implements SwipeService {

    private SwipeRecordsDao swipeDao = new SwipeRecordsDaoImpl();

    @Override
    public void swipeIn(int cardNo, int startStationId) throws DatabaseConnectionException {
        SwipeRecord openJourney = swipeDao.getOpenJourneyByCard(cardNo);
        if (openJourney != null) {
            throw new DatabaseConnectionException("Card " + cardNo + " already has an open journey!");
        }
        swipeDao.swipeIn(cardNo, startStationId);
    }

    @Override
    public void swipeOut(int cardNo, int endStationId, double fare) throws DatabaseConnectionException {
        SwipeRecord openJourney = swipeDao.getOpenJourneyByCard(cardNo);
        if (openJourney == null) {
            throw new DatabaseConnectionException("No open journey found for card " + cardNo);
        }
        swipeDao.swipeOut(cardNo, endStationId, fare);
    }

    @Override
    public SwipeRecord getOpenJourneyByCard(int cardNo) throws DatabaseConnectionException {
        return swipeDao.getOpenJourneyByCard(cardNo);
    }

    @Override
    public List<SwipeRecord> getSwipeHistoryByCard(int cardNo) throws DatabaseConnectionException {
        return swipeDao.getRecordsByCard(cardNo);
    }
}
