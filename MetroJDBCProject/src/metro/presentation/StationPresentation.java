package metro.presentation;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.StationNotFoundException;

public interface StationPresentation {

    void showStationMenu(Card card) throws CardNotFoundException, DatabaseConnectionException, Exception;

    boolean isValidStationId(int stationId);

    // If you want presentation layer to explicitly expose these too:
     void viewAllStations() throws Exception;
     void getStationByName() throws StationNotFoundException, Exception;
     void addNewStation();
}
