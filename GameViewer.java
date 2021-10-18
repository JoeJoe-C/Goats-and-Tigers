
/**
 * Controls the drawing of the board and game play. 
 * Allows the human player to make goat moves.
 * Calls AIplayer to make tiger moves.
 *
 * @Student 1 Name: Yoseph Wilmott Campbell
 * @Student 1 Number: 22689394
 * 
 * @Student 2 Name: Matthew Mannion
 * @Student 2 Number: 22500809
 */

import java.awt.*;
import java.awt.event.*; 
import javax.swing.SwingUtilities;

public class GameViewer implements MouseListener
{
    // instance variables
    private int bkSize; // block size - all other measurements to be derived from bkSize
    private int brdSize; // board size
    private SimpleCanvas sc; // an object of SimpleCanvas to draw 
    private GameRules rules; // an object of GameRules
    private Board bd; // an object of Board
    public AIplayer ai; //an object of AIplayer
    
    // 2D coordinates of valid locations on the board in steps of block size                                  
    public static final int[][] locs = {{1,1},                  {4,1},                  {7,1},
    
                                                {2,2},          {4,2},          {6,2},
                                                
                                                        {3,3},  {4,3},  {5,3}, 
                                                        
                                        {1,4},  {2,4},  {3,4},          {5,4},  {6,4},  {7,4},
                                        
                                                        {3,5},  {4,5},  {5,5},
                                                        
                                                {2,6},          {4,6},          {6,6},        
                                        
                                        {1,7},                  {4,7},                  {7,7} };
                                 
    // source and destination for the goat moves                             
    private int[] mov = {-1,-1}; //-1 means no selection

    /**
     * Constructor for objects of class GameViewer
     * Initializes instance variables and adds mouse listener.
     * Draws the board.
     */
    public GameViewer(int bkSize)
    {        
        this.bkSize = bkSize;
        brdSize = bkSize*8;
        sc = new SimpleCanvas("Tigers and Goats", brdSize, brdSize, Color.BLUE);
        sc.addMouseListener(this);           
        rules = new GameRules();
        bd = new Board();
        ai = new AIplayer();              
        drawBoard();                      
    }
    
    /**
     * Constructor with default block size
     */
    public GameViewer( )
    {
        this(80);
    }
    
    /**
     * Draws the board lines and the pieces as per their locations.
     * Drawing of lines is provided, students to implement drawing 
     * of pieces and number of goats.
     */
    private void drawBoard()
    {
        sc.drawRectangle(0,0,brdSize,brdSize,Color.BLUE); //wipe the canvas
        
        //draw shadows of Goats and Tigers - not compulsory, for beauty only /////////////
        
        //////////////////////////////////////////////////////                
        // Draw the lines
        for(int i=1; i<9; i++)
        {
            //draw the red lines
            if(i<4)
                sc.drawLine(locs[i-1][0]*bkSize, locs[i-1][1]*bkSize,
                        locs[i+5][0]*bkSize, locs[i+5][1]*bkSize, Color.red);
            else if(i==4)
                sc.drawLine(locs[i+5][0]*bkSize, locs[i+5][1]*bkSize,
                        locs[i+7][0]*bkSize, locs[i+7][1]*bkSize, Color.red);
            else if(i==5)
                sc.drawLine(locs[i+7][0]*bkSize, locs[i+7][1]*bkSize,
                        locs[i+9][0]*bkSize, locs[i+9][1]*bkSize, Color.red);              
            else
                sc.drawLine(locs[i+9][0]*bkSize, locs[i+9][1]*bkSize,
                        locs[i+15][0]*bkSize, locs[i+15][1]*bkSize, Color.red);              
           
            if(i==4 || i==8) continue; //no more to draw at i=4,8
            // vertical white lines
            sc.drawLine(i*bkSize, i*bkSize,
                        i*bkSize, brdSize-i*bkSize,Color.white);            
            // horizontal white lines
            sc.drawLine(i*bkSize,         i*bkSize,
                        brdSize-i*bkSize, i*bkSize, Color.white);  
            
        }
        
        // TODO 10 
        // Draw the goats and tigers. (Drawing the shadows is not compulsory)
        // for loop that goes through length of the locs
        for (int index = 0; index < locs.length; index++) {
            if (bd.isGoat(index)==true) { //condition that checks if the goat at index is GOAT
                int xPos = locs[index][0] * bkSize; //gets the X pixel coordinates
                int yPos = locs[index][1] * bkSize; //gets the y pixel coordinates
                sc.drawDisc(xPos,yPos,10,Color.GREEN); //draws a disc from SimpleCanvas
            } 
            else if (bd.isVacant(index)==true) {
                continue;
            }
            else {
                int xPos = locs[index][0] * bkSize;
                int yPos = locs[index][1] * bkSize;
                sc.drawRectangle(xPos - 8, yPos - 8, xPos + 5, yPos + 5, Color.RED);
            }
        }
        
        // Display the number of goats
        sc.drawString("Number of goats: " + rules.getNumGoats(), 5, 15 , Color.WHITE);
    }
    
    /**
     * If vacant, place a goat at the user clicked location on board.
     * Update goat count in rules and draw the updated board
     */
    public void placeGoat(int loc) 
    {   
        //TODO 2
        //if statement that calls the isVacant function from board
        //the isVacant function returns boolean
    
        if (bd.isVacant(loc) == true)
        {
            //calls the method from board class to set the location as GOAT
            //if the location is VACANT
            bd.setGoat(loc);    //Sets location enum Piece
             
            rules.addGoat(+1);   //adds goat number to count goats from GameRules
            
            this.drawBoard();   //redraws the board
            
        }
    }
        
    
    
    /**
     * Calls the placeTiger method of AIplayer to place a tiger on the board.
     * Increments tiger count in rules.
     * Draws the updated board.
     */
    public void placeTiger() 
    {   
        //TODO 13
        ai.placeTiger(bd); //calls placetiger method from AI
        rules.incrTigers(); //increments through rules
        drawBoard(); //updates the board 
               
    }
    
    /**
     * Toggles goat selection - changes the colour of selected goat.
     * Resets selection and changes the colour back when the same goat is clicked again.
     * Selects destination (if vacant) to move and calls moveGoat to make the move.
     */
    public void selectGoatMove(int loc) 
    {   
        //TODO 16
        boolean goatSelected = true; //when method is called, goat is selected
        while (goatSelected){ //while loop continues until goatSelected is False
            //we need to change the colour of selected goat at loc
            //update the board
            //etc
        }
    }
    
    /**
     * Make the user selected goat move only if legal otherwise set the destination to -1 (invalid).
     * If did make a goat move, then update board, draw the updated board, reset mov to -1,-1.
     * and call tigersMove() since after every goat move, there is a tiger move.
     */
    public void moveGoat() 
    {   
        //TODO 18        
        
    }
 
    /**
     * Call AIplayer to make its move. Update and draw the board after the move.
     * If Tigers cannot move, display "Goats Win!".
     * If goats are less than 6, display "Tigers Win!".
     * No need to terminate the game.
     */
    public void tigersMove()
    {
        //TODO 20
                                
    }
        
    /**
     * Respond to a mouse click on the board. 
     * Get a valid location nearest to the click (from GameRules). 
     * If nearest location is still far, do nothing. 
     * Otherwise, call placeGoat to place a goat at the location.
     * Call this.placeTiger when it is the tigers turn to place.
     * When the game changes to move stage, call selectGoatMove to move 
     * the user selected goat to the user selected destination.
     */
    public void mousePressed(MouseEvent e) 
    {
        //TODO 1
        //local variables
        int mouseX = e.getX();  //gets the x value of the pointed click
        int mouseY = e.getY();  //gets the y values of the pointed click
        
        int isLegalClick = rules.nearestLoc(mouseX,mouseY,bkSize);  //returns 0 - 23 corresponding to index of coordinates
        //condition for the phase of the game {intial placing phase or movingphase}
        if (rules.isMoveStage() == false)
        {
            if (isLegalClick != -1 && rules.isGoatsTurn() == true)  //conditions for intial phase of the game (placing the goats/tigers)
            {
                this.placeGoat(isLegalClick); //places goat on legalClick
            } else if(rules.isGoatsTurn() == false)
            {
                this.placeTiger(); //calling the placeTiger for this instance
            }
        } else
        {
           if (isLegalClick != -1 && rules.isGoatsTurn() == true) //inf it is move phase, it will select goat from mouseclick
           {
               this.selectGoatMove(isLegalClick);
           }
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
