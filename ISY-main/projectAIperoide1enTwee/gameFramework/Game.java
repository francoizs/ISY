package gameFramework;


/**
 * @author Ihab Al-Safadi
 */

abstract public class Game extends Board{
    public Game(int size) {
        super(size);
    }
    
    public static String gameName;
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

    public static int[] coordinate(int position){
        /**
         * // Deze methode neemt de position die de speler wil doen, en geeft de coordinates op het board terug.
         * Vervolgens  worden de coordinates gestuurd naar de add methode om een zet te doen op het board
         */
        int counter =  -1;
        int[] coordinate= {0,0};
        for (int row=0; row< Game.height; row++){
            for (int col = 0; col< Game.width; col++){
                counter ++;
                if (counter == position){coordinate[0] =row; coordinate[1] = col;break;}
            }
        }

        return coordinate;
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
        int[] coordinates = coordinate(position);
        if(getBoard()[coordinates[0]][coordinates[1]] ==' '){getBoard()[coordinates[0]][coordinates[1]] = teken;}
        else {System.out.println("Deze plek is niet beschikbaar");}
    }

    /**
     * Removes a move from the board.
     */
    public void remove(char teken, int position) {
        int[] coordinates = coordinate(position);
        if (getBoard()[coordinates[0]][coordinates[1]] != ' ') {
            getBoard()[coordinates[0]][coordinates[1]] = ' ';
        } else {
            System.out.println("Deze plek is niet beschikbaar");
        }
    }
    
    abstract public void enableButtons(char piece); // enable buttons for the player

    abstract public void moveAI(char piece, int playernumber); // move the AI
    
    abstract public void serverAdd(int position, char piece); // add a move to the server

    abstract public char oppPiece(char piece); // get the opponent piece
}
