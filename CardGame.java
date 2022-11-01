import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;

class CardGame {

    static int playerCount;
    static String fileName;
    static Scanner scanner = new Scanner(System.in);

    public static void inputData(){    
        System.out.println("Please enter the number of players:");
        playerCount = scanner.nextInt();

        System.out.println("Please enter location of pack to load:");
        fileName = scanner.next();
    }

    public static void setup() throws IOException {
        Card card = new Card();

        while (card.getPackOfCards().isEmpty() == true) {
            inputData();
            card.setPackOfCards(fileName, playerCount);
        }
    }

    public static void main(String[] args) throws IOException {
        setup();
    }
}
