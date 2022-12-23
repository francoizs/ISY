package othello;

/**
 * Static methods for traversing in all 8 directions.
 * Can be used for allowMoveOthello() and flipPiece()
 * @author Aaldert Kroes, Ihab Al-Safadi
 * @version 0.1
 */

public class GameRules {



    /**
     *
     * @param coord
     * @return
     */
    public static boolean checkOutOfTheRange(int coord){
        if(coord  < 8 && coord  >= 0 ){return true;}
        else {return false;}
    }




    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */




    public static boolean leftTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {
                int i = xCoord -1;
                if(checkOutOfTheRange(i) && board[i][yCoord] == oppPiece){
                while (checkOutOfTheRange(i) && board[i][yCoord] == oppPiece){
                    i--;
                }
                if(checkOutOfTheRange(i))
                    return board[i][yCoord] == myPiece;}

        return false;
    }




    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */
    public static boolean rightTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {
                int i = xCoord +1;
                if(checkOutOfTheRange(i) && board[i][yCoord] == oppPiece){
                while (checkOutOfTheRange(i) && board[i][yCoord] == oppPiece){
                    i++;
                }
                if(checkOutOfTheRange(i))
                    return board[i][yCoord] == myPiece;}

        return false;
    }




    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */
    public static boolean upTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int i = yCoord - 1;
                if(checkOutOfTheRange(i) && board[xCoord][i] == oppPiece){
                while (checkOutOfTheRange(i) && board[xCoord][i] == oppPiece){
                    i--;
                }
                if(checkOutOfTheRange(i))
                    return board[xCoord][i] == myPiece;}

        return false;
    }




    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */
    public static boolean downTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int i = yCoord + 1;
                if(checkOutOfTheRange(i) && board[xCoord][i] == oppPiece){
                while (checkOutOfTheRange(i) && board[xCoord][i] == oppPiece){
                    i++;
                }
                if(checkOutOfTheRange(i))
                    return board[xCoord][i] == myPiece;}

        return false;
    }



    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */
    public static boolean diagLeftUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int i = 1;
                if(checkOutOfTheRange(xCoord - i) && checkOutOfTheRange(yCoord - i) && board[xCoord - i][yCoord - i] == oppPiece){
                while(checkOutOfTheRange(xCoord - i) && checkOutOfTheRange(yCoord - i) && board[xCoord - i][yCoord - i] == oppPiece){
                    i++;
                }
                if(checkOutOfTheRange(xCoord - i) && checkOutOfTheRange(yCoord - i))
                   return board[xCoord - i][yCoord - i] == myPiece;}


        return false;
    }




    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */
    public static boolean diagLeftDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int i = 1;
                if(checkOutOfTheRange(xCoord-i) && checkOutOfTheRange(yCoord+i)&& board[xCoord-i][yCoord+i] == oppPiece){
                while(checkOutOfTheRange(xCoord-i) && checkOutOfTheRange(yCoord+i)&& board[xCoord-i][yCoord+i] == oppPiece){
                    i++;
                }
                if(checkOutOfTheRange(xCoord-i) && checkOutOfTheRange(yCoord+i))
                   return board[xCoord-i][yCoord+i] == myPiece;}

        return false;
    }




    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */
    public static boolean diagRightUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int i = 1;
                if(checkOutOfTheRange(xCoord+i) && checkOutOfTheRange(yCoord -i) && board[xCoord+i][yCoord-i] == oppPiece){
                while (checkOutOfTheRange(xCoord+i) && checkOutOfTheRange(yCoord -i) && board[xCoord+i][yCoord-i] == oppPiece){
                    i++;
                }
                if(checkOutOfTheRange(xCoord+i) && checkOutOfTheRange(yCoord -i))
                   return board[xCoord+i][yCoord-i] == myPiece;}

        return false;
    }




    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @return
     */
    public static boolean diagRightDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int i = 1;
                if(checkOutOfTheRange(xCoord+i) && checkOutOfTheRange(yCoord+i) &&board[xCoord+i][yCoord+i] == oppPiece){
                while (checkOutOfTheRange(xCoord+i) && checkOutOfTheRange(yCoord+i) &&board[xCoord+i][yCoord+i] == oppPiece){
                    i++;
                }
                if(checkOutOfTheRange(xCoord+i) && checkOutOfTheRange(yCoord+i))
                    return board[xCoord+i][yCoord+i] == myPiece;}
        return false;
    }
}
