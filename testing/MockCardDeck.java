package testing;

import java.util.ArrayList;

public class MockCardDeck implements CardDeckInterface{
    private int id;
    private ArrayList<Integer> deckOfCards = new ArrayList<Integer>();
    static ArrayList<MockCardDeck> deckList = new ArrayList<>();

    public MockCardDeck(int id) {
        this.id = id;
    }
    
    /** 
     * @return int
     */
    @Override
    public int getId() {
        return id;
    }
    
    /** 
     * @param id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
  
    @Override
    public int popCard(){
        return deckOfCards.get(deckOfCards.size()-1);
    }
    
    @Override
    public int getTopCard(){
        int topCard = deckOfCards.get(0);
        deckOfCards.remove(0);
        return topCard;
    }
    
    @Override
    public void addToDeck(int discardedCard){
        this.deckOfCards.add(discardedCard);
    }

    /** 
     * @return ArrayList<Integer>
     */
    @Override
    public ArrayList<Integer> getDeckOfCards() {
        return this.deckOfCards;
    }
    /**
     * @param deckOfCards
     */
    @Override
    public void setDeckOfCards(ArrayList<Integer> deckOfCards) {
        this.deckOfCards = deckOfCards;
    } 

    
    /** 
     * @param pack
     * @param playerCount
     */
    @Override
    public void takeMineDeck(ArrayList<Integer> pack, int playerCount){
        int posTracker = this.getId() - 1 + (4*playerCount);
        for (int i = 0; i< 4; i++){
            this.deckOfCards.add(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }
    }

}
