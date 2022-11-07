import java.util.ArrayList;

class CardDeck{
    private int id;
    private ArrayList<Integer> deckOfCards = new ArrayList<Integer>();
    
    public CardDeck(ArrayList<Integer> pack, int id) {
        this.deckOfCards = pack;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
  
    public ArrayList<Integer> getDeckOfCards() {
        return deckOfCards;
    }
    /**
     * @param deckOfCards
     */
    public void setDeckOfCards(ArrayList<Integer> deckOfCards) {
        this.deckOfCards = deckOfCards;
    } 

    public void takeMine(ArrayList<Integer> pack, int playerCount){

        int posTracker = this.getId() - 1 + 4*playerCount;
        for (int i = 0; i< 4; i++){
            deckOfCards.add(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }
    }
}