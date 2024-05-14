import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class SwingBoard {
    public static final int WIDTH = 320*2;
    public static final int HEIGHT = 320*2;
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;

    // Start of the program
    public static void main(String[] args) {
        JFrame frame = new JFrame("JFrame Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        Player computer = new Player();
        computerStartUp(computer);
        
        // Creates the GUI
        // SwingUtilities is boilerplate code
        // SwingUtilities.invokeLater(() -> {
        //     Board gridButtons = new Board(10, 10); // 3x3 grid of buttons
        //     gridButtons.setVisible(true);
        // });
        
        
        // Old Code
        
        // // Creates instances variables of Player for the player and computer
        // Player human = new Player();
        // Player computer = new Player();
        
        // // Has the player create their grid and the computer randomly create theirs
        // playerStartUp(human);
        // computerStartUp(computer);
        
        // // Prints out instructions of how to play


        // // Game loop
        // while (!gameOver(human, computer))
        // {
        //     playRound(human, computer);
        //     System.out.println("Your guesses");
        //     human.printMyGuesses();
        //     System.out.println("Computer's guesses");
        //     printComputerGuess(human, computer);
            
        // }
        
        // // Prints if the player or computer has won
        // if (human.hasWon())
        // {
        //     System.out.println("The player has won");
        //     computer.printMyShips();
        // }
        // else
        // {
        //     System.out.println("The player has somehow lost to the computer");
        // }

    
        
        JFrame f = new JFrame("Grid of Buttons"); 
    
        // Sets up the frame
        f.setSize(WIDTH, HEIGHT);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(new GridLayout(1, 2));
        // GridBagLayout b = new GridBagLayout();

        // JTextArea rowLetters = new JTextArea("A B C D E F G H I J");
        // f.add(rowLetters);


        // Sets up the Attacking Board as a Jpanel
        JPanel attackBoard = new JPanel();
        attackBoard.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));

        // Adds buttons to JPanel
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                ImageIcon icon = new ImageIcon("C:\\Users\\ijgso\\Documents\\coding\\javaWork\\swingBoard\\images\\water.png");
                JButton button = new JButton(icon);
                int finalRow = r;
                int finalCol = c;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (computer.recordOpponentGuess(finalRow, finalCol)) {
                            ImageIcon x = new ImageIcon("C:\\Users\\ijgso\\Documents\\coding\\javaWork\\swingBoard\\images\\O.png");
                            button.setDisabledIcon(x);
                        } else {
                            ImageIcon x = new ImageIcon("C:\\Users\\ijgso\\Documents\\coding\\javaWork\\swingBoard\\images\\X.png");
                            button.setDisabledIcon(x);
                        }
                        button.setEnabled(false);
                    }
                });
                attackBoard.add(button);
            }
        }
        f.add(attackBoard);
        


        // Beginning devlopment for a board to set your own ships on


        // Sets up the Attacking Board as a Jpanel
        // JPanel playerBoard = new JPanel();
        // playerBoard.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));

        // Adds buttons to JPanel
        // for (int r = 0; r < NUM_ROWS; r++) {
        //     for (int c = 0; c < NUM_COLS; c++) {
        //         ImageIcon icon = new ImageIcon("C:\\Users\\ijgso\\Documents\\coding\\javaWork\\swingBoard\\images\\water.png");
        //         JButton button = new JButton(icon);
        //         // button.setBounds(10,10,64,6400);
        //         int finalRow = r;
        //         int finalCol = c;
        //         button.addActionListener(new ActionListener() {
        //             @Override
        //             public void actionPerformed(ActionEvent e) {
        //                 setUpShips(finalRow, finalCol, button);

                        
        //                 // Print the row and column when the button is pressed
        //                 // System.out.println("Button pressed at: [" + finalRow + ", " + finalCol + "]");
                        
        //             }
        //         });
        //         playerBoard.add(button);
        //     }
        // }
        // f.add(playerBoard);


        // pack();

        f.setVisible(true);
    }

    // Call is currently commented out
    public static void setUpShips(int row, int col, JButton button) {
        // Starting with a case for a ship length of 2, will later add ships with other lengths
        int length = 2;
        if (!(col+length-1 > 9)) {
            ImageIcon x = new ImageIcon("C:\\Users\\ijgso\\Documents\\coding\\javaWork\\swingBoard\\images\\shipA.png");
            button.setDisabledIcon(x);
        }
        
        button.setEnabled(false);

    }


    // Pre game

    // Randomly sets up the computer's board of ships
    private static void computerStartUp(Player computer)
    {
        int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
        for (int i = 0; i < 5; i++)
        {
            int row;
            int col;
            
            // Setting up Random
            Random rand = new Random();

            int direction = rand.nextInt(2);
            if (direction == 0)
            {
                row = rand.nextInt(10);
                col = rand.nextInt(10-SHIP_LENGTHS[i]);
            }
            else
            {
                row = rand.nextInt(10-SHIP_LENGTHS[i]);
                col = rand.nextInt(10);
            }
            if (computer.shipInTheWay(row, col, direction, SHIP_LENGTHS[i]))
            {
                i--; // Makes sure that the for loop does not continue for incorrect placement
            }
            else
            {
                computer.chooseShipLocation(new Ship(SHIP_LENGTHS[i]), row, col, direction);
            }

        }
    }


    // Explains to the user how to set up their board and has the user set it up
    private static void playerStartUp(Player player)
    {
        int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++)
        {
            System.out.println("\nEnter the location for the ship with a length of " + SHIP_LENGTHS[i] + ":");
            
            System.out.println("Enter its row (0-9):");
            int row = sc.nextInt();
            
            System.out.println("Enter its column (0-9):");
            int col = sc.nextInt();
            
            System.out.println("Enter the direction (0 for horizontal and 1 for vertical):");
            int direction = sc.nextInt();
            
            /**
             * Checks if any part of the ship does not fit on the grid
             * Or if any part of the ship intersects with a part of another ship
             * It has the player redo the input if so
             * If the ship is a valid placement, it puts the ship on the
             * player's grid of ships
             */
            if (direction == 0 && (row < 0 || row > 9 || col < 0 || col > 9-SHIP_LENGTHS[i]) ||
                direction == 1 && (row < 0 || row > 9-SHIP_LENGTHS[i] || col < 0 || col > 9) ||
                player.shipInTheWay(row, col, direction, SHIP_LENGTHS[i]))
            {
                System.out.println("Ship out of bounds or inside another ship. Redo input.");
                i--;    // Makes sure that the for loop does not continue for incorrect placement
            }
            else
            {
                player.chooseShipLocation(new Ship(SHIP_LENGTHS[i]), row, col, direction);
                player.printMyShips();
            }
        }
    }
        
    
    

    // All code below has yet to be intergrated into the GUI


    // Game
    
    // plays the round and returns if the game is over
    private static void playRound(Player human, Player computer)
    {
        humanTurn(human, computer);
        if (!human.hasWon())
            computerTurn(human, computer);
    }
    
    /**
     * Creates the player's turn
     * Asks for the location of the guess
     * Checks if the guess is with the game board
     * Then makes the guess if it is
     */
    private static void humanTurn(Player human, Player computer)
    {
        System.out.println("\nGuess opponet's ship location.");
        
        Scanner sc = new Scanner(System.in);
            
        System.out.println("Enter its row (0-9):");
        int row = sc.nextInt();
            
        System.out.println("Enter its column (0-9):");
        int col = sc.nextInt();
        
        if (!((row < 0 || row > 9 || col < 0 || col > 9) ||
            (row < 0 || row > 9 || col < 0 || col > 9)))
        {
            human.markGuess(row, col, computer.recordOpponentGuess(row, col));
        }
    }
    
    
    // The computer randomly guesses a location for the player's ship
    private static void computerTurn(Player human, Player computer)
    {
        Random rand = new Random();

        int row = rand.nextInt(0, 9);
        int col = rand.nextInt(0, 9);
        
        computer.markGuess(row, col, human.recordOpponentGuess(row, col));
    }
    
    // Determines if someone has won
    private static boolean gameOver(Player human, Player computer)
    {
        if (human.hasWon() || computer.hasWon())
            return true;
        else
            return false;
    }
    
    // Prints the player's board with the computer's guess on it
    private static void printComputerGuess(Player human, Player computer)
    {
        char[] rowLetters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
        char[] statusMarks = {'-', 'X', 'O'};
        
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int r = 0; r < 10; r++)
        {
            System.out.print(rowLetters[r] + " ");
            for (int c = 0; c < 10; c++)
            {
                if (computer.getGuessGrid().getStatus(r, c) != 0)
                    System.out.print(statusMarks[computer.getGuessGrid().getStatus(r, c)] + " ");
                else if (human.getPlayerGrid().hasShip(r, c))
                    System.out.print("S ");
                else
                    System.out.print("- ");
            }
            System.out.println();
        }
    }

}
