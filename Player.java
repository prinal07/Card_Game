import java.io.File;
import java.util.ArrayList;

class Player{
    private int id;
    private String logFileName = "player" + id + "output.txt";
    private File outputFile = new File(logFileName);
    private int cardDeckId;
    private ArrayList<Integer> cardHand = new ArrayList<>();
    // private CardDeck myDeck;
    public int playerCount;

    public Player(int id, int playerCount) {
        this.id = id;
        // myDeck = new CardDeck();
    }

    public int getId() {
        return id;
    }
   
    public String getLogFileName() {
        return logFileName;
    }
    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
    public File getOutputFile() {
        return outputFile;
    }
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }
    // public int getCardDeckId() {
    //     return cardDeckId;
    // }
    // public void setCardDeckId(int cardDeckId) {
    //     this.cardDeckId = cardDeckId;
    // }


    public void takeMine(ArrayList<Integer> pack){

        int posTracker = this.getId() - 1;
        for (int i = 0; i< 4; i++){
            cardHand.add(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }
    }



}
