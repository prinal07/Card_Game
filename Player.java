import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Player implements Runnable {
    private int id;
    private String logFileName = "player" + id + "output.txt";
    public File outputFile;
    private ArrayList<Integer> cardHand = new ArrayList<>();
    private int playerCount;
    private boolean winner = false;

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

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

        writeToLogFile(outputFile,
                "player " + this.id + " initial hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                        + cardHand.get(2) + " " + cardHand.get(3));
    }

    public synchronized void checkHand() throws IOException {
        Boolean flag = true;
        for (int y = 1; y <= cardHand.size()-1; y++) {
            if (cardHand.get(y) != cardHand.get(y - 1)) {
                flag = false;
            }
        }
        if (flag == true) {
            CardGame.winningBool = true;
            this.winner = true;
            CardGame.winnerId = this.id;

           
        }
    }

    public void winner() throws IOException {

        writeToLogFile(outputFile, "player " + this.id + " wins");
        writeToLogFile(outputFile, "player " + this.id + " exits");
        writeToLogFile(outputFile,
        "player " + this.id + " final hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));
        System.out.println("player " + this.id + " wins");;
    }

    public void loser() throws IOException{
        writeToLogFile(outputFile, "player " + CardGame.winnerId + " has informed player " + this.id + " that player " + CardGame.winnerId + " has won");
        writeToLogFile(outputFile, "player " + this.id + " exits");
        writeToLogFile(outputFile,
        "player " + this.id + "  hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                + cardHand.get(2) + " " + cardHand.get(3));

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

    public synchronized void takeCard() throws IOException {
        int localTopCard = CardDeck.deckList.get(id - 1).getTopCard();
        cardHand.add(localTopCard);
        writeToLogFile(outputFile, "player " + this.id + " draws a " + localTopCard + " from deck " + this.id);
    }

    public synchronized void discardCard() throws IOException {
        int deckId;
        if (this.id == playerCount) {
            deckId = 1;
        } else {
            deckId = this.id + 1;
        }

        for (int i = 0; i < cardHand.size(); i++) {
            if (cardHand.get(i) != id) {
                //System.out.print("Player" + id + " discarded " + cardHand.get(i));
                int discardedCard = cardHand.get(i);
                writeToLogFile(outputFile,
                        "player " + this.id + " discards a " + discardedCard + " to deck " + (deckId));
                cardHand.remove(i);
                CardDeck.deckList.get(deckId - 1).addToDeck(discardedCard);
                break;
            }
        }
        checkHand();
//        System.out.println("player " + this.id + " current hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
//                + cardHand.get(2) + " " + cardHand.get(3));
//        System.out.println("a Deck " + deckId + CardDeck.deckList.get(deckId - 1).getDeckOfCards().toString());

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


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                takeCard();
            } catch (IOException e) {
                System.out.println(
                        Thread.currentThread().getName()
                                + "is throwing an excepting while taking a card");
                e.printStackTrace();
            }
            try {
                discardCard();
            } catch (IOException e) {
                System.out.println(Thread.currentThread().getName()
                        + "is throwing an excepting while discarding a card");
                ;
                e.printStackTrace();
            }

            try {
                checkHand();
            } catch (IOException e) {
                System.out.println(
                        Thread.currentThread().getName()
                                + "is throwing an excepting while checking hand");

            }
        }
    }
}
