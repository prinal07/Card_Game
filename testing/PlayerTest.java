package testing;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;

public class PlayerTest {
    public MockCardGame cardGame = new MockCardGame();
    public MockCard card;

    @Test
    public void correctNumberOfPlayersCreated() throws NumberFormatException, IOException {
        cardGame.resetPlayerList();
        cardGame.setPlayerCount(4);
        card = new MockCard();
        card.setPackOfCards("C:/Users/prina/Desktop/Card_Game/winner1.txt", 4);
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
}
