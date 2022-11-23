package testing;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.*;

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
            File file = new File("winner1.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                sc.nextLine();
                counter++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        mockCardGame.setFileName("winner1.txt");
        assertNotNull("winner1.txt");
        assertEquals(counter%8, 0);

        mockCardGame.resetFileName();
        String nullString = null;
        mockCardGame.setFileName(nullString);
        assertNull(fileName);
    }

    @Test
    public void clearInputData() {
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
            File file = new File("winner1.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
            sc.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        mockCardGame.setFileName("winner1.txt");
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
        mockCardGame.setFileName("textFiles/winner6.txt");
        mockCardGame.setPlayerCount(6);
        mockCardGame.setup(mockCard);
        assertNotEquals(mockCard.getPackOfCards(), temp); 
    }

    @Test
    public void cardWithValidData() throws IOException{
        mockCard.resetPackOfCards();

        mockCardGame.setFileName("winner1.txt");
        mockCardGame.setPlayerCount(4);

        mockCard.setPackOfCards("winner1.txt", 4);
        assertEquals(32, mockCard.getPackOfCards().size()); 
    }

    @Test
    public void cardWithInvalidData() throws NumberFormatException, IOException{
        mockCard.resetPackOfCards();

        mockCardGame.setFileName("winner6.txt");
        mockCardGame.setPlayerCount(4);

        mockCard.setPackOfCards("winner6.txt", 4);
        //to check if the pack of cards was set even though player count does not correspond with number of lines.
        assertNull(mockCard.getPackOfCards()); 
    }

    @Test
    public void fileExists() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("winner1.txt").getFile());
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
    public void checkDealtCards() throws IOException{
        MockCard newCard = new MockCard();
        mockCardGame.resetPlayerList();

        mockCardGame.setFileName("winner1.txt");
        mockCardGame.setPlayerCount(4);

        newCard.setPackOfCards("winner1.txt", mockCardGame.playerCount);
        
        mockCardGame.dealing(newCard);
        ArrayList<MockPlayer> localPlayerList = MockCardGame.playerList;
        Object [] player1Hand = {1, 5, 9, 13};
        Object [] player2Hand = {2, 6, 1, 14};
        Object [] player3Hand = {3, 7, 11, 15};
        Object [] player4Hand = {4, 8, 12, 16};

        Object[] hand1Array = localPlayerList.get(0).returnMyHand().toArray();
        Object[] hand2Array = localPlayerList.get(1).returnMyHand().toArray();
        Object[] hand3Array = localPlayerList.get(2).returnMyHand().toArray();
        Object[] hand4Array = localPlayerList.get(3).returnMyHand().toArray();

        assertArrayEquals(hand1Array, player1Hand);        
        assertArrayEquals(hand2Array, player2Hand);        
        assertArrayEquals(hand3Array, player3Hand);        
        assertArrayEquals(hand4Array, player4Hand);        
    }

    @Test
    public void checkDealtDecks(){
        MockCardDeck deck1 = MockCardDeck.deckList.get(0);
        MockCardDeck deck2 = MockCardDeck.deckList.get(1);
        MockCardDeck deck3 = MockCardDeck.deckList.get(2);
        MockCardDeck deck4 = MockCardDeck.deckList.get(3);

        assertNotNull(deck1);
        assertNotNull(deck2);
        assertNotNull(deck3);
        assertNotNull(deck4);

        Object [] player1Deck = {17, 21, 25, 29};
        Object [] player2Deck = {18, 22, 26, 30};
        Object [] player3Deck = {19, 23, 1, 31};
        Object [] player4Deck = {1, 24, 28, 32};

        assertArrayEquals(deck1.getDeckOfCards().toArray(), player1Deck);        
        assertArrayEquals(deck2.getDeckOfCards().toArray(), player2Deck);        
        assertArrayEquals(deck3.getDeckOfCards().toArray(), player3Deck);        
        assertArrayEquals(deck4.getDeckOfCards().toArray(), player4Deck);       
    }

    public void checkValidWinnerAtDealing() throws NumberFormatException, IOException{
        MockCard newCard = new MockCard();
        mockCardGame.resetPlayerList();

        mockCardGame.setFileName("winningHand.txt");
        mockCardGame.setPlayerCount(4);

        newCard.setPackOfCards("winningHand.txt", mockCardGame.playerCount);
        mockCardGame.dealing(newCard);

        assertTrue("Player boolean of winning should be true, but isn't", MockCardGame.playerList.get(0).isWinner());
        assertFalse("Player boolean of winning should be false, but isn't", MockCardGame.playerList.get(1).isWinner());
        assertFalse("Player boolean of winning should be false, but isn't", MockCardGame.playerList.get(2).isWinner());
        assertFalse("Player boolean of winning should be false, but isn't", MockCardGame.playerList.get(3).isWinner());

    }
}
