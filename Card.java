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
    private ArrayList<Integer> packOfCards = new ArrayList<>();

    public ArrayList<Integer> getPackOfCards() throws IOException {
        return this.packOfCards;
    }

    public void setPackOfCards(String fileName, int playerCount) throws NumberFormatException, IOException {

        ArrayList<Integer> packOfCards = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Boolean flag = true;
        int lineCount = 0;

        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while (br.readLine() != null) {
            lineCount++;
        }

        while (flag == true && lineCount == (8 * playerCount) && sc.hasNextLine()) {

            if (sc.nextInt() < 0 && sc.nextInt() != 0) {
                flag = false;
                break;
            }
        
            packOfCards.add(sc.nextInt());
        }

        if (flag == true) {
            this.packOfCards = packOfCards;
        } 
        else {
            packOfCards.clear();
            System.out.println("Invalid file");
        }

    }

}
