package testing;

import java.util.ArrayList;

public interface CardDeckInterface {

    public int getId();
    public void setId(int id);
    public int popCard();
    public int getTopCard();
    public void addToDeck(int discardedCard);
    public ArrayList<Integer> getDeckOfCards();
    public void setDeckOfCards(ArrayList<Integer> deckOfCards);
    public void takeMineDeck(ArrayList<Integer> pack, int playerCount);
}
