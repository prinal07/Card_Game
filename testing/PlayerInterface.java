package testing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface PlayerInterface {
    public boolean isWinner();
    public void setWinner(boolean winner);
    public int getMyId();
    public File getOutputFile();
    public void setOutputFile(File outputFile);
    public void takeMine(ArrayList<Integer> pack) throws IOException;
    public void checkHand() throws IOException;
    public void winner() throws IOException;
    public void loser() throws IOException;
    public void createFile();
    public void takeCard() throws IOException;
    public void discardCard() throws IOException;
    public void writeToLogFile(File fileName, String data) throws IOException;


}
