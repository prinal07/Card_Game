import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;

class CardGame {
    public static void setup() throws IOException {
        int playerCount;
        String fileName;
        Scanner scanner = new Scanner(System.in);
        Card card = new Card();

        while (card.getPackOfCards().isEmpty() == true) {
            
            System.out.println("Please enter the number of players:");
            playerCount = scanner.nextInt();

            System.out.println("Please enter location of pack to load:");
            fileName = scanner.next();

            card.setPackOfCards(fileName, playerCount);
        }
    }

    public static void main(String[] args) throws IOException {
        setup();
    }
}
