package metro.presentation;

import java.util.Scanner;

import metro.entity.Card;
import metro.entity.SwipeRecord;
import metro.service.CardService;
import metro.service.CardServiceImpl;
import metro.service.SwipeService;
import metro.service.SwipeServiceImpl;

public class FarePresentationImpl implements FarePresentation {
	
    private static final double MIN_BALANCE = 20.0; // required to allow Swipe In
	
	public void fare(Card card) throws Exception {
	    SwipeService swipeService = new SwipeServiceImpl();
	    CardService cardService = new CardServiceImpl();
	    CardPresentationImpl cardPresentation = new CardPresentationImpl(cardService); // for recharge
	    
	    boolean keepRunning = true;
	    StationPresentation station = new StationPresentationImpl();
        Scanner scanner = new Scanner(System.in);

	    while (keepRunning) {
	        SwipeRecord openJourney = swipeService.getOpenJourneyByCard(card.getCardNo());
	       
	        System.out.println("\n--- Fare Menu ---");
	        if (openJourney == null) {
	            System.out.println("1. Swipe In");
	            System.out.println("2. Back to Station Menu");
	        } else {
	            System.out.println("1. Swipe Out");
	            System.out.println("2. Back to Station Menu");
	            System.out.println("Currently Swiped-In from Station ID: " 
	                               + openJourney.getStart() +
	                               " at " + openJourney.getStartTime());
	        }
	        System.out.print("Enter choice: ");
	        int choice = scanner.nextInt();

	        if (openJourney == null) {
	            // only Swipe In
	            switch (choice) {
	                case 1:
	                	// Check if card has enough balance
	                	double balance = cardService.getCardById(card.getCardNo()).getBalance();
	                	if (balance < MIN_BALANCE) {
	                		System.out.println("Insufficient balance (Current: Rs " + balance + "). Minimum Rs " + MIN_BALANCE + " required.");
	                		// Redirect to recharge
	                		cardPresentation.rechargeCard(card);
	                		break;
	                	}
	                	
	                    station.viewAllStations();
	                    System.out.print("Enter Station ID to swipe in: ");
	                    int src = scanner.nextInt();
	                    try {
	                        swipeService.swipeIn(card.getCardNo(), src);
	                        System.out.println("Swipe-In successful at Station " + src);
	                    } catch (Exception e) {
	                        System.out.println("Error: " + e.getMessage());
	                    }
	                    break;
	                case 2:
	                    keepRunning = false;
	                    break;
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        } else {
	            // only Swipe Out
	            switch (choice) {
	                case 1:
	                    station.viewAllStations();
	                    System.out.print("Enter Station ID to swipe out: ");
	                    int dest = scanner.nextInt();
	                    try {
	                        // Penalty check
	                        double fare = (openJourney.getStart() == dest) ? 10.0 : calculateFare(openJourney.getStart(), dest);
	                        
	                        // Deduct balance
	                        cardService.deductFare(card, fare);

	                        // Update record
	                        swipeService.swipeOut(card.getCardNo(), dest, fare);
	                        cardService.deductFare(card, fare);
	                        
	                        double updatedBalance = cardService.getCardById(card.getCardNo()).getBalance(); // fetch updated balance
	                        System.out.println("Swipe-Out successful at Station " + dest + 
	                                           " | Fare Deducted: Rs " + fare +
	                                           " | Current Balance: Rs " + updatedBalance);
	                    } catch (Exception e) {
	                        System.out.println("Error: " + e.getMessage());
	                    }
	                    break;
	                case 2:
	                    keepRunning = false;
	                    break;
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	}

	// Replace later with distance-based calculation
	private double calculateFare(int start, int end) {
	    return Math.abs(start-end)*5; // flat Rs 20 for now
	}
}
