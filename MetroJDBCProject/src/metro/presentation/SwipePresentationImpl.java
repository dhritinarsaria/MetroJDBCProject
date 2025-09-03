package metro.presentation;
import metro.service.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import metro.entity.Card;
import metro.entity.SwipeRecord;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;
import metro.service.SwipeService;
import metro.service.SwipeServiceImpl;

public class SwipePresentationImpl implements SwipePresentation {
    
    private SwipeService swipeService = new SwipeServiceImpl();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void showSwipeHistory(int cardNo) throws DatabaseConnectionException {
        List<SwipeRecord> records = swipeService.getSwipeHistoryByCard(cardNo);

        if (records == null || records.isEmpty()) {
            System.out.println("No swipe records found for Card No: " + cardNo);
        } else {
            System.out.println("Swipe History for Card No: " + cardNo);
            for (SwipeRecord record : records) {
                System.out.println("Date: " + record.getDate() +
                                   ", Start Station: " + record.getStart() +
                                   ", End Station: " + record.getEnd() +
                                   ", Fare Deducted: " + record.getFareDeducted() +
                                   ", Start Time: " + record.getStartTime() +
                                   ", End Time: " + record.getEndTime());
            }
        }
    }
    
    @Override
    public void swipeIn(int cardNo) throws DatabaseConnectionException, SQLException, CardNotFoundException {
        System.out.print("Enter source station id: ");
        int sourceId = scanner.nextInt();
        swipeService.swipeIn(cardNo, sourceId);
        System.out.println("Swipe-in successful at station " + sourceId);
    }

    @Override
    public void swipeOut(int cardNo) throws DatabaseConnectionException, SQLException, CardNotFoundException, InvalidAmountException {
        System.out.print("Enter destination station id: ");
        int destId = scanner.nextInt();

        try {
            // Step 1: Get open journey
            SwipeRecord openJourney = swipeService.getOpenJourneyByCard(cardNo);
            if (openJourney == null) {
                System.out.println("No open journey found! Please swipe in first.");
                return;
            }

            int startId = openJourney.getStart();

            // Step 2: Calculate fare
            FareService fareService = new FareServiceImpl();
            double fare;
            if (startId == destId) {
                System.out.println("Start and destination are same. Penalty ₹10 applied.");
                fare = 10.0;
            } else {
                fare = fareService.calculateFare(startId, destId);
            }

            // Step 3: Call service to swipe out
            swipeService.swipeOut(cardNo, destId, fare);

            // Step 4: Deduct fare from card balance
            CardService cardService = new CardServiceImpl();
            Card card = cardService.getCardById(cardNo);
            cardService.deductFare(card, fare);

            System.out.println("Swipe-out successful at station " + destId);
            System.out.println("Fare deducted: " + fare);
            System.out.println("Remaining balance: " + card.getBalance());

        } catch (Exception e) {
            System.out.println("Error during swipe-out: " + e.getMessage());
        }
    }

}
