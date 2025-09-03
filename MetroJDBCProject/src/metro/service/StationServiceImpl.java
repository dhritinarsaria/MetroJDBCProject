package metro.service;

import java.util.List;

import metro.entity.Station;
import metro.exceptions.StationAlreadyExistsException;
import metro.exceptions.StationNotFoundException;
import metro.persistence.*;

public class StationServiceImpl implements StationService{
	
	private final StationDao stationDao;

    public StationServiceImpl() {
        this.stationDao = new StationDaoImpl();
    }

    @Override
    public List<Station> getAllStations() {
        try {
            return stationDao.getAllStations();
        } catch (Exception e) {
            System.err.println("Error fetching all stations: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Station getStationById(int stationId) throws StationNotFoundException, Exception {
       
            return stationDao.getStationById(stationId);
        
    }

    @Override
    public Station getStationByName(String name) throws StationNotFoundException, Exception {
       
            return stationDao.getStationByName(name);
       
    }

    @Override
    public int countStations() throws Exception {
    
            return stationDao.countStations();
      
    }

    @Override
    public void addStation(String name)throws StationAlreadyExistsException,Exception {
    
            Station station = new Station(0, name); 
            // pass 0 or ignore ID, DB will auto-generate
            stationDao.addStation(station);
       
    }

	@Override
	public boolean isValidStation(int id) throws StationNotFoundException, Exception {
		if(this.getStationById(id)!=null) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
