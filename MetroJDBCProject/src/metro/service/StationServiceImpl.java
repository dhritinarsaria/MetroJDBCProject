package metro.service;

import java.util.List;

import metro.entity.Station;
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
    public Station getStationById(int stationId) {
        try {
            return stationDao.getStationById(stationId);
        } catch (StationNotFoundException e) {
            System.err.println("Station not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error fetching station by ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Station getStationByName(String name) {
        try {
            return stationDao.getStationByName(name);
        } catch (StationNotFoundException e) {
//            System.err.println("Station not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error fetching station by name: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int countStations() {
        try {
            return stationDao.countStations();
        } catch (Exception e) {
            System.err.println("Error counting stations: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public void addStation(String name) {
    	try {
            Station station = new Station(0, name); 
            // pass 0 or ignore ID, DB will auto-generate
            stationDao.addStation(station);
            System.out.println("Station added: " + station.getStationName());
        } catch (Exception e) {
            System.err.println("Error adding station: " + e.getMessage());
        }
    }

}
