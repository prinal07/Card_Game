import java.util.ArrayList;

class CardDeck{
    private int id;
    private ArrayList<Integer> deckOfCards = new ArrayList<Integer>();
    static ArrayList<CardDeck> deckList = new ArrayList<>();

    public CardDeck(int id, ArrayList<Integer> pack) {
        this.deckOfCards = pack;
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
        return deckOfCards.get(deckOfCards.get(0));
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

        System.out.println("ID:" + this.getId());
        System.out.println("Player count:" + playerCount);
        int posTracker = this.getId() - 1 + (4*playerCount);
        System.out.println("Position:" + posTracker);

        for (int i = 0; i< 4; i++){
            deckOfCards.add(pack.get(posTracker));
            System.out.println("Value at pos:" + pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }
    }
}