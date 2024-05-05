import java.util.*;

public class ConnectFour
{
    private String[][] grid;
    private int columns;
    private int rows;
    private int toWin;
    private final String PLAYER1 = "X";
    private final String PLAYER2 = "O";
    private Scanner input = new Scanner(System.in);

    public ConnectFour()
    {
        System.out.println("Enter the corresponding number to get the size of the grid. \n 1: 7x6 2: 8x7 3: 9x7 4: 10x7");
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
                System.out.println("Invalid number entered!");
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

    public void runGame()
    {
        int winnerAcross;
        int winnerVertical;
        while (true)
        {
            inputPrompt(PLAYER1);
            winnerAcross = horizontal(PLAYER1);
            winnerVertical = vertical(PLAYER1);
            if (winnerAcross == 1 || winnerVertical == 1 ) {System.out.println("Player 1 has won!"); break;}
            inputPrompt(PLAYER2);
            winnerAcross = horizontal(PLAYER2);
            winnerVertical = vertical(PLAYER2);
            if (winnerAcross == 1 || winnerVertical == 1 ) {System.out.println("Player 2 has won!"); break;}
        }
        
    }

    public void inputPrompt(String player)
    {
        String turn;
        if (player.equals(PLAYER1)) turn = "( PLAYER 1 )";
        else turn = "( PLAYER 2 )";
        System.out.println(turn + " Choose the column to enter your token: 0-" + (columns-1) +"!");
        int col = input.nextInt();
        if (col < columns)
        {
            addToken(col, player);
        }
        else System.out.println("Invalid input!");
    }

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

    public void addToken(int col,String player)
    {
        int row = rows - 1;
        boolean loop = true;
        while (loop)
        {
            if (grid[row][col].equals("-"))
            {
                grid[row][col] = player;
                loop = false;
            }
            row--;
        }
        gridState();
    }

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
}