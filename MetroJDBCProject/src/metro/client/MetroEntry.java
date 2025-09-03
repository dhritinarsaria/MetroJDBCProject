package metro.client;

//import metro.presentation.CardPresentation;
//import metro.presentation.UserPresentation;
import metro.service.CardService;
import metro.service.CardServiceImpl;
import metro.exceptions.*;
import metro.exceptions.DatabaseConnectionException;
import metro.presentation.CardPresentationImpl;
import metro.presentation.UserPresentation;
import metro.presentation.UserPresentationImpl;

import java.util.Scanner;

public class MetroEntry {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Sapient Metro");
while(true) {
        try {
        	CardService cardService = new CardServiceImpl();
            CardPresentationImpl cardPresentation = new CardPresentationImpl(cardService);
        UserPresentation userPresentation= new UserPresentationImpl();
        userPresentation.askUser();

        } catch (DatabaseConnectionException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        } catch (CardNotFoundException e) {
            System.out.println("Card not found: " + e.getMessage());
        }
        catch(InsufficientBalanceException e) {
        	System.out.println(e.getMessage());
        	
        }
        catch(InvalidAmountException e) {
        	System.out.println(e.getMessage());
        }
        catch(StationNotFoundException e) {
        	System.out.println(e.getMessage());
        	
        }
       
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        
        //ClassNotFoundException
        
        
        
        
        
        
        }
    }
    }

