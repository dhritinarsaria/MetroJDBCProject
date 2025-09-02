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

    // Business validation: check if card exists and has minimum balance
    public Card getCardById(int cardNo) throws CardNotFoundException, Exception {
        Card card = cardDao.getCardById(cardNo);
        if (card.getBalance() < 20) {
            throw new InsufficientBalanceException("Minimum balance of 20 required.");
        }
        return card;
    }

    public Card createCard(String name, double amount)
            throws DatabaseConnectionException, InvalidAmountException, SQLException {
        return cardDao.createCard(name, amount);
    }

    @Override
    public void rechargeCard(Card card, double amount) throws DatabaseConnectionException, SQLException, CardNotFoundException {
        double newBalance = card.getBalance() + amount;
        card.setBalance(newBalance);
        cardDao.updateBalance(card.getCardNo(), newBalance);
    }

    @Override
    public void deductFare(SwipeRecord record)
            throws DatabaseConnectionException, CardNotFoundException, SQLException {
        cardDao.alterBalance(record.getCardNo(), record.getFareDeducted());
    }

    public List<Card> getAllCards() throws DatabaseConnectionException, SQLException {
        return cardDao.getAllCards();
    }



//	@Override
//	public double checkBalance(int cardNo) {
//		// TODO Auto-generated method stub
//		return 0;
//	}


//	@Override
//	public void deductFare(int cardNo, double fare) {
//		// TODO Auto-generated method stub
//		
//	}

   
}
