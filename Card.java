import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

class Card {
    private ArrayList<Integer> packOfCards = new ArrayList<Integer>();

    public ArrayList<Integer> getPackOfCards() throws IOException {
        return this.packOfCards;
    }

    public void setPackOfCards(String fileName, int playerCount) throws NumberFormatException, IOException {

        ArrayList<Integer> packOfCards = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Boolean flag = true;
        int count = 0;
        int lineCount = 0;

        while (br.readLine() != null) {
            lineCount++;
        }

        while (flag == true && lineCount == (8 * playerCount) && count < lineCount) {

            try {
                Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                flag = false;
                break;
            }

            if (Integer.parseInt(br.readLine()) < 0 || Integer.parseInt(br.readLine()) != 0) {
                flag = false;
                break;
            }

            packOfCards.add(Integer.parseInt(br.readLine()));
            count++;
        }

        if (flag == true) {
            this.packOfCards = packOfCards;
        } else {
            packOfCards.clear();
            System.out.println("Invalid file");
        }

    }

}
