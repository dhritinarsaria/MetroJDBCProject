package metro.presentation;
import metro.presentation.*;
import java.util.*;



import metro.entity.*;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.StationAlreadyExistsException;
import metro.exceptions.StationNotFoundException;
import metro.service.CardService;
import metro.service.CardServiceImpl;
import metro.service.StationService;
import metro.service.StationServiceImpl;

public class StationPresentationImpl implements StationPresentation {
	
	private StationService stationService;
    private Scanner scanner = new Scanner(System.in);
    
    private CardPresentationImpl cardPresentation;
    private CardService cardService;

    public StationPresentationImpl() {
    	this.stationService = new StationServiceImpl();
        this.cardService = new CardServiceImpl();
        this.cardPresentation = new CardPresentationImpl(cardService);
    }
    
    
    public void showStationMenu(Card card) throws CardNotFoundException, DatabaseConnectionException, Exception {
    	boolean keepRunning = true;

    	while (keepRunning) {
            System.out.println("\n--- Station Options ---");
            System.out.println("1. Start Journey");
            System.out.println("2. View All Stations");
            System.out.println("3. Get Station By Name");
            System.out.println("4. Add New Station");
            System.out.println("5. View user details");
            System.out.println("6. Login as another user");
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
                	 getStationByName(card,null);
                	                	    break;
                case 4:
                    addNewStation(card);
                    break;
                    
                case 5:
                	UserPresentation userPresentation1= new UserPresentationImpl();
                	userPresentation1.getUserDetails(1, card.getCardNo(), card);
                	
                	break;
                case 6:
                    keepRunning = false;  // exit station menu loop
                    UserPresentation userPresentation= new UserPresentationImpl();
                    userPresentation.askUser();
                    break;

                
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
    
   


    private void viewAllStations() {
        List<Station> stations = stationService.getAllStations();
        if (stations != null && !stations.isEmpty()) {
            stations.forEach(System.out::println);
        } else {
            System.out.println("No stations found.");
        }
    }

    private void getStationByName(Card card,String name) throws Exception {
    	if(name==null) {
        System.out.print("Enter station name: ");
        name = scanner.nextLine();}
    	
        Station station;
		try {
			station = stationService.getStationByName(name);
			if (station != null) {
	            System.out.println(station);
	        } else {
	            System.out.println("Station not found: " + name);
	            System.out.println("hi");
//	            this.showStationMenu(card);
	        }
		} catch (StationNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			this.showStationMenu(card);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			this.showStationMenu(card);
		}
        
        
    }

    private void addNewStation(Card card) throws StationAlreadyExistsException, Exception {
        System.out.print("Enter new station name: ");
        String newName = scanner.nextLine();
        try {
        stationService.addStation(newName);
        System.out.println("station added");
        this.getStationByName(card, newName);}
        catch(StationAlreadyExistsException ex){
        	System.out.println("Station already exists");
        
				this.getStationByName(card,newName);
        }
        catch(Exception e) {
        	System.out.println("Station already exists");
			//System.out.println(e.getMessage());
        	this.getStationByName(card,newName);
		}
        
			
    }
    
    public boolean isValidStationId(int stationId) {
    	try {
			return stationService.isValidStation(stationId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    	return false;
    	
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
