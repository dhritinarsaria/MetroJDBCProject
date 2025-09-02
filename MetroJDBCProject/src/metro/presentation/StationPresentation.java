package metro.presentation;
import metro.presentation.*;
import java.util.*;



import metro.entity.*;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.StationNotFoundException;
import metro.service.CardService;
import metro.service.CardServiceImpl;
import metro.service.StationService;
import metro.service.StationServiceImpl;

public class StationPresentation {
	
	private StationService stationService;
    private Scanner scanner = new Scanner(System.in);
    
    private CardPresentation cardPresentation;
    private CardService cardService;

    public StationPresentation() {
    	this.stationService = new StationServiceImpl();
        this.cardService = new CardServiceImpl();
        this.cardPresentation = new CardPresentation(cardService);
    }
    
    
    public void showStationMenu(Card card) throws CardNotFoundException, DatabaseConnectionException, Exception {
    	boolean keepRunning = true;

    	while (keepRunning) {
            System.out.println("\n--- Station Options ---");
            System.out.println("1. Start Journey");
            System.out.println("2. View All Stations");
            System.out.println("3. Get Station By Name");
            System.out.println("4. Add New Station");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int subChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (subChoice) {
                case 1:
                  
                    cardPresentation.startJourney(card);
                    break;
                case 2:
                    viewAllStations();
                    break;
                case 3:
                    getStationByName();
                    break;
                case 4:
                    addNewStation();
                    break;
                case 5:
//                	System.out.println("Exiting...");
//                    System.exit(0); 
                	UserPresentation userPresentation= new UserPresentation();
                	userPresentation.askUser();
                	
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    
   
//    private void startJourney(Card card) {
//        if (card.getBalance() < 20) {
//            cardPresentation.rechargeCard(card);
//        } else {
//            FarePresentation farePresentation = new FarePresentation();
//            farePresentation.fare(card);
//        }
//    }

    private void viewAllStations() {
        List<Station> stations = stationService.getAllStations();
        if (stations != null && !stations.isEmpty()) {
            stations.forEach(System.out::println);
        } else {
            System.out.println("No stations found.");
        }
    }


    private void getStationByName() {
        System.out.print("Enter station name: ");
        String name = scanner.nextLine();
        Station station = stationService.getStationByName(name);
        if (station != null) {
            System.out.println(station);
        } else {
            System.out.println("Station not found: " + name);
        }
    }

    private void addNewStation() {
        System.out.print("Enter new station name: ");
        String newName = scanner.nextLine();
        stationService.addStation(newName);
    }
    
    public boolean isValidStationId(int stationId) {
    	return stationService.isValidStation(stationId);
    	
    }
    
//    public void viewAllStations() throws Exception {
//        List<Station> stations = stationService.getAllStations();
//        if (stations != null && !stations.isEmpty()) {
//            stations.forEach(System.out::println);
//        } else {
//            System.out.println("No stations found.");
//        }
//    }
//
//    public void viewStationByName(String name) throws StationNotFoundException {
//        Station station = stationService.getStationByName(name);
//        if (station != null) {
//            System.out.println(station);}
////        } else {
////            System.out.println("Station not found with name: " + name);
////        }
//    }
//
//	public void addStation(String newName) {
//		try {
//            stationService.addStation(newName);
//        } catch (Exception e) {
//            System.out.println("Error adding station: " + e.getMessage());
//        }
//		
//	}


}
