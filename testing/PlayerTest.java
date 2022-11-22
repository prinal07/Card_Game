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
    public MockCard card;

    @Test
    public void correctNumberOfPlayersCreated() throws NumberFormatException, IOException {
        cardGame.setPlayerCount(4);
        card = new MockCard();
        card.setPackOfCards("C:/Users/prina/Desktop/Card_Game/valid.txt", 4);
        cardGame.dealing(card);
        assertEquals(cardGame.getPlayerCount(), 4);
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
    public void resetForPlayerCreation() {
        cardGame.resetDealing();
    }

    @Test
    public void checkIfFilesAreCreatedProperly(){
        //WILL HAVE TO RUN THE PLAYGAME METHOD HERE... FIRST FINISH THAT.
    }

    




}
