import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

public class CardGameTest{
    private CardGame cardGame;
    private Card card;
    ArrayList<Integer> test;
    String [] stringsValues = {"hello", "", " "};
    int [] intValues = {-2, 0, 2};
    File file;

    /** 
     * @throws IOException
     */
    @Before public void testSetup() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("valid.txt").getFile());
        cardGame = new CardGame();
        card = new Card();
        int playerCount = intValues[0];
    }

    @Test
    public void testInputData() {
        int invalid = intValues[0];
        int valid = intValues[1];
        int valid_positive = intValues[2];

        assertTrue(invalid < 0);
        assertTrue(valid >= 0);
    }


    @Test
    public void fileExists() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("valid.txt").getFile());
        assertTrue(file.exists());
    }

    
    /** 
     * @throws IOException
     */
    @Test
    public void testReadPackOfCards() throws IOException {
        // File file = new File("C:/Users/prina/Desktop/Card_Game/valid.txt");
        assertTrue(file.exists());
        String filename = file.getName();
        assertNotNull(filename);
        card.setPackOfCards(filename, 4);   //DOESNT WORK, FILE NOT FOUND
        test = card.getPackOfCards();
        assertNotNull(test);

    }

    @Test
    public void testDealing() {
        
    }

   
    @Test
    public void testMain() {
        
    }


    

}
