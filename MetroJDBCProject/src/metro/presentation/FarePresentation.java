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
//import metro.service.SwipeRecordServiceImpl;

public class FarePresentation {
	
	CardService cardService= new CardServiceImpl();
	CardPresentation cardPresentation= new CardPresentation(cardService);
	
	public void fare(Card card) throws Exception {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter start station id: ");
        int startId = scanner.nextInt();
        StationPresentation stationPresentation= new StationPresentation();
        if(!stationPresentation.isValidStationId(startId)) {
        	System.out.println("Invalid station Id. Please try again");
        	cardPresentation.startJourney(card);
        }
        
        System.out.print("Enter end station id: ");
        
        
        int endId = scanner.nextInt();
        
        if(!stationPresentation.isValidStationId(endId)) {
        	System.out.println("Invalid station Id. Please try again");
        	CardService cardService= new CardServiceImpl();
        	CardPresentation cardPresentation= new CardPresentation(cardService);
        	cardPresentation.startJourney(card);
        }
        
        FareService fareService= new FareServiceImpl();
       double fare=  fareService.calculateFare(startId, endId);
       
       //add record by vaibhav returns record
       //cardService.deductFare(record);
   
        
        //System.out.println(record.getStart()+" to " +record.getEnd() +"\nAmount Deducted: "+record.getFareDeducted()+"\nRemaining Balance"+(card.getBalance()-record.getFareDeducted()));
	}

}
