
/**
 * Implments a simple AI player to 
 * automatically contol the tiger moves
 *
 * @author Professor Ajmal Mian
 * @dated Sep 2021
 * @version 1.0
 */
//import java.util.Random;
import java.util.*;

public class AIplayer
{
    private Random rn; // for random tiger or location selection
    private GameRules rul; // an instance of GameRules to check for legal moves
    public int[] tigerLocs; // location of tigers for convenience 
    private int ntigers; // number of tigers placed
    
    /**
     * Constructor for objects of class AIplayer.
     * Initializes instance variables.
     */
    public AIplayer()
    {
        // TODO 14
        rn = new Random();
        rul = new GameRules();
        tigerLocs = new int[3];  //this will create an empty Array of 0's
        ntigers = 0;  //beginnin of game number of tigers is 0
    }

    /**
     * Place tiger in a random vacant location on the Board
     * Update the board, the tiger count and tigerLocs.
     */
    public void placeTiger(Board bd)
    {
        //TODO 15
        //using a while loop will allow it to appear more random than a for loop
        boolean isPlaced = false;  //condition to break the loop
        for (int index = 0; index < tigerLocs.length ; index++){
            while (!isPlaced){ //while loop to keep iterating until isPlaced is true
                int indexNumber = rn.nextInt(24); //generates a random in from 0 - 23
                if (bd.isVacant(indexNumber) == true) { //if vacant place tiger
                    bd.setTiger(indexNumber); //calls setTiger method from board
                    tigerLocs[index] = indexNumber;  //in the index it will be 1
                    isPlaced = true; //break the loop and end method
                }
                else {
                    continue; //if not vacant skip iteration
                }
            }
            ntigers += 1; //increment the number of tigers by 1
        }
    }
    /**
     * If possible to eat a goat, must eat and return 1
     * If cannot eat any goat, make a move and return 0
     * If cannot make any move (all Tigers blocked), return -1
     */
    public int makeAmove(Board bd)
    {
        if (eatGoat(bd))  return 1; // did eat a goat
        else if (simpleMove(bd)) return 0; // made a simple move
        else return -1; // could not move
    }
    
    /**
     * Randomly choose a tiger, move it to a legal destination and return true
     * if none of the tigers can move, return false.
     * Update the board and the tiger location (tigerLocs)
     */
    private boolean simpleMove(Board bd)
    {
        //TODO 21
        boolean moved = false;
        int validation = 0; //if 0 no possible moves, if greater than 1 possible moves
        for (int i = 0 ; i < tigerLocs.length; i++) {
            int currentPosition = tigerLocs[i];
            for (int legMoves : rul.legalMoves[currentPosition]){
                if (rul.isLegalMove(currentPosition, legMoves)){
                       validation += 1;
                }
            }
        }
        
        if (validation >=1){
            while (!moved) { //because we don't know how many times we need to verify
                int randomindex = rn.nextInt(3);//random integer from 0 - 2
                int tigerPosition = tigerLocs[randomindex];
                int[] possibleMoves = rul.legalMoves[tigerPosition]; 
                for(int moves : possibleMoves) {
                    if (bd.isVacant(moves)) {
                    bd.swap(tigerPosition, moves);
                    tigerLocs[randomindex] = moves;
                    return true;
                    }
                }
            }
        }
        return false;
    }
    
    
    /**
     * If possible, eat a goat and return true otherwise return false.
     * Update the board and the tiger location (tigerLocs)
     * 
     * Hint: use the canEatGoat method of GameRules
     */
    private boolean eatGoat(Board bd)
    {
        //TODO 22
        //if (rul.canEatGoat(
        return false;
    }
   
}
