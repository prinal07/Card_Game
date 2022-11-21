package testing;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import code.Player;

public class PlayerTest {
    public CardGameTest cardGame = new CardGameTest();

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

    @Test
    public void playerListEmptySuccessfullyCreatingAPlayer() {
        Player player = new Player(1, 4);
        assertFalse("No players exist, but not successfully creating a player with proper id",
                player.getId() < CardGameTest.playerListTest.size());
        CardGameTest.playerListTest.add(player);
    }

    @Test
    public void playerAddedSuccessfully() {
        assertTrue("List is empty", CardGameTest.playerListTest.size() == 1);
        assertNotNull(CardGameTest.playerListTest.get(0));
        assertTrue("Incorrect id player was added",
                CardGameTest.playerListTest.get(0).getId() == 1);
    }

    @Before
    public void emptyPlayerList() {
    }

    @Test
    public void playerListEmptyCreatingFalseIdPlayer() {
        CardGameTest.playerListTest.clear();
        Player player = new Player(2, 4);
        assertTrue("Player cannot be created with this id, players with previous ids have not been created",
                player.getId()-1 < CardGameTest.playerListTest.size());
    }
}
