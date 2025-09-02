package metro.presentation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import metro.entity.Card;
import metro.entity.SwipeRecord;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.service.CardService;
//import metro.service.SwipeRecordServiceImpl;

public class FarePresentation {
	
	public void fare(Card card) throws DatabaseConnectionException, CardNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter start station id: ");
        int startId = scanner.nextInt();
        System.out.print("Enter end station id: ");
        int endId = scanner.nextInt();
        int fare= (endId-startId)*5;
        SwipeRecord record = new SwipeRecord(
                0, card.getCardNo(), fare, startId, endId,
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now().plusMinutes(15)),
                java.sql.Date.valueOf(LocalDate.now())
        );
        
//        SwipeRecordServiceImpl swipeRecordService= new SwipeRecordServiceImpl();
//        swipeRecordService.addSwipeRecord(record);
//        CardService cardService = new CardService();
//        cardService.alterBalance(record);
        
        System.out.println(record.getStart()+" to " +record.getEnd() +"\nAmount Deducted: "+record.getFareDeducted()+"\nRemaining Balance"+(card.getBalance()-record.getFareDeducted()));
	}

}
