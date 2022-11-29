package gameFramework;

/**
 * @author Ihab Al-Safadi
 */

public class Game extends Board{
    public Game(int size) {
        super(size);
    }

    public Game(int width, int height) {
        super(width, height);
    }

    public int[] coordinate(int position){
        /**
         * // Deze methode neemt de position die de speler wil doen, en geeft de coordinates op het board terug.
         * Vervolgens  worden de coordinates gestuurd naar de add methode om een zet te doen op het board
         */
        int counter =  0;
        int[] coordinate= {0,0};
        for (int row=0; row< getHeight(); row++){
            for (int col = 0; col< getWidth(); col++){
                counter ++;
                if (counter == position){coordinate[0] =row; coordinate[1] = col;break;}
            }
        }

        return coordinate;
    }

    public boolean allowMove(int position){

        int[] check_coordinate = coordinate(position); // retrieve the coordinate
        if(getBoard()[check_coordinate[0]][check_coordinate[1]] != ' '){return false;}
        return true;
    }

    /**
     * Checks whether the move is allowed with the Othello rules, meaning:
     * Space must be empty, this position will enclose at least one opposing piece and the position isn't out of bounds.
     * @param position
     * @return boolean if move is allowed
     * @author Aaldert Kroes
     */
    public boolean allowMoveOthello(int position) {
        int[] coordinateMove = coordinate(position);
        boolean freeSpace = getBoard()[coordinateMove[0]][coordinateMove[1]] != ' ';    // filled space
        boolean inBounds = position > 0 && position < 65;                               // in bounds
        // check for 8 adjacent tiles if filled
        // check for if it captures at least one opposing tile

        return freeSpace && inBounds; // add additional booleans
    }

    public boolean winOthello(char piece) {
        int pieceCount = 0;
        int oppPieceCount = 0;
        // check of bord vol is
        // if(board.isFull()){
            for (int row = 0; row < getHeight(); row++) {
                for (int col = 0; col < getWidth(); col++) {
                    if(getBoard()[row][col] == piece){pieceCount++;} else {oppPieceCount++;}
                }
            }
        //}
        return pieceCount > oppPieceCount;
    }

    /**
     *
     * @param piecs
     * @return
     * @authur Ihab Al-Safadi
     */

    public boolean win(char piecs){
        return winHorizontal(piecs) || winvertical(piecs) || winDiagonalLeft(piecs) || winDiagonalRight(piecs);
    }

    /**
     *
     * @param piecs // Dit wordt toegevoergd vanuit de game class bv X en O.
     * @return
     * @authur Ihab Al-Safadi
     */

    public Boolean winvertical(char piecs){

        for(int row = 0; row < getHeight()-2; row++){
            for(int col = 0; col < getWidth(); col++){
                if(getBoard()[row][col] == piecs
                        && getBoard()[row+1][col] == piecs
                        && getBoard()[row+2][col] == piecs){ return true;}
            }
        }
        return false;
    }

    /***
     *
     * @param piecs
     * @return
     * @authur Ihab Al-Safadi
     */
    public Boolean winHorizontal(char piecs){

        for(int row = 0; row < getHeight(); row++){
            for(int col = 0; col < getWidth()-2; col++){
                if(getBoard()[row][col] == piecs &&
                        getBoard()[row][col+1] == piecs &&
                        getBoard()[row][col+2] == piecs){return true;}
            }
        }
        return false;
    }

    /***
     *
     * @param piecs
     * @return
     * @authur Ihab Al-Safadi
     */
    public Boolean winDiagonalLeft(char piecs){

        for(int row = 0; row < getHeight()-2; row++){
            for(int col = 0; col < getWidth()-2; col++){
                if(getBoard()[row][col] == piecs &&
                        getBoard()[row+1][col+1] == piecs &&
                        getBoard()[row+2][col+2] == piecs){return true;}
            }

        }
        return false;
    }

    /***
     * @authur Ihab Al-Safadi
     * @param piecs
     * @return
     */

    public Boolean winDiagonalRight(char piecs){

        for(int row = 0; row < getHeight()-2; row++){
            for(int col = 0; col < getWidth()-2; col++){
                if(getBoard()[row][getWidth()-1] == piecs &&
                        getBoard()[row+1][getWidth() -2] == piecs &&
                        getBoard()[row+2][getWidth() -3] == piecs){return true;}
            }
        }
        return false;
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
    public void remove(char teken, int position){
        int[] coordinates = coordinate(position);
        if(getBoard()[coordinates[0]][coordinates[1]] !=' '){getBoard()[coordinates[0]][coordinates[1]] = ' ';}
        else {System.out.println("Deze plek is niet beschikbaar");}
    }
}
