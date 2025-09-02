package metro.persistence;

import java.util.List;

import metro.entity.SwipeRecord;
import metro.exceptions.DatabaseConnectionException;

public class SwipeRecordsDaoImpl implements SwipeRecordsDao{

	@Override
	public void addSwipeRecord(SwipeRecord record) throws DatabaseConnectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SwipeRecord> getRecordsByCard(int cardNo) throws DatabaseConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SwipeRecord> getAllRecords() throws DatabaseConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

}
