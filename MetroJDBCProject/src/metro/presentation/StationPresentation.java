package metro.presentation;

import java.util.*;

import metro.entity.Station;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.StationNotFoundException;
import metro.service.StationService;
import metro.service.StationServiceImpl;

public class StationPresentation {
	
	private StationService stationService;
    private Scanner scanner = new Scanner(System.in);

    public StationPresentation() {
        this.stationService = new StationServiceImpl();
    }
    
    public void viewAllStations() throws Exception {
        List<Station> stations = stationService.getAllStations();
        if (stations != null && !stations.isEmpty()) {
            stations.forEach(System.out::println);
        } else {
            System.out.println("No stations found.");
        }
    }

    public void viewStationByName(String name) throws Exception {
        Station station = stationService.getStationByName(name);
        if (station != null) {
            System.out.println(station);
        } else {
            System.out.println("Station not found with name: " + name);
        }
    }


}
