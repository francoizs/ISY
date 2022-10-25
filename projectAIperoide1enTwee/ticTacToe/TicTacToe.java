package ticTacToe;
import gameFramework.Board;

public class TicTacToe extends Board{

    public TicTacToe(int width, int height){
        super(width, height);

    }



    /**
     * Adds a move to the board.
     * @authur Ihab Al-Safadi
     */

    public int[] coordinate(int position){
        /**
         * // Deze methode neemt de position die de speler wil doen, en geeft de coordinates op het board terug.
         * Vervolgens  worden de coordinates gestuurd naar de add methode om een zet te doen op het board
         */
        int counter =  0;
        int[] coordinate= {0,0};
        for (int row=0; row< this.height; row++){
            for (int col = 0; col< this.width; col++){
                counter ++;
                if (counter == position){coordinate[0] =row; coordinate[1] = col;break;}
            }
        }

        return coordinate;
    }
    /**
     *
     * @param position
     * @return
     * @authur Ihab Al-Safadi
     */

    public boolean allowMove(int position){

        int[] check_coordinate = coordinate(position); // retrieve the coordinate
        if(board[check_coordinate[0]][check_coordinate[1]] != ' '){return false;}
        return true;
    }




    /**
     *
     * @param piecs
     * @return
     * @authur Ihab Al-Safadi
     */

    public Boolean win(char piecs){
        return winHorizontal(piecs) || winvertical(piecs) || winDiagonalLeft(piecs) || winDiagonalRight(piecs);
    }

    /**
     *
     * @param piecs // Dit wordt toegevoergd vanuit de game class bv X en O.
     * @return
     * @authur Ihab Al-Safadi
     */

    public Boolean winvertical(char piecs){

        for(int row = 0; row < this.height-2; row++){
            for(int col = 0; col < this.width; col++){
                if(board[row][col] == piecs
                        && board[row+1][col] == piecs
                        && board[row+2][col] == piecs){ return true;}
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

        for(int row = 0; row < this.height; row++){
            for(int col = 0; col < this.width-2; col++){
                if(board[row][col] == piecs &&
                        board[row][col+1] == piecs &&
                        board[row][col+2] == piecs){return true;}
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

        for(int row = 0; row < this.height-2; row++){
            for(int col = 0; col < this.width-2; col++){
                if(board[row][col] == piecs &&
                        board[row+1][col+1] == piecs &&
                        board[row+2][col+2] == piecs){return true;}
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

        for(int row = 0; row < this.height-2; row++){
            for(int col = 0; col < this.width-2; col++){
                if(board[row][this.width-1] == piecs &&
                        board[row+1][this.width -2] == piecs &&
                        board[row+2][this.width -3] == piecs){return true;}
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
        if(board[coordinates[0]][coordinates[1]] ==' '){board[coordinates[0]][coordinates[1]] = teken;}
        else {System.out.println("Deze plek is niet beschikbaar");}
    }

    /**
     * Removes a move from the board.
     */
    public void remove(char teken, int position){
        int[] coordinates = coordinate(position);
        if(board[coordinates[0]][coordinates[1]] !=' '){board[coordinates[0]][coordinates[1]] = ' ';}
        else {System.out.println("Deze plek is niet beschikbaar");}
    }
}
