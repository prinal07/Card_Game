package testing;

import java.io.IOException;
import java.util.ArrayList;

public interface CardInterface {
    public ArrayList<Integer> getPackOfCards() throws IOException;
    public void setPackOfCards(String fileName, int playerCount) throws NumberFormatException, IOException;
}
