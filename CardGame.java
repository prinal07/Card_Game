import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class CardGame {

    static Card card;
    static int playerCount;
    static String fileName;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Integer> pack;
    static ArrayList<Player> playerList = new ArrayList<>();
    static ArrayList<Thread> threads = new ArrayList<>();
    static boolean winningBool = false;
    static int winnerId;
    static boolean gameRunning;

    public static void inputData() {
        System.out.println("Please enter the number of players:");
        playerCount = scanner.nextInt();
        assertTrue(playerCount > 0);

        System.out.println("Please enter location of pack to load:");
        fileName = scanner.next();
        assertNotNull(fileName);
    }

    /**
     * @throws IOException
     */
    public static void dealing() throws IOException {

        pack = card.getPackOfCards();

        for (int i = 1; i < playerCount + 1; i++) {
            Player player = new Player(i, playerCount);
            playerList.add(player);
            player.takeMine(pack);

            CardDeck cardDeck = new CardDeck(i);
            CardDeck.deckList.add(cardDeck);
            cardDeck.takeMineDeck(pack, playerCount);

            for (int j: cardDeck.getDeckOfCards()){
            System.out.println("DECK CARDS" + j);
            }
            System.out.println("DECK DONE");
            player.checkHand();

        }
    }

    /**
     * @throws IOException
     */
    public static void setup() throws IOException {
        card = new Card();

        while (card.getPackOfCards().isEmpty() == true) {
            inputData();
            card.setPackOfCards(fileName, playerCount);
        }
    }

    public static void playGame() throws IOException {
        for (int i = 0; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            Thread thread = new Thread(player);
            threads.add(thread);
            thread.start();
        }
        while(!winningBool){
            System.out.println("Running");
        }
        for (Thread t : threads){
            t.interrupt();
        }
        for (Player p : playerList) {
            if (p.isWinner()) {
                try {
                    p.winner();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    p.loser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        setup();
        dealing();
        playGame();
    }
}