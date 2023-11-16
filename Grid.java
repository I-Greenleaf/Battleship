public class Grid
{
    // instance variables
    private Location[][] grid;

    // Constants for number of rows and columns.
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    
    // Create a new Grid. Initialize each Location in the grid
    // to be a new Location object.
    public Grid()
    {
        grid = new Location[NUM_ROWS][NUM_COLS];
        for (int r = 0; r < NUM_ROWS; r++)
        {
            for (int c = 0; c < NUM_COLS; c++)
            {
                grid[r][c] = new Location();
            }
        }
    }
    
    // Mark a hit in this location by calling the markHit method
    // on the Location object.  
    public void markHit(int row, int col)
    {
        grid[row][col].setStatus(1);
    }
    
    // Mark a miss on this location.    
    public void markMiss(int row, int col)
    {
        grid[row][col].setStatus(2);
    }
    
    // Set the status of this location object.
    public void setStatus(int row, int col, int status)
    {
        grid[row][col].setStatus(status);
    }
    
    // Get the status of this location in the grid  
    public int getStatus(int row, int col)
    {
        return grid[row][col].getStatus();
    }
    
    // Return whether or not this Location has already been guessed.
    public boolean alreadyGuessed(int row, int col)
    {
        return grid[row][col].getStatus() != 0;
    }
    
    // Set whether or not there is a ship at this location to the val   
    public void setShip(int row, int col, boolean val)
    {
        grid[row][col].setShip(val);
    }
    
    // Return whether or not there is a ship here   
    public boolean hasShip(int row, int col)
    {
        return grid[row][col].hasShip();
    }
    
    
    // Get the Location object at this row and column position
    public Location get(int row, int col)
    {
        return grid[row][col];
    }
    
    // Return the number of rows in the Grid
    public int numRows()
    {
        return NUM_ROWS;
    }
    
    // Return the number of columns in the grid
    public int numCols()
    {
        return NUM_COLS;
    }
    
    // prints your guess of the oppenet's board
    public void printStatus()
    {
        char[] rowLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        char[] statusMarks = {'-', 'X', 'O'};
        
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int r = 0; r < NUM_ROWS; r++)
        {
            System.out.print(rowLetters[r] + " ");
            for (int c = 0; c < NUM_COLS; c++)
            {
                System.out.print(statusMarks[grid[r][c].getStatus()] + " ");
            }
            System.out.println();
        }
    }
    
    // Prints your board
    public void printShips()
    {
        char[] rowLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int r = 0; r < NUM_ROWS; r++)
        {
            System.out.print(rowLetters[r] + " ");
            for (int c = 0; c < NUM_COLS; c++)
            {
                if (grid[r][c].hasShip())
                {
                    System.out.print("X ");
                }
                else
                {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    /**
     * This method can be called on your own grid. To add a ship
     * we will go to the ships location and mark a true value
     * in every location that the ship takes up.
     */
    public void addShip(Ship s)
    {
        int r = s.getRow();
        int c = s.getCol();
        for (int i = 0; i < s.getLength();i++)
        {
            grid[r][c].setShip(true);
            if (s.getDirection() == 0)
            {
                c++;
            }
            else
            {
                r++;
            }
        }
    }
}