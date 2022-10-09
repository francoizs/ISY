import java.util.ArrayList;
import java.util.Arrays;

public class Board{

    private char board[][] ; // char datatype gebruikt omdat de speler zal  alleen maar 1 char gebruiken, namelijk 'X' of 'O'
    private int width; // width van het board.
    private int height; // height van het board.

    /**
     *
     * @param width
     * @param height
     * @author Ihab Al-Safadi
     */

    public Board(int width,int height){

        this.width = width; // initialiseer de width instance
        this.height = height;//initialiseer de height instance
        board = new char[height][width];
        for( int row = 0; row< height; row++){
            for (int col = 0; col < width; col++){
                board[row][col] = ' ';

            }

        }
    }

    /**
     *
     * @return
     * @authur Ihab Al-Safadi
     */

    public  String toString(){
        /**
        //Representation of the board, bij deze methode wordt de inhoud van de Board object omgezet naar String datatype.
         Bovendien maakt deze methode de grens voor de board.
         */

        StringBuilder grens = new StringBuilder() ;
        String repeated_top = new String("_").repeat(2 * this.width+1);
        grens.append(repeated_top);
        grens.append("\n");


        for( int row = 0; row< this.height; row++){
            grens.append("|");
            for (int col = 0; col < this.width; col++){
                grens.append(board[row][col]);
                grens.append( "|");

            }
            grens.append("\n"); // elke array komt op een eigen lijn.
        }
        String repeated_down = new String("_").repeat(2 * this.width+1); // grens onder voor het board
        grens.append(repeated_down);
        return grens.toString(); //convert s van String builder naar String en return.

    }



    /**
     * Clears the board without needing a new board.
     */
    public char[][] clear(char board[][]) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
        return board;
    }

    /**
     * Adds a move to the board.
     */

    public void add(){

    }

    /**
     * Removes a move from the board.
     */
    public void remove(){

    }

    /**
     * Checks whether the board is full or not.
     * @return true or false
     */
    public boolean isFull(){
        boolean placeHolder = false;
        return placeHolder;
    }
}
