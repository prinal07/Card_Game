import java.io.IOException;

public class InterruptionThread implements Runnable {

    public String threadName;
    public Player player;

    public InterruptionThread(int i) {
        threadName = "Player " + (i + 1);
        player = CardGame.playerList.get(i);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            if(CardGame.winningBool){
                Thread.currentThread().interrupt();;
            }

            try {
                player.takeCard();
            } catch (IOException e) {
                System.out.println(
                        Thread.currentThread().getName()
                                + "is throwing an excepting while taking a card");
                e.printStackTrace();
            }
            try {
                player.discardCard();
            } catch (IOException e) {
                System.out.println(Thread.currentThread().getName()
                        + "is throwing an excepting while discarding a card");
                ;
                e.printStackTrace();
            }

            try {
                player.checkHand();
            } catch (IOException e) {
                System.out.println(
                        Thread.currentThread().getName()
                                + "is throwing an excepting while checking hand");

            }
        }
        
    }
}
