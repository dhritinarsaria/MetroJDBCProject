package metro.presentation;

import java.util.Scanner;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.service.*;

public class UserPresentationImpl implements UserPresentation {
	Scanner scanner= new Scanner(System.in);
	
	
	public void askUser() throws CardNotFoundException, DatabaseConnectionException, Exception {
		System.out.println("Press 1 if you have valid card \nPress 2 to create a new card\nPress 3 to exit Sapient Metro");
        int userType = scanner.nextInt();
        userType(userType);
	}

	public void getDetails(Card card, int type) throws CardNotFoundException, DatabaseConnectionException, Exception {
	    boolean keepRunning = true;
	    while (keepRunning) {
	        System.out.println("Press 1 to get User Details\nPress 2 to enter the Metro Station\nPress 4 to login as another user\nPress any other key to exit");
	        int ch = scanner.nextInt();

	        if (ch == 1) {
	            this.getUserDetails(type, card.getCardNo(), card);
	        } 
	        else if (ch == 2) {
	            StationPresentationImpl stationPresentation = new StationPresentationImpl();
	            stationPresentation.showStationMenu(card);   
	        } 
	        else if(card.getBalance()<20) {
	        	CardService cardService = new CardServiceImpl();
	             CardPresentation cardPresentation = new CardPresentationImpl(cardService);
	             cardPresentation.rechargeCard(card);
	        	
	        }
	        else  if(ch==3){
	            this.userType(type);  // go back to card options
	            keepRunning = false;
	        } 
	       
	        else {
	        	System.out.println("Exiting!");
	            System.exit(0);
	        }
	    }
	}

	public void userType(int type) throws CardNotFoundException, DatabaseConnectionException, Exception {
		CardService cardService = new CardServiceImpl();
		CardPresentationImpl cardPresentation= new CardPresentationImpl(cardService);
		if(type==1) {

			System.out.println("Enter card number");
			Card card=cardPresentation.isValidCard(scanner.nextInt()) ;
			System.out.println("cardNo:"+ card.getCardNo());
			System.out.println("Existing balance:"+card.getBalance());
			
			this.getDetails(card, type);
			
		}
		else if(type==2) {
			//System.out.println("lets create a new card");
			Card card=cardPresentation.createCard();
			//System.out.println("cardNo:"+ card.getCardNo()+" \n balance:"+card.getBalance());
			System.out.println(card);
		}
		else {
			System.out.println("Exiting..");
			System.exit(0);
			
		}
	}
	
	public void getUserDetails(int type, int cardNo, Card card) throws CardNotFoundException, Exception{
		System.out.println("Press 1 to get Recharge History\nPress 2 to get Journey History\nPress 3 to go back");
		int ch=scanner.nextInt();
		if(ch==1) {
			TransactionPresentation transactionPresentation= new TransactionPresentationImpl();
			transactionPresentation.showTransactions(cardNo);
			this.getUserDetails(type, cardNo, card);			
		}
		else if(ch==2) {
			SwipePresentation swipePresentation= new SwipePresentationImpl();
			swipePresentation.showSwipeHistory(cardNo);
			this.getUserDetails(type, cardNo,card);
		}
		else {
			this.getDetails( card, type);
		}
	}

}





