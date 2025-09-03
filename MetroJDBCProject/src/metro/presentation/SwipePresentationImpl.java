package metro.presentation;

import java.util.List;
import java.util.Scanner;

import metro.entity.SwipeRecord;
import metro.exceptions.DatabaseConnectionException;
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
}
