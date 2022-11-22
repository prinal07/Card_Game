package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.multi.MultiColorChooserUI;

import org.junit.*;

import code.Card;
import code.CardGame;
import code.Player;

public class CardGameTest {
    public MockCardGame mockCardGame = new MockCardGame();
    public MockCard mockCard = new MockCard();
    public int playerCount;
    public String fileName;


    /**
     * @throws IOException
     */
    @Test
    public void testValidInputData() throws IOException {
        mockCardGame.setPlayerCount(4);
        assertTrue(mockCardGame.playerCount > 0);
        int counter = 0;
        // read each line and
        // count number of lines
        try {
            File file = new File("valid.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                sc.nextLine();
                counter++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        mockCardGame.setFileName("valid.txt");
        assertNotNull("valid.txt");
        assertEquals(counter%8, 0);

        mockCardGame.resetFileName();
        String nullString = null;
        mockCardGame.setFileName(nullString);
        assertNull(fileName);
    }

    @Test
    public void clearInputData() {
        //mockCardGame.resetPlayerCount();
        mockCardGame.resetFileName();
    }

    @Test
    public void testInvalidInputData() {
        mockCardGame.setPlayerCount(-2);
        assertFalse(mockCardGame.playerCount > 0);

        int count = 0;

        // read each line and
        // count number of lines
        try {
            File file = new File("invalid.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        mockCardGame.setFileName("invalid.txt");
        assertNotEquals(count, 8*mockCardGame.playerCount);
    }

    @Test
    public void testSetupWithEmptyCard() throws NumberFormatException, IOException{
        mockCard.resetPackOfCards();
        assertNull("Should be empty/null", mockCard.getPackOfCards());
        //this is testing if the card is null/empty. As setup method has a check if it is empty
        // and cannot be tested here as it is a void method.
        
    }

    @Test
    public void testSetupWithFullCard() throws NumberFormatException, IOException{
        ArrayList<Integer> temp = mockCard.getPackOfCards();
        mockCardGame.resetFileName();
        mockCardGame.resetPlayerCount();
        mockCardGame.setFileName("C:/Users/prina/Desktop/Card_Game/valid6.txt");
        // have to enter complete path for it to find file?
        mockCardGame.setPlayerCount(6);
        mockCardGame.setup(mockCard);
        assertNotEquals(mockCard.getPackOfCards(), temp); 
    }

    @Test
    public void cardWithValidData() throws IOException{
        mockCard.resetPackOfCards();

        mockCardGame.setFileName("valid.txt");
        mockCardGame.setPlayerCount(4);

        mockCard.setPackOfCards("C:/Users/prina/Desktop/Card_Game/valid.txt", 4);
        assertEquals(32, mockCard.getPackOfCards().size()); 
    }

    @Test
    public void cardWithInvalidData() throws NumberFormatException, IOException{
        mockCard.resetPackOfCards();

        mockCardGame.setFileName("valid6.txt");
        mockCardGame.setPlayerCount(4);

        mockCard.setPackOfCards("C:/Users/prina/Desktop/Card_Game/valid6.txt", 4);
        //to check if the pack of cards was set even though player count does not correspond with number of lines.
        assertNull(mockCard.getPackOfCards()); 
    }

    @Test
    public void fileExists() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("valid.txt").getFile());
        assertTrue(file.exists());
    }

    @Test
    public void testDealing() throws IOException {
        mockCardGame.dealing(mockCard);
    }

    @Test 
    public void resetDealing(){
        mockCardGame.resetDealing();
    }

    @Test
    public void testMain() {

    }

}
