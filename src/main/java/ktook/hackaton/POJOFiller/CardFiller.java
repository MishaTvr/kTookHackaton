package ktook.hackaton.POJOFiller;

import ktook.hackaton.rest.entities.Card;

import java.util.ArrayList;
import java.util.List;

public class CardFiller {
    private static List<Card> cards=null;

    public static void initCards(){
        long[] numbers = {4012888888881881L,4222222222222L,5555555555554444L,5105105105105100L};
        cards = new ArrayList<>();
        for(int i = 0; i<4 ; i++){
            Card card = new Card();
            card.setId(numbers[i]);
            card.setHolder(UserFiller.getOwnersNames().toArray()[i].toString());
            card.setMoney(10000);
            card.setDueDate("07/17");
            card.setCvv(322);
            cards.add(card);
        }
    }

    public static List<Card> getCards(){
        initCards();
        return cards;
    }
}
