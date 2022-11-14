import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Player {
    private int id;
    private String logFileName = "player" + id + "output.txt";
    public File outputFile;
    private ArrayList<Integer> cardHand = new ArrayList<>();
    // private CardDeck myDeck;
    private int playerCount;

    public Player(int id, int playerCount) {
        this.id = id;
        this.playerCount = playerCount;
        createFile();
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
     * @throws IOException
     */
    public void takeMine(ArrayList<Integer> pack) throws IOException {
        int posTracker = this.getId() - 1;
        for (int i = 0; i < 4; i++) {
            this.cardHand.add(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }
        System.out.println("player " + this.id + " intial hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));
        FileWriter myWriter = new FileWriter(outputFile);
        myWriter.write("player " + this.id + " intial hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));
        myWriter.close();
    }

    public void checkHand() throws IOException {
        Boolean flag = true;
        for (int y = 1; y < cardHand.size() - 1; y++) {
            if (cardHand.get(y) != cardHand.get(y - 1)) {
                flag = false;
                break;
            }
        }
        if (flag == true) {
            FileWriter myWriter = new FileWriter(outputFile);
            myWriter.write("player " + this.id + " wins");
            myWriter.close();
        }
    }

    public void createFile() {
        try {
            this.outputFile = new File("player" + this.id + "output.txt");
            if (outputFile.createNewFile()) {
                //System.out.println("File created: " + outputFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void takeCard() throws IOException {
        System.out.print("Player" + id + " took " + CardDeck.deckList.get(id-1).getTopCard() + " from deck " + CardDeck.deckList.get(id-1).getId());
        cardHand.add(CardDeck.deckList.get(id-1).getTopCard());
        FileWriter myWriter = new FileWriter(this.outputFile);
        myWriter.write("player " + this.id + " current hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3)  + cardHand.get(4));
        myWriter.close();
    }

    public void discardCard() throws IOException {
        for (int i = 0; i < cardHand.size(); i++) {
            if (cardHand.get(i) != id) {
                System.out.print("Player" + id + " discarded " + cardHand.get(i));
                cardHand.remove(i);
                break;
            }
            else{
                FileWriter myWriter2 = new FileWriter(outputFile);
                myWriter2.write("player " + this.id + " wins");
                myWriter2.close();
            }
        }
        System.out.println("player " + this.id + " current hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));
        FileWriter myWriter = new FileWriter(this.outputFile);
        myWriter.write("player " + this.id + " current hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));
        myWriter.close();
    }
}
