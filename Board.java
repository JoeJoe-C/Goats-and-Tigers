
/**
 * Maintains and updates the status of the board
 * i.e. the locations of goats and tigers
 *
 * @Student 1 Name: 
 * @Student 1 Number: 
 * 
 * @Student 2 Name: 
 * @Student 2 Number:
 */
public class Board
{
    // An enumated type for the three possibilities
    private enum Piece {GOAT, TIGER, VACANT};
    Piece[] board;

    /**
     * Constructor for objects of class Board.
     * Initializes the board VACANT.
     */
    public Board()
    {
        // TODO 3
        board = new Piece[24];
        for (int i = 0; i<=23; i++)
        {
            board[i] = Piece.VACANT;
        }
    }

            
    /**
     * Checks if the location a is VACANT on the board
     */
    public boolean isVacant(int a)
    {
        //TODO 4
        //conditional if board array at index a is vacant.
        //if board[a] is true, the method returns true
        //if board[a] is false, the method returns false
        if (board[a] == Piece.VACANT) //checks for the location position (0-23) these are the indexes for the board array 
        {
            return true;
        } else {
            return false;
        }
        
    }
    
    /**
     * Sets the location a on the board to VACANT
     */
    public void setVacant(int a)
    {
        //TODO 5
        //access the index of the board using board[a]
        //set board[a] to Piece.Vacant
        board[a] = Piece.VACANT;
    }
    
    /**
     * Checks if the location a on the board is a GOAT
     */
    public boolean isGoat(int a)
    {
        //TODO 6
        //conditional to check if board[a] (0-23) is Piece.GOAT
        //returns true if board[a] is GOAT
        //returns false if board[a] is not GOAT
        if (board[a] == Piece.GOAT)
        {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Sets the location a on the board to GOAT
     */
    public void setGoat(int a)
    {
        //TODO 7
        //the location a with range (0-23)
        //changes board[a] into Piece.GOAT
        board[a] = Piece.GOAT; 
    }
    
    /**
     * Sets the location a on the board to TIGER
     */
    public void setTiger(int a)
    {
        //TODO 8
        //uses location a with range 0-23
        //changes board[a] into Piece.TIGER
        board[a] = Piece.TIGER;
    }
    
    /**
     * 
     */
    public boolean isTiger(int a)
    {
        //uses location a with range 0 - 23
        //checks if board[a] is tiger
        if (board[a] == Piece.TIGER) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Moves a piece by swaping the contents of a and b
     */
    public void swap(int a, int b)
    {
        //TODO 9
        //if we would like to swap contents, we have to create a temporary variable that acts as a placeholder
        Piece tempC = board[a]; //temp variable that holds the contents of b
        board[a] = board[b]; // board[a] points to address board[b]
        board[b] = tempC; //board[b] points to same address as tempC which is board[a]
        
        //test prints
        System.out.println(board[a]);
        System.out.println(board[b]);
        
    }
    //Test method
    /**public void checkPiece(){
        for (int i = 0; i<=23;i++){
            System.out.println(this.board[i]);
        }
        System.out.print(this.board);
    }*/
}

