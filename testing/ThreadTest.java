package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import code.*;

public class ThreadTest {
    public MockCardGame mockCardGame;
    public MockCard mockCard;
    public int playerCount;
    public String fileName;

    @Test
    public void testPlayGame() throws NumberFormatException, IOException{
        mockCardGame = new MockCardGame();
        mockCard = new MockCard();
        mockCardGame.setFileName("valid.txt");
        mockCardGame.setPlayerCount(4);
        mockCard.setPackOfCards("C:/Users/prina/Desktop/Card_Game/valid.txt", 4);
        
        mockCardGame.dealing(mockCard);
        MockCardGame.playGame();
    }

    @Test
    public void checkValidWinner(){
        assertTrue("Private winner attribute not set to true", MockCardGame.playerList.get((mockCardGame.getWinnerId()-1)).isWinner());
        assertEquals(mockCardGame.getWinnerId(), 1);
    }

    @Test
    public void checkValidLosers(){
        assertFalse("Private winner attribute not set to false", MockCardGame.playerList.get(1).isWinner());
        assertFalse("Private winner attribute not set to false", MockCardGame.playerList.get(2).isWinner());
        assertFalse("Private winner attribute not set to false", MockCardGame.playerList.get(3).isWinner());
    }

        

}
