package gameFramework;


/**
 * @author Ihab Al-Safadi
 */

abstract public class Game extends Board{


    public static int width;
    public static int height;
    public static char piece;

    public Game(){}

    public Game(int width, int height, char piece) {
        super(width, height);
        Game.piece = piece;
        Game.width = width;
        Game.height = height;
        Gui.gameScreen(width, height);
    }



    /**
     *
     * @param teken
     * @param position
     * @author Ihab Al-Safadi
     */
    public void add(char teken, int position){
        /**
         * voeg een zet toe aan het baord
         */
        Coordinates.XAndYCoordinates(position);
        if(getBoard()[Coordinates.getX()][Coordinates.getY()] ==' '){getBoard()[Coordinates.getX()][Coordinates.getY()] = teken;}
        else {System.out.println("Deze plek is niet beschikbaar");}
    }

    /**
     * Removes a move from the board.
     */
    public void remove(char teken, int position) {
            Coordinates.XAndYCoordinates(position);
        if (getBoard()[Coordinates.getX()][Coordinates.getY()] != ' ') {
            getBoard()[Coordinates.getX()][Coordinates.getY()] = ' ';
        } else {
            System.out.println("Deze plek is niet beschikbaar");
        }
    }
    
    abstract public void enableButtons(char piece); // enable buttons for the player

    abstract public void moveAI(char piece, int playernumber); // move the AI
    
    abstract public void serverAdd(int position, char piece); // add a move to the server

    abstract public char oppPiece(char piece); // get the opponent piece
}
