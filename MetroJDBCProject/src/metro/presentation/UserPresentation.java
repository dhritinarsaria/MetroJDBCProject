package metro.presentation;

import java.util.Scanner;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.service.*;

public class UserPresentation {
	Scanner scanner= new Scanner(System.in);
	
	
	public void askUser() throws CardNotFoundException, DatabaseConnectionException, Exception {
		System.out.println("Press 1 if you have valid card \nPress 2 to create a new card\nPress 3 to exit");
        int userType = scanner.nextInt();
        userType(userType);
	}
	public void userType(int type) throws CardNotFoundException, DatabaseConnectionException, Exception {
		CardService cardService = new CardServiceImpl();
		CardPresentation cardPresentation= new CardPresentation(cardService);
		if(type==1) {
			
//			System.out.println("Enter card number");
//	        Card card = cardPresentation.isValidCard(scanner.nextInt());
//	        System.out.println("cardNo:" + card.getCardNo() + " \n balance:" + card.getBalance());
//	        StationPresentation stationPresentation = new StationPresentation();
//
//	        // new sub-menu after showing card details
//	        boolean keepRunning = true;
//	        while (keepRunning) {
//	            System.out.println("\n--- Station Options ---");
//	            System.out.println("1. Start Journey");
//	            System.out.println("2. View All Stations");
//	            System.out.println("3. Get Station By Name");
//	            System.out.println("4. Add New Station");
//	            System.out.println("5. Back to Main Menu");
//	            System.out.print("Enter choice: ");
//	            int subChoice = scanner.nextInt();
//	            scanner.nextLine(); // consume newline
//
//	            switch (subChoice) {
//	                case 1:
//	                    if (card.getBalance() < 20) {
//	                        cardPresentation.rechargeCard(card);
//	                    } else {
//	                        FarePresentation farePresentation = new FarePresentation();
//	                        farePresentation.fare(card);
//	                    }
//	                    break;
//
//	                case 2:
////	                    StationPresentation stationPresentation = new StationPresentation();
//	                    stationPresentation.viewAllStations();
//	                    break;
//
//	                case 3:
//	                    System.out.print("Enter station name: ");
//	                    String name = scanner.nextLine();
//	                    StationPresentation sp = new StationPresentation();
//	                    sp.viewStationByName(name);
//	                    break;
//	                    
//	                case 4:
//	                    System.out.print("Enter new station name: ");
////	                    StationPresentation stationPresentation = new StationPresentation();
//	                    String newName = scanner.nextLine();
//	                    stationPresentation.addStation(newName);
//	                    break;
//
//	                  
//	                case 5:
//	                    keepRunning = false; // back to main menu
//	                    break;
//
//	                default:
//	                    System.out.println("Invalid choice, try again.");
//	            }
//	        }
			
			System.out.println("Enter card number");
			Card card=cardPresentation.isValidCard(scanner.nextInt()) ;
			System.out.println("cardNo:"+ card.getCardNo()+" \n balance:"+card.getBalance());
			
			StationPresentation stationPresentation = new StationPresentation();
			stationPresentation.showStationMenu(card);
				
				//ask for journey
				if(card.getBalance()<20) {
					cardPresentation.rechargeCard(card);
				}
				else {
					FarePresentation farePresentation= new FarePresentation();
					farePresentation.fare(card);
				}
//				
				
			
		}
		else if(type==2) {
			System.out.println("lets create a new card");
			Card card=cardPresentation.createCard();
			//System.out.println("cardNo:"+ card.getCardNo()+" \n balance:"+card.getBalance());
			System.out.println(card);
		}
		else {
			System.out.println("Exiting..");
			System.exit(0);
			
		}
	}
	

}
