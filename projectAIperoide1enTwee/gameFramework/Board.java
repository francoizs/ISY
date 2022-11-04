package gameFramework;

/**
 * Het spelbord en alles wat daarmee gebeurt
 * @version 0.2
 */
public class Board{

    protected char board[][] ; // char datatype gebruikt omdat de speler zal  alleen maar 1 char gebruiken, namelijk 'X' of 'O'
    protected int width; // width van het board.
    protected int height; // height van het board.

    public static int movesCounter = 0; // counter voor het aantal zetten dat gedaan is.

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
     * Geef de breedte van het bord
     * @return width
     * @author Aaldert Kroes
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Geef de hoogte van het bord
     * @return height
     * @author Aaldert Kroes
     */
    public int getHeight(){
        return this.height;
    }

    /**
     *
     * @return
     * @author Ihab Al-Safadi
     */
    @Override
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
     * @author Francois Dieleman
     */

    public void clear() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
    }

    /**
     * Checks whether the board is full or not.
     * @return true or false
     * @author Ihab Al-Safadi
     */
    public boolean isFull(){
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if(board[row][col] == ' ') {return false;}
            }
        }
        return true;
    }

    /**
     * Checks whether the board is completely empty or not.
     * @return true or false
     * @author Aaldert Kroes
     */
    public int isEmpty(){
        int empty = 0;
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if(board[row][col] != ' ') {empty += 1;}
            }
        }
        return empty;
    }
}
