import java.io.BufferedWriter;
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

        writeToLogFile(outputFile, "player " + this.id + " intial hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));
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
            writeToLogFile(outputFile, "player " + this.id + " wins");
        }
    }

    public void createFile() {
        try {
            this.outputFile = new File("player" + this.id + "output.txt");
            if (outputFile.createNewFile()) {
                // System.out.println("File created: " + outputFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void takeCard() throws IOException {
        int localTopCard = CardDeck.deckList.get(id - 1).getTopCard();
        cardHand.add(localTopCard);
        writeToLogFile(outputFile, "player " + this.id + " draws a " + localTopCard + " from deck " + this.id);

       
    }

    public void discardCard() throws IOException {
        int deckId;
        if(this.id == playerCount){
            deckId = 1;
        }
        else{
            deckId = this.id + 1;
        }

        for (int i = 0; i < cardHand.size(); i++) {
            if (cardHand.get(i) != id) {
                System.out.print("Player" + id + " discarded " + cardHand.get(i));
                int discardedCard = cardHand.get(i);
                writeToLogFile(outputFile,
                        "player " + this.id + " discards a " + discardedCard + " to deck " + (deckId));
                cardHand.remove(i);
                CardDeck.deckList.get(deckId).addToDeck(discardedCard);
                break;
            } else {
                checkHand();
            }
        }
        System.out.println("player " + this.id + " current hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));

        writeToLogFile(outputFile,
                "player " + this.id + " current hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                        + cardHand.get(2) + " " + cardHand.get(3));
    }

    public void writeToLogFile(File fileName, String data) throws IOException {
        BufferedWriter br;
        FileWriter fr = new FileWriter(fileName, true);
        br = new BufferedWriter(fr);
        br.write(data);
        br.newLine();
        br.close();
        fr.close();
    }

}
