import java.util.*;

public class ConnectFour
{
    private String[][] grid;
    private int columns;
    private int rows;
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
        int toWin = input.nextInt();
        while (toWin >= columns)
        {
            System.out.println("Number cannot be equal to or greater than number of columns!");
            toWin = input.nextInt();
        }
    }

    public void runGame()
    {
        while (true)
        {
            inputPrompt(PLAYER1);
            inputPrompt(PLAYER2);
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
}