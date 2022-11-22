package testing;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import code.Card;
import code.CardGame;
import code.Player;

public class MockCardGame implements CardGameInterface{
    // public MockCard card;
    public int playerCount;
    public String fileName;
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<Integer> pack = new ArrayList<>();
    static ArrayList<MockPlayer> playerList = new ArrayList<>();
    public ArrayList<Thread> threads = new ArrayList<>();
    public static boolean winningBool = false;
    public static int winnerId;

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
  
    public void dealing(MockCard card) throws IOException{
        pack = card.getPackOfCards();

        for (int i = 1; i < playerCount + 1; i++) {
            MockPlayer player = new MockPlayer(i, playerCount);
            playerList.add(player);
            player.takeMine(pack);

            MockCardDeck cardDeck = new MockCardDeck(i);
            MockCardDeck.deckList.add(cardDeck);
            cardDeck.takeMineDeck(pack, playerCount);

            for (int j: cardDeck.getDeckOfCards()){
            System.out.println("TEST DECK CARDS" + j);
            }
            System.out.println("TEST DECK DONE");
            player.checkHand();

        }
    }

    public void setup(MockCard card) throws NumberFormatException, IOException{
        while (card.getPackOfCards().isEmpty() == true) {
            card.setPackOfCards(this.fileName, this.playerCount);
        }

    }

    public void resetDealing(){
        pack.clear();
        for(int i = 1; i<playerCount + 1; i++){
            MockPlayer a = playerList.get(i);
            playerList.remove(a);
            a = null;

            MockCardDeck b = MockCardDeck.deckList.get(i);
            MockCardDeck.deckList.remove(b);
            b = null;
        }
    }

    public static void playGame(){}
}
