package code;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import testing.CardInterface;

public class Card implements CardInterface{
    private ArrayList<Integer> packOfCards = new ArrayList<>();

    /** 
     * @return ArrayList<Integer>
     * @throws IOException
     */
    public ArrayList<Integer> getPackOfCards() throws IOException {
        return this.packOfCards;
    }

    
    /** 
     * @param fileName
     * @param playerCount
     * @throws NumberFormatException
     * @throws IOException
     */
    public void setPackOfCards(String fileName, int playerCount) throws NumberFormatException, IOException {

        ArrayList<Integer> packOfCards = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Boolean flag = true;
        int lineCount = 0;

        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while (br.readLine() != null) {
            lineCount++;
        }

        br.close();
        


        while(flag == true && lineCount == (8*playerCount)){
            try{
                Integer localVal = sc.nextInt();
                if(localVal > 0 || localVal == 0){
                    packOfCards.add(localVal);
                }
                else{
                    System.out.println("Invalid File");
                    flag = false;
                }
            }
            catch (NoSuchElementException e){
                if(packOfCards.size() == (8*playerCount)){
                    flag = false;
                    this.packOfCards = packOfCards;
                }
                else{
                    System.out.println("Invalid File");
                    flag = false;
                }

            }
        }
        sc.close();
    }


    }


