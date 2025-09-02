package metro.presentation;

import java.sql.SQLException;
import java.util.Scanner;

import metro.entity.Card;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;
import metro.service.CardService;
import metro.service.CardServiceImpl;

public class CardPresentation {

    private CardService cardService;
    Scanner scanner = new Scanner(System.in);

    public CardPresentation(CardService cardService2) {
        this.cardService = cardService2;
    }

    public Card isValidCard(int cardNo) throws CardNotFoundException, DatabaseConnectionException,Exception {
        return cardService.getCardById(cardNo);
    }
    
    public Card createCard()  throws DatabaseConnectionException, InvalidAmountException, SQLException  {
    	System.out.println("Enter name:");
    	String name= scanner.next();
    	System.out.println("Enter Amount (>100): ");
    	int amount= scanner.nextInt();
    	return cardService.createCard(name, amount);
    	//System.out.println();
   
    }
    
    public void rechargeCard(Card card) throws CardNotFoundException, DatabaseConnectionException, Exception {
    	System.out.println("Press 1 ro recharge card\nPress 2 to exit");
    	int choice=scanner.nextInt();
    	UserPresentation user= new UserPresentation();
    	if(choice==1) {
    		System.out.println("Enter Amount: ");
    		int amount = scanner.nextInt();
    		cardService.rechargeCard(card, amount);
    	}
    	else if(choice==2) {
    		user.askUser();
    	}
    	else {
    		System.out.println("Enter valid choice");
    		this.rechargeCard(card);
    	}
    }
}
