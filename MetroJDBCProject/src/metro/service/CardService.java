package metro.service;

import metro.entity.Card;

public interface CardService {
    Card createCard(int userId, double initialBalance);  // create new card for a user
    Card getCardById(int cardNo);                        // fetch card
    double checkBalance(int cardNo);                     // check balance
    void rechargeCard(int cardNo, double amount);        // add balance
    void deductFare(int cardNo, double fare);            // deduct during swipeOut
}