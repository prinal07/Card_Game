import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.naming.ldap.HasControls;

import org.junit.Before;

class CardGame {

    static Card card;
    static int playerCount;
    static String fileName;
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Integer> pack;
    static ArrayList<Player> playerList = new ArrayList<>();

    public static void inputData(){    
        System.out.println("Please enter the number of players:");
        playerCount = scanner.nextInt();

        System.out.println("Please enter location of pack to load:");
        fileName = scanner.next();
    }
    
    public static void dealing() throws IOException{

        pack = card.getPackOfCards();
        for (int i=1; i<playerCount + 1; i++){
            Player player = new Player(i, playerCount);
            playerList.add(player);
            player.takeMine(pack);

            CardDeck cardDeck = new CardDeck(pack, i);
            cardDeck.takeMine(pack, i);
        }
    }

    public static void setup() throws IOException {
        card = new Card();

        while (card.getPackOfCards().isEmpty() == true) {
            inputData();
            card.setPackOfCards(fileName, playerCount);
        }
    }

    public static void main(String[] args) throws IOException {
        setup();
        dealing();
    }
}
