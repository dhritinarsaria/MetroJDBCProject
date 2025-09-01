package metro.service;

import java.util.List;

import metro.entity.Station;

public interface StationService {
    List<Station> getAllStations();              // fetch all
    Station getStationById(int stationId);       // fetch by id
    Station getStationByName(String name);       // fetch by name
    int countStations();                         // total stations
    void addStation(String name);                // add new station
}