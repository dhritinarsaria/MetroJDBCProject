package metro.service;

import metro.entity.Card;
import metro.entity.SwipeRecord;
import metro.exceptions.CardNotFoundException;
import metro.exceptions.DatabaseConnectionException;
import metro.exceptions.InvalidAmountException;
import metro.exceptions.InsufficientBalanceException;
import metro.persistence.CardDao;
import metro.persistence.CardDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class CardServiceImpl implements CardService{

    private CardDao cardDao;

    public CardServiceImpl() {
        this.cardDao = new CardDaoImpl();
    }
    
    public boolean hasEnoughBalance(int cardNo) throws CardNotFoundException, Exception {
    	Card card = cardDao.getCardById(cardNo);
    	 if (card.getBalance() < 20) {
             return false;
         }
    	 return true;
    }

    // Business validation: check if card exists and has minimum balance
    public Card getCardById(int cardNo) throws CardNotFoundException, Exception {
        Card card = cardDao.getCardById(cardNo);
       
        return card;
    }

    public Card createCard(String name, double amount)
            throws DatabaseConnectionException, InvalidAmountException, SQLException {
    	
    	 if (amount < 100) {
             throw new InvalidAmountException("Minimum balance for creating a card is 100");
         }

        return cardDao.createCard(name, amount);
    }

    @Override
    public void rechargeCard(Card card, double amount) throws DatabaseConnectionException, SQLException, CardNotFoundException {
        double newBalance = card.getBalance() + amount;
        card.setBalance(newBalance);
        cardDao.updateBalance(card.getCardNo(), newBalance);
    }

    @Override
    public void deductFare(Card card, double fare)
            throws DatabaseConnectionException, CardNotFoundException, SQLException, Exception {
    	
    	//Card card= cardDao.getCardById(card.getCardNo());
    	this.rechargeCard(card, -1*fare);
       
    }

    public List<Card> getAllCards() throws DatabaseConnectionException, SQLException {
        return cardDao.getAllCards();
    }

	@Override
	public boolean isValidCard(int cardNo) throws CardNotFoundException, Exception {
		// TODO Auto-generated method stub
		if(this.getCardById(cardNo)!=null) {
			return true;
		}
		return false;
	}





   
}
