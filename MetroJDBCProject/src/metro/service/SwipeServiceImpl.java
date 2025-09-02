package metro.service;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import metro.entity.SwipeRecord;
import metro.persistence.SwipeRecordsDao;
import metro.persistence.SwipeRecordsDaoImpl;
import metro.exceptions.*;

public class SwipeServiceImpl implements SwipeService {
	
	 SwipeRecordsDao swipeRecordsDao = new SwipeRecordsDaoImpl();
	 
	 @Override
	 public void addSwipeRecord(SwipeRecord record) throws DatabaseConnectionException {
		 record.setStartTime(Timestamp.valueOf(LocalDateTime.now())); // auto-generate
		 record.setEndTime(Timestamp.valueOf(LocalDateTime.now())); // auto-generate
		 record.setDate(Date.valueOf(LocalDate.now()));               // auto-generate
		 swipeRecordsDao.addSwipeRecord(record);
	 }
	 
	 @Override
	 public List<SwipeRecord> getSwipeHistoryByCard(int cardNo) throws DatabaseConnectionException{
	     return swipeRecordsDao.getRecordsByCard(cardNo);
	 }

}
