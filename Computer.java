package MyFrame;

/*
* Name: Kevin Zhu and Samuel Deng
* Date: June 13, 2019
* Course: ICS 3U7 - 02
* Teacher: Mr.Anthony
* Assignment: Computer Science Final Project (Connect 4)
 */
import java.util.*;
public class Computer {
//Variable Declarations
    private int computerCol = (int) (Math.random() * 7);
    private int blockRow;
    private int blockColumn;
    private Board board;
    private int emptyColumn;
    ArrayList<Integer> availableCols = new ArrayList<Integer>();
//Constructor Computer()
    public Computer(Board board) {
        this.board = board;
    }

    /*
	 * Method isColumnFull() 
	 * Pre: None 
	 * Post: Returns whether a column is full or not, so computer can choose another empty column
     */
    public boolean isColumnFull() {
        int emptyCount = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (board.grid[j][i].equals("-")) {
                    emptyCount++;
                    emptyColumn = j;
                }
                if (emptyCount == 0) {
                    return true;
                }
            }
            emptyCount = 0;
        }
        return false;
    }

    /*
	 * Method makeMove() 
	 * Pre: Must be the computer's turn to move 
	 * Post: Determines the best move for the computer based on different conditions
     */
    public int makeMove() {
        if (isColumnFull() == false) {
            if (blockPlayerColumn() == true) {// Block player's column
                computerCol = blockColumn();

            } else if (blockPlayerRow() == true) {// Block player's row
                computerCol = blockRow();

            } else if (isColumnFull() == false) {
                computerCol = column();
            } else {
                computerCol = newColumn();
            }
        } else if (isColumnFull() == true) {
            computerCol = newColumn();
        }
        return computerCol;
    }

    /*
	 * Method blockPlayerColumn() 
	 * Pre: none
	 * Post: returns whether it is necessary for the computer to block a column to prevent a connect four.
     */
    public boolean blockPlayerColumn() {
        int p1Counter = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (board.grid[j][i].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 3) {
                        if (j > 3 && board.grid[j - 4][i].equals("C") == false) {
                            return true;
                        } else {
                            p1Counter = 0;
                        }
                    }
                }

            }

            p1Counter = 0;
        }
        return false;
    }

    /*
	 * Method blockColumn() 
	 * Pre: The gameboard has already been filled with some pieces
	 * Post: returns which column the computer should place their piece in to block the potential connect four
     */
    public int blockColumn() {
        int columnCount = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (board.grid[j][i].equals("1")) {
                    columnCount++;
                    if (columnCount == 3) {// Computer needs to block
                        blockColumn = i;
                        return blockColumn;
                    }
                } else {
                    columnCount = 0;
                }
            }
            columnCount = 0;
        }
        return blockColumn;
    }

    /*
	 * Method blockPlayerRow() 
	 * Pre: none
	 * Post: returns whether it is necessary for the computer to block a row to prevent connect four
     */
    public boolean blockPlayerRow() {
        int p1Counter = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.grid[i][j].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 3) {
                        return true;
                    }
                } else {
                    p1Counter = 0;
                }
            }
            p1Counter = 0;
        }
        return false;
    }

    /*
	 * Method blockRow() 
	 * Pre: The gameboard has already been filled with some pieces
	 * Post: returns which row the computer should place their piece in to block potential connect four
     */
    public int blockRow() {
        int rowCount = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.grid[i][j].equals("1")) {
                    rowCount++;
                    if (rowCount == 3 && j < 6) {// Computer needs to block to the right
                        blockRow = j + 1;
                        return blockRow;
                    } else if (rowCount == 3 && j == 6) {// Computer needs to block to the left
                        blockRow = j - 1;
                        return blockRow;
                    }
                } else {
                    rowCount = 0;
                }
            }
        }

        return blockRow;
    }

    /*
	 * Method column() 
	 * Pre: None
	 * Post: returns which column the computer should place their piece in to make a connect 4
     */
    private int column() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board.grid[i][j] == "C" && board.access(j).equals("-")) {
                    computerCol = j;
                }
                if (j > 6) {
                    computerCol = j + 1;
                }
                if (i == 6 && j == 7) {
                    newColumn();
                }
            }
        }
        return computerCol;
    }

    /*
	 * Method newColumn() 
	 * Pre: Computer needs to place pieces in a new column
	 * Post: generates a new column for the computer to place their piece in
     */
    	public int newColumn() {
    
    		if (computerCol >= 0 && computerCol < 6) {
    			computerCol = computerCol + 1;
    		} else if (computerCol == 6) {
    			computerCol = emptyColumn;
    		}
    		return computerCol;
    	}

}
