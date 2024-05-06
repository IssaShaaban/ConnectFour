import java.util.*;

/**
 * Basic Connect Four game
 * @author Issa Shaaban
 */
public class ConnectFour
{
    private String[][] grid; // Game girid
    private int columns; // Number of columns in the grid
    private int rows; // Number of rows in the grid
    private int toWin; // Amount of tokens in a row needed to win
    private final String PLAYER1 = "X"; // Token of red player
    private final String PLAYER2 = "O"; // Token of yellow player
    private Scanner input = new Scanner(System.in); // Input from the user(s)

    /**
     * Initialise game grid by getting user to choose size
     */
    public ConnectFour()
    {
        System.out.println("Enter the corresponding number to get the size of the grid. \n (1) 7x6 (2) 8x7 (3) 9x7 (4) 10x7");
        int gridSize = input.nextInt();
        switch (gridSize) {
            case 1:
                columns = 7; rows = 6;
                break;
            case 2:
                columns = 8; rows = 7;
                break;
            case 3:
                columns = 9; rows = 7;
                break;
            case 4:
                columns = 10; rows = 7;
                break;
            default:
                columns = 7; rows = 6;
                System.out.println("Invalid number entered! Default size of 7x6 grid created!");
                break;
        }
        newGrid(columns, rows);
        System.out.println("Enter the number of tokens in a row needed to win!");
        toWin = input.nextInt();
        while (toWin >= columns)
        {
            System.out.println("Number cannot be equal to or greater than number of columns!");
            toWin = input.nextInt();
        }
    }

    /**
     * Runs the game
     */
    public void runGame()
    {
        int winnerAcross;
        int winnerVertical;
        int winnerDiagonals;
        boolean draw = false;
        while (true)
        {
            inputPrompt(PLAYER1);
            gridState();
            winnerAcross = horizontal(PLAYER1);
            winnerVertical = vertical(PLAYER1);
            winnerDiagonals = diagonals(PLAYER1);
            draw = gameDraw();
            if (winnerAcross == 1 || winnerVertical == 1 || winnerDiagonals == 1) {System.out.println("Player 1 has won!"); break;}
            if (draw == true) System.out.println("Game ended in a draw!");
            inputPrompt(PLAYER2);
            gridState();
            winnerAcross = horizontal(PLAYER2);
            winnerVertical = vertical(PLAYER2);
            winnerDiagonals = diagonals(PLAYER2);
            draw = gameDraw();
            if (draw == true) System.out.println("Game ended in a draw!");
            if (winnerAcross == 1 || winnerVertical == 1 || winnerDiagonals == 1) {System.out.println("Player 2 has won!"); break;}
        }
        
    }

    /**
     * User prompt of token location
     * @param player Current player playing
     */
    public void inputPrompt(String player)
    {
        String turn;
        if (player.equals(PLAYER1)) turn = "( PLAYER 1 - X)";
        else turn = "( PLAYER 2 - O)";
        System.out.println(turn + " Choose the column to enter your token: 0-" + (columns-1) +"!");
        int col = input.nextInt();
        if (col < columns)
        {
            addToken(col, player);
        }
        else {System.out.println("Invalid input!"); inputPrompt(player);}
    }

    /**
     * Creates the required grid
     * @param col Number of columns
     * @param row Number of rows
     * @return Returns the new created grid
     */
    public String[][] newGrid(int col,int row)
    {
        grid = new String[row][col];
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                grid[i][j] = "-";
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        return grid;
    }

    /**
     * Adds the token to the correct column
     * @param col Coloumn to add the token
     * @param player Current player playing
     */
    public void addToken(int col,String player)
    {
        int row = rows - 1;
        boolean loop = true;
        while (loop)
        {
            if (row == -1) {System.out.println("Cannot add token to column " + col + " anymore!"); inputPrompt(player); break;}
            if (grid[row][col].equals("-"))
            {
                grid[row][col] = player;
                loop = false;
            }
            row--;
        }
    }

    /**
     * Prints a current state of the game
     */
    public void gridState()
    {
        for (int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Game logic for horizontal number of tokens in a row
     * @param player Current player playing
     * @return A win or not
     */
    public int horizontal(String player)
    {
        int toWinCounter = 0;
        for (int row=0;row<rows;row++)
            for (int column = 0;column<columns;column++)
            {
                if (toWinCounter == toWin) return 1;
                if (grid[row][column] == player) toWinCounter++;
                else if (grid[row][column] != player) toWinCounter=0;
            }
        return 0;
    }

    /**
     * Game logic for vertical number of tokens in a row
     * @param player
     * @return A win or not
     */
    public int vertical(String player)
    {
        int toWinCounter = 0;
        for (int column=0;column<columns;column++)
            for (int row=0;row<rows;row++)
            {
                if (toWinCounter == toWin) return 1;
                if (grid[row][column] == player) toWinCounter++;
                else if (grid[row][column] != player) toWinCounter=0;
            }
        return 0;
    }

    /**
     * Game logic for a diagonal going from left to right for a number of tokens in a row
     * @param player Current player playing
     * @param row Current x co-ordinate of token
     * @param column Current y co-ordinate of token
     * @return A win or not
     */
    public int diagonal1(String player,int row,int column)
    {
        int toWinCounter = 1;
        int currentRow = row - 1;
        int currentColumn = column - 1;
        while (true)
        {
            if (toWinCounter == toWin) return 1;
            if ((currentRow >= rows || currentRow < 0)) break;
            if ((currentColumn >= columns || currentColumn < 0)) break;
            if (grid[currentRow][currentColumn] == player) toWinCounter++;
            currentColumn--; currentRow--;
        }
        return 0;
    }

    /**
     * Game logic for a diagonal going from right to left for a number of tokens in a row
     * @param player Current player playing
     * @param row Current x co-ordinate of token
     * @param column Current y co-ordinate of token
     * @return A win or not
     */
    public int diagonal2(String player,int row,int column)
    {
        int toWinCounter = 1;
        int currentRow = row - 1;
        int currentColumn = column + 1;
        while (true)
        {
            if (toWinCounter == toWin) return 1;
            if ((currentRow >= rows || currentRow < 0)) break;
            if ((currentColumn >= columns || currentColumn < 0)) break;
            if (grid[currentRow][currentColumn] == player) toWinCounter++;
            currentColumn++; currentRow--;
        }
        return 0;
    }

    /**
     * Game logic for diagonal number of tokens in a row
     * @param player
     * @return
     */
    public int diagonals(String player)
    {
        int value1;
        int value2;
        for (int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                value1 = diagonal1(player, i, j);
                value2 = diagonal2(player, i, j);

                if (value1 == 1) return 1;
                if (value2 == 1) return 1;
            }
        }
        return 0;
    }

    /**
     * Checks to see if all tokens have been entered and returns a draw if true
     * @return True or false
     */
    public boolean gameDraw()
    {

        for (int i=0;i<rows;i++)
        {
            for (int j=0;j<columns;j++)
            {
                if (grid[i][j].equals("-")) return false;
            }
            System.out.println();
        }
        return true;
    }
}