package testing;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import code.Card;
import code.CardGame;
import code.Player;

public class MockCardGame implements CardGameInterface{
    public Card card;
    public int playerCount;
    public String fileName;
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<Integer> pack;
    static ArrayList<Player> playerList = new ArrayList<>();
    public ArrayList<Thread> threads = new ArrayList<>();
    public boolean winningBool = false;
    public int winnerId;

    public void setPlayerCount(int i){
        this.playerCount = i;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void resetPlayerCount(){
        this.playerCount = 0;
    }

    public void setWinningBool(Boolean bool){
        this.winningBool = bool;
    }

    public void resetWinningBool(){
        this.winningBool = false;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String filename){
        this.fileName = filename;
    }

    public void resetFileName(){
        this.fileName = "";
    }

    // input data if wanted to be tested, will need to change tests in CardGameTests to run the inputData from here,
    // meaning will have to copy implementation of inputData here and add extra additions such as arguments or smth
    public void inputData(){
    }
  
    public void dealing(){
    }

    public void setup(MockCard card) throws NumberFormatException, IOException{
        while (card.getPackOfCards().isEmpty() == true) {
            card.setPackOfCards(this.fileName, this.playerCount);
        }

    }

    public static void playGame(){}
}
