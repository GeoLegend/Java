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


public class Player {    
    //Constructor Player
    public Player() {
    }
    
    /*
    * Method makeMove()
    * Pre: The user input for the column.
    * Post: Returns the column number which the player chooses to drop his or her token. 
    */
    public int makeMove(int xCoord, int yCoord) {
        int column = -1;

        if (xCoord >= 110 && xCoord <= 810 && yCoord >= 130 && yCoord <= 730){
            column = (int)(Math.floor((xCoord - 110)/100));
        }
        return column;
    }
    
    
}
    
   