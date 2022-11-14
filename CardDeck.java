import java.util.ArrayList;

class CardDeck{
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
    
    public int getTopCard(){
        int topCard = deckOfCards.get(0);
        deckOfCards.remove(0);
        return topCard;
    }
    public void addToDeck(int discardedCard){
        deckOfCards.add(discardedCard);
    }

    /** 
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getDeckOfCards() {
        return deckOfCards;
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
            //System.out.println(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }
        System.out.println("Deck "+this.getId()+" "+this.deckOfCards.toString());
    }
}