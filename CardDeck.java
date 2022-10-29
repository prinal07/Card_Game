import java.util.ArrayList;

class CardDeck{
    private int Id;
    private int playerCount;
    private ArrayList<Integer> deckOfCards = new ArrayList<Integer>();
    
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public int getPlayerCount() {
        return playerCount;
    }
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
    public ArrayList<Integer> getDeckOfCards() {
        return deckOfCards;
    }
    public void setDeckOfCards(ArrayList<Integer> deckOfCards) {
        this.deckOfCards = deckOfCards;
    }
}