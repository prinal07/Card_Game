package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import code.CardGame;
import code.Player;

public class PlayerTest {
    public MockCardGame cardGame = new MockCardGame();

    @Test
    public void testGetId() {

    }

    @Test
    public void testGetLogFileName() {

    }

    @Test
    public void testGetOutputFile() {

    }

    @Test
    public void testSetLogFileName() {

    }

    @Test
    public void testSetOutputFile() {

    }

    @Test
    public void testTakeMine() {

    }

    @Before
    public void setupForPlayerCreation() throws NumberFormatException, IOException {
        MockCard card = new MockCard();
        card.setPackOfCards("valid.txt", 4);
        cardGame.dealing(card);
    }

    @Test
    public void correctNumberOfPlayersCreated() {
        assertEquals(MockCardGame.playerList.size(), 4);
    }

    @Test
    public void correctPlayerIds() {
        int ctr = 1;
        for (MockPlayer p : MockCardGame.playerList) {
            assertEquals(p.getMyId(), ctr);
            ctr++;
        }
    }

    @Test
    

    @After
    public void resetForPlayerCreation() {
        cardGame.resetDealing();
    }


}
