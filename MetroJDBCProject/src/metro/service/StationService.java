package metro.service;

import java.util.List;

import metro.entity.Station;
import metro.exceptions.StationAlreadyExistsException;
import metro.exceptions.StationNotFoundException;

public interface StationService {
    List<Station> getAllStations();              // fetch all
    Station getStationById(int stationId) throws StationNotFoundException, Exception;       // fetch by id
    Station getStationByName(String name) throws StationNotFoundException, Exception;       // fetch by name
    int countStations() throws Exception;                         // total stations
    void addStation(String name) throws StationAlreadyExistsException, Exception;                // add new station
    boolean isValidStation(int id) throws StationNotFoundException, Exception;
}