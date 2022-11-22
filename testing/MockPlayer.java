package testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MockPlayer extends Thread implements PlayerInterface {
    private int id;
    private String logFileName = "player" + id + "output.txt"; // this is not used...
    public File outputFile;
    public File deckOutputFile;
    private String deckFileName = "deck" + id;
    private ArrayList<Integer> cardHand = new ArrayList<>();
    private int playerCount;
    private boolean winner = false;

    @Override
    public boolean isWinner() {
        return winner;
    }

    @Override
    public void setWinner(boolean winner) {
        this.winner = winner;

    }

    public MockPlayer(int id, int playerCount) {
        this.id = id;
        this.playerCount = playerCount;
        createFile();
    }

    @Override
    public int getMyId() {
        return id;
    }

    @Override
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;

    }

    @Override
    public void takeMine(ArrayList<Integer> pack) throws IOException {
        int posTracker = this.getMyId() - 1;
        for (int i = 0; i < 4; i++) {
            this.cardHand.add(pack.get(posTracker));
            posTracker = posTracker + playerCount;
        }

        writeToLogFile(outputFile,
                "player " + this.id + " initial hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                        + cardHand.get(2) + " " + cardHand.get(3));
    }

    @Override
    public void checkHand() throws IOException {
        Boolean flag = true;
        for (int y = 1; y <= cardHand.size() - 1; y++) {
            if (cardHand.get(y) != cardHand.get(y - 1)) {
                flag = false;
            }
        }
        if (flag == true) {
            MockCardGame.winningBool = true;
            this.winner = true;
            MockCardGame.winnerId = this.id;

        }
    }

    @Override
    public void winner() throws IOException {

        writeToLogFile(outputFile, "player " + this.id + " wins");
        writeToLogFile(outputFile, "player " + this.id + " exits");
        writeToLogFile(outputFile,
                "player " + this.id + " final hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                        + cardHand.get(2) + " " + cardHand.get(3));

        ArrayList<Integer> tempDeck = MockCardDeck.deckList.get(id - 1).getDeckOfCards();
        writeToLogFile(deckOutputFile,
                "deck" + id + " contents: " + tempDeck.get(0) + " "
                        + tempDeck.get(1) + " " + tempDeck.get(2) + " " + tempDeck.get(3));
        System.out.println("player " + this.id + " wins");

    }

    @Override
    public void createFile() {
        try {
            this.outputFile = new File("TESTPlayer" + this.id + "output.txt");
            this.deckOutputFile = new File("TESTdeck" + this.id + "_output.txt");
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

    @Override
    public void takeCard() throws IOException {
        int localTopCard = MockCardDeck.deckList.get(id - 1).getTopCard();
        cardHand.add(localTopCard);
        writeToLogFile(outputFile, "player " + this.id + " draws a " + localTopCard + " from deck " + this.id);
    }

    @Override
    public void discardCard() throws IOException {
        int deckId;
        if (this.id == playerCount) {
            deckId = 1;
        } else {
            deckId = this.id + 1;
        }

        for (int i = 0; i < cardHand.size(); i++) {
            if (cardHand.get(i) != id) {
                // System.out.print("Player" + id + " discarded " + cardHand.get(i));
                int discardedCard = cardHand.get(i);
                writeToLogFile(outputFile,
                        "player " + this.id + " discards a " + discardedCard + " to deck " + (deckId));
                cardHand.remove(i);
                MockCardDeck.deckList.get(deckId - 1).addToDeck(discardedCard);
                break;
            }
        }
        checkHand();
        writeToLogFile(outputFile,
                "player " + this.id + " current hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                        + cardHand.get(2) + " " + cardHand.get(3));
    }

    @Override
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

    @Override
    public File getOutputFile() {
        return outputFile;
    }

    @Override
    public void loser() throws IOException {
        writeToLogFile(outputFile,
                "player " + MockCardGame.winnerId + " has informed player " + this.id + " that player "
                        + MockCardGame.winnerId + " has won");
        writeToLogFile(outputFile, "player " + this.id + " exits");
        writeToLogFile(outputFile,
                "player " + this.id + "  hand " + cardHand.get(0) + " " + cardHand.get(1) + " "
                        + cardHand.get(2) + " " + cardHand.get(3));

        ArrayList<Integer> tempDeck = MockCardDeck.deckList.get(id - 1).getDeckOfCards();
        writeToLogFile(deckOutputFile,
                "deck" + id + " contents: " + tempDeck.get(0) + " "
                        + tempDeck.get(1) + " " + tempDeck.get(2) + " " + tempDeck.get(3));
    }

}
