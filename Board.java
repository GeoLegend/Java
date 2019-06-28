
package MyFrame;

/*
* Name: Kevin Zhu and Samuel Deng
* Date: June 13, 2019
* Course: ICS 3U7 - 02
* Teacher: Mr.Anthony
* Assignment: Computer Science Final Project (Connect 4)
*/

public class Board {
    //Variable Declarations
    public String[][] grid = new String[6][7];

    //Constructor Board()
    public Board() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = "-";
            }
        }
    }

    /*
    * Method addPiece(String player, int column)
    * Pre: Pre conditions include the player's name as well as the column they chose to drop his or her token.
    * Post: Sets grid[i][column] to the player's name, which is where they will place his or her token.
     */
    public void addPiece(String player, int column) {
        boolean validPlay = false;
        for (int i = 5; i >= 0 && validPlay == false; i--) {
            if (grid[i][column].equals("-")) {
                grid[i][column] = player;
                validPlay = true;
            }
        }
    }

    /*
    * Method toString()
    * Pre: None.
    * Post: Returns a String called theBoard, which has the game pieces in play.
     */
    public String toString() {
        String theBoard = "";
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                theBoard += "| " + grid[i][j] + " ";
            }
            theBoard += "|\n";
        }
        return theBoard;
    }

    /*
    * Method access(int column)
    * Pre: Pre condition includes the column number.
    * Post: Returns the player's name at grid[0][column]
     */
    public String access(int column) {
        return grid[0][column];
    }

    /*
    * Method checkRowWinner()
    * Pre: None.
    * Post: Returns the winner for a row, if applicable.
     */
    public int checkRowWinner() {
        int p1Counter = 0;
        int p2Counter = 0;
        int compCounter = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (grid[i][j].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 4) {
                        return 1; //Player 1 wins.
                    }
                } else {
                    p1Counter = 0;
                }
            }
            p1Counter = 0;
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (grid[i][j].equals("2")) {
                    p2Counter++;
                    if (p2Counter == 4) {
                        return 2; //Player 2 wins.
                    }
                } else {
                    p2Counter = 0;
                }
            }
            p2Counter = 0;
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (grid[i][j].equals("C")) {
                    compCounter++;
                    if (compCounter == 4) {
                        return 3; //computer wins.
                    }
                } else {
                    compCounter = 0;
                }
            }
            compCounter = 0;
        }
        return 0; //No one wins.
    }

    /*
    * Method checkColumnWinner()
    * Pre: None.
    * Post: Returns the winner for a column, if applicable.
     */
    public int checkColumnWinner() {
        int p1Counter = 0;
        int p2Counter = 0;
        int compCounter = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (grid[j][i].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 4) {
                        return 1; //Player 1 wins.
                    }
                } else {
                    p1Counter = 0;
                }
            }
            p1Counter = 0;
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (grid[j][i].equals("2")) {
                    p2Counter++;
                    if (p2Counter == 4) {
                        return 2; //Player 2 wins.
                    }
                } else {
                    p2Counter = 0;
                }
            }
            p2Counter = 0;
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (grid[j][i].equals("C")) {
                    compCounter++;
                    if (compCounter == 4) {
                        return 3; //computer wins.
                    }
                } else {
                    compCounter = 0;
                }
            }
            compCounter = 0;
        }
        return 0; //No one wins.
    }

    /*
    * Method checkTopLeftToBottomRightDiagonals()
    * Pre: None.
    * Post: Returns the winner for a diagonal which goes from top left to bottom right, if applicable.
     */
    public int checkTopLeftToBottomRightDiagonals() {
        int p1Counter = 0;
        int p2Counter = 0;
        int compCounter = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6 - i; j++) {
                if (grid[j + i][j].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 4) {
                        return 1; //Player 1 wins.
                    }
                } else {
                    p1Counter = 0;
                }
            }
            p1Counter = 0;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6 - i; j++) {
                if (grid[j + i][j].equals("2")) {
                    p2Counter++;
                    if (p2Counter == 4) {
                        return 2; //Player 2 wins.
                    }
                } else {
                    p2Counter = 0;
                }
            }
            p2Counter = 0;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6 - i; j++) {
                if (grid[j + i][j].equals("C")) {
                    compCounter++;
                    if (compCounter == 4) {
                        return 3; //computer wins.
                    }
                } else {
                    compCounter = 0;
                }
            }
            compCounter = 0;
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 7 - i; j++) {
                if (grid[j][j + i].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 4) {
                        return 1; //Player 1 wins.
                    }
                } else {
                    p1Counter = 0;
                }
            }
            p1Counter = 0;
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 7 - i; j++) {
                if (grid[j][j + i].equals("2")) {
                    p2Counter++;
                    if (p2Counter == 4) {
                        return 2; //Player 2 wins.
                    }
                } else {
                    p2Counter = 0;
                }
            }
            p2Counter = 0;
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 7 - i; j++) {
                if (grid[j][i + j].equals("C")) {
                    compCounter++;
                    if (compCounter == 4) {
                        return 3; //computer wins.
                    }
                } else {
                    compCounter = 0;
                }
            }
            compCounter = 0;
        }
        return 0; //No one wins.
    }

    /*
    * Method checkTopRightToBottomLeftDiagonals()
    * Pre: None.
    * Post: Returns the winner for a diagonal which goes from top right to bottom left, if applicable.
     */
    public int checkTopRightToBottomLeftDiagonals() {
        int p1Counter = 0;
        int p2Counter = 0;
        int compCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > i; j--) {
                if (grid[6 - j + i][j].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 4) {
                        return 1; //Player 1 wins.
                    }
                } else {
                    p1Counter = 0;
                }
            }
            p1Counter = 0;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > i; j--) {
                if (grid[6 - j + i][j].equals("2")) {
                    p2Counter++;
                    if (p2Counter == 4) {
                        return 2; //Player 2 wins.
                    }
                } else {
                    p2Counter = 0;
                }
            }
            p2Counter = 0;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > i; j--) {
                if (grid[6 - j + i][j].equals("C")) {
                    compCounter++;
                    if (compCounter == 4) {
                        return 3; //computer wins.
                    }
                } else {
                    compCounter = 0;
                }
            }
            compCounter = 0;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 5; j > i; j--) {
                if (grid[5 - j][j - i].equals("1")) {
                    p1Counter++;
                    if (p1Counter == 4) {
                        return 1; //Player 1 wins.
                    }
                } else {
                    p1Counter = 0;
                }
            }
            p1Counter = 0;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 5; j > i; j--) {
                if (grid[5 - j][j - i].equals("2")) {
                    p2Counter++;
                    if (p2Counter == 4) {
                        return 2; //Player 2 wins.
                    }
                } else {
                    p2Counter = 0;
                }
            }
            p2Counter = 0;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 5; j > i; j--) {
                if (grid[5 - j][j - i].equals("C")) {
                    compCounter++;
                    if (compCounter == 4) {
                        return 3; //computer wins.
                    }
                } else {
                    compCounter = 0;
                }
            }
            compCounter = 0;
        }
        return 0; //No one wins.
    }
}