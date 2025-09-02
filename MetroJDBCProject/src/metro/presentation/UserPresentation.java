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





