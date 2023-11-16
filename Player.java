public class Player
{
    // Constants
    private static final int MAX_HITS = 17;
    
    // Grids
    private Grid playerGrid;
    private Grid guessGrid;
    
    // Variables
    private int numShips;
    private int hitsGiven;
    
    // Player constructor
    public Player()
    {
        playerGrid = new Grid();
        guessGrid = new Grid();
        
    }
    
    // returns the player's grid (the grid with thier ships)
    public Grid getPlayerGrid()
    {
        return playerGrid;
    }
    
    // returns the grid that the stores the player's guesses
    public Grid getGuessGrid()
    {
        return guessGrid;
    }
    
    /** 
     * Returns if the game is over. Meaning that the number of hits required
     * is equal to the number of hits that the player has gotten
     */
    public boolean hasWon()
    {
        return hitsGiven == MAX_HITS;
    }

    // sets a ship location
    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        if (numShips < 5)
        {
            s.setLocation(row, col);
            s.setDirection(direction);
            playerGrid.addShip(s);
            numShips++;
        }
    }
    
    /**
     * Determines if there is a ship where the user is trying to put a new ship
     * during the setup of the game
     */
    public boolean shipInTheWay(int row, int col, int direction, int length)
    {
        boolean shipsInTheWay = false;
            
            for (int i = 0; i < length; i++)
            {
                if (direction == 0)
                {
                    if (playerGrid.hasShip(row, col+i))
                        shipsInTheWay = true;
                }
                else
                {
                    if (playerGrid.hasShip(row+i, col))
                        shipsInTheWay = true;
                }
          }
          return shipsInTheWay;
    }
    
    // Print your ships on the grid
    public void printMyShips()
    {
        playerGrid.printShips();
    }
    
    // Print opponent guesses
    public void printOpponentGuesses()
    {
        playerGrid.printStatus();
    }
    
    // Print your guesses
    public void printMyGuesses()
    {
        guessGrid.printStatus();
    }
    
    /**
     * On the player's guess grid, it sets the location of the guess from
     * unguessed (0) to either hit (1) or miss (2)
     */
    public void markGuess(int row, int col, boolean val)
    {
        if (!guessGrid.alreadyGuessed(row, col))
        {
            if(val)
            {
                guessGrid.setStatus(row, col, 1);
                hitsGiven++;
            }
            else
            {
                guessGrid.setStatus(row, col, 2);
            }
        }
}
    
    // Record a guess from the opponent and returns if there is a ship there
    public boolean recordOpponentGuess(int row, int col)
    {
        if (playerGrid.hasShip(row, col))
            playerGrid.setStatus(row, col, 1);
        else
            playerGrid.setStatus(row, col, 2);
        return playerGrid.hasShip(row, col);
    }
}