/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyFrame;

/*
* Name: Kevin Zhu and Samuel Deng
* Date: June 13, 2019
* Course: ICS 3U7 - 02
* Teacher: Mr.Anthony
* Assignment: Computer Science Final Project (Connect 4)
*/

public class Game {
//Variable declarations
    private Player p1;
    private Player p2;
    private Computer comp;
    protected Board board;
    protected boolean p1Turn;
    
    //Constructor Game()
    public Game() {
        p1 = new Player();
        p2 = new Player();
        board = new Board();
        comp = new Computer(board);
        p1Turn = true;
    }
    
    /*
    *Method makeMove()
    *Pre: Pre condition includes whether or not it is player 1's turn or not. 
    *Post: Prints out the player's move as well as the board for each turn.
    */
    public void makeMove(int xCoord, int yCoord) {
        int column = -1;

        if (p1Turn == true) {
            column = p1.makeMove(xCoord, yCoord);
            if (board.access(column).equals("-")) {
                board.addPiece("1", column);
                p1Turn = !p1Turn;
            } 
        }
        else {
            column = p2.makeMove(xCoord, yCoord);
            if (board.access(column).equals("-")) {
                board.addPiece("2", column);
                p1Turn = !p1Turn;
            } 
        }
        

    }
    /*
	 * Method makeMove() Pre: Pre condition includes whether or not it is player 1's
	 * turn or not. Post: Prints out the player's move as well as the board for each
	 * turn.
	 */
	public void makeMoveVsComputer(int xCoord, int yCoord) {
		int column = -1;

		if (p1Turn == true) {
			column = p1.makeMove(xCoord, yCoord);
			if (board.access(column).equals("-")) {
				board.addPiece("1", column);
				p1Turn = !p1Turn;
			}
		} else {
			column = comp.makeMove();
			if (board.access(column).equals("-")) {
				board.addPiece("C", column);
				p1Turn = !p1Turn;
			}
		}
	}
    /*
    * Method checkWinners()
    * Pre: None.
    * Post: Returns the winner, if applicable. 
    */
    public int checkWinners() {
        int columnWinner, rowWinner, diagonalWinner, diagonalWinner2; 
        columnWinner = board.checkColumnWinner();
        if (columnWinner != 0) {
            return columnWinner;
        }
        rowWinner = board.checkRowWinner();
        if (rowWinner != 0) {
            return rowWinner;
        }
        diagonalWinner = board.checkTopLeftToBottomRightDiagonals();
        if (diagonalWinner != 0) {
            return diagonalWinner;
        }
        diagonalWinner2 = board.checkTopRightToBottomLeftDiagonals();
        if (diagonalWinner2 != 0) {
            return diagonalWinner2;
        }

        return 0;

    }

}
