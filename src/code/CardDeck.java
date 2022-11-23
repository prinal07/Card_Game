package src.code;
import java.util.ArrayList;

import testing.CardDeckInterface;

public class CardDeck implements CardDeckInterface{
    private int id;
    private ArrayList<Integer> deckOfCards = new ArrayList<Integer>();
    static ArrayList<CardDeck> deckList = new ArrayList<>();

    public CardDeck(int id) {
        this.id = id;
    }
    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }
    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
  
    public int popCard(){
        return deckOfCards.get(deckOfCards.size()-1);
    }
    
    public synchronized int getTopCard(){
        int topCard = deckOfCards.get(0);
        deckOfCards.remove(0);
        return topCard;
    }
    public synchronized void addToDeck(int discardedCard){
        this.deckOfCards.add(discardedCard);
        //System.out.println("Deck "+this.getId()+" "+this.deckOfCards.toString());

    }

    /** 
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getDeckOfCards() {
        return this.deckOfCards;
    }
    /**
     * @param deckOfCards
     */
    public void setDeckOfCards(ArrayList<Integer> deckOfCards) {
        this.deckOfCards = deckOfCards;
    } 

    
    /** 
     * @param pack
     * @param playerCount
     */
    public void takeMineDeck(ArrayList<Integer> pack, int playerCount){
        int posTracker = this.getId() - 1 + (4*playerCount);
        for (int i = 0; i< 4; i++){
            this.deckOfCards.add(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }
    }
}