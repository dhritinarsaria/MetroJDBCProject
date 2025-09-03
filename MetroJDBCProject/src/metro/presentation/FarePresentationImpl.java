package metro.presentation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import metro.entity.Card;
import metro.entity.SwipeRecord;
import metro.service.CardService;
import metro.service.CardServiceImpl;
import metro.service.FareService;
import metro.service.FareServiceImpl;
import metro.service.SwipeService;
import metro.service.SwipeServiceImpl;
//import metro.service.SwipeRecordServiceImpl;

public class FarePresentationImpl implements FarePresentation {
	
	CardService cardService= new CardServiceImpl();
	CardPresentationImpl cardPresentation= new CardPresentationImpl(cardService);
	
	public void fare(Card card) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter start station id: ");
        int startId = scanner.nextInt();
        StationPresentationImpl stationPresentation= new StationPresentationImpl();
        if(!stationPresentation.isValidStationId(startId)) {
        	
        	cardPresentation.startJourney(card);
        }
        
        System.out.print("Enter end station id: ");
        
        
        int endId = scanner.nextInt();
        
        if(!stationPresentation.isValidStationId(endId)) {
        	CardService cardService= new CardServiceImpl();
        	CardPresentationImpl cardPresentation= new CardPresentationImpl(cardService);
        	cardPresentation.startJourney(card);
        }
        
        FareService fareService= new FareServiceImpl();
       double fare=  fareService.calculateFare(startId, endId);
       
       //add record by vaibhav returns record
       SwipeService swipe= new SwipeServiceImpl();
       
       SwipeRecord swipeRecord=  new SwipeRecord();
       swipeRecord.setCardNo(card.getCardNo());
       swipeRecord.setDate(null);
       swipeRecord.setEnd(endId);
       swipeRecord.setEndTime(null);
       swipeRecord.setFareDeducted(fare);
       swipeRecord.setStart(startId);
       swipeRecord.setStartTime(null);
       swipe.addSwipeRecord(swipeRecord);
       
       cardService.deductFare(card, fare);
   
        
        System.out.println(swipeRecord.getStart()+" to " +swipeRecord.getEnd() +"\nAmount Deducted: "+swipeRecord.getFareDeducted()+"\nRemaining Balance: "+(card.getBalance()));
       stationPresentation.showStationMenu(card);
	}

}
