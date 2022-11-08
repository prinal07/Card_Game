import java.io.File;
import java.util.ArrayList;

class Player{
    private int id;
    private String logFileName = "player" + id + "output.txt";
    private File outputFile = new File(logFileName);
    private ArrayList<Integer> cardHand = new ArrayList<>();
    // private CardDeck myDeck;
    private int playerCount;

    public Player(int id, int playerCount) {
        this.id = id;
        this.playerCount = playerCount;
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }
   
    
    /** 
     * @return String
     */
    public String getLogFileName() {
        return logFileName;
    }
    
    /** 
     * @param logFileName
     */
    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }
    
    /** 
     * @return File
     */
    public File getOutputFile() {
        return outputFile;
    }
    
    /** 
     * @param outputFile
     */
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    
    /** 
     * @param pack
     */
    public void takeMine(ArrayList<Integer> pack){
        int posTracker = this.getId() - 1;
        for (int i = 0; i< 4; i++){
            this.cardHand.add(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }

    }



}
