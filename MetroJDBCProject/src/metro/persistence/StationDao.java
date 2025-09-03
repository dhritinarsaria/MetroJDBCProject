package metro.persistence;


import metro.entity.Station;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.StationAlreadyExistsException;
import metro.exceptions.StationNotFoundException;

import java.util.List;

public interface StationDao {
    List<Station> getAllStations() throws Exception;

    public void addStation(Station station) throws DatabaseConnectionException, Exception, StationAlreadyExistsException ;

    int countStations() throws Exception;

    Station getStationById(int stationId) throws StationNotFoundException, Exception;

    Station getStationByName(String stationName) throws StationNotFoundException, Exception;
}
