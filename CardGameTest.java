import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.*;

public class CardGameTest {
    private CardGame cardGame;
    private Card card;
    ArrayList<Integer> test;

    @Before
    public void testSetup() throws IOException {
        File file = new File("four.txt");
        cardGame = new CardGame();
        card = new Card();
    }

    @Test
    public void fileExists() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("four.txt").getFile());
        assertTrue(file.exists());
    }

    @Test
    public void testReadPackOfCards() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("four.txt").getFile());
        assertTrue(file.exists());

        card.setPackOfCards(file.getName(), 4);   //DOESNT WORK, FILE NOT FOUND
        test = card.getPackOfCards();
        assertNotNull(test);

    }

}
