package othello;

/**
 * @author Ihab Al-Safadi
 */

public class FlipPiece {


    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi
     */
    public static void upTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int index = yCoord - 1;
                if(GameRules.checkOutOfTheRange(index)){
                while (board[xCoord][index] == oppPiece) {
                    board[xCoord][index] = myPiece;
                    index--;
            }
        }
    }

    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi
     */

    public static void leftTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {
        if(xCoord - 1 >= 0){
                int index = xCoord - 1;
                if(GameRules.checkOutOfTheRange(index))
                while (board[index][yCoord] == oppPiece){
                    board[index][yCoord] = myPiece;
                    index--;
                }
            }
        }



    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi
     */

    public static void rightTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {
                int index = xCoord +1;
                if(GameRules.checkOutOfTheRange(index)){
                while (board[index][yCoord] == oppPiece){
                    board[index][yCoord] = myPiece;
                    index++;
                }
        }
    }

    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi
     */
    public static void downTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {
                int index = yCoord +1;
                if(GameRules.checkOutOfTheRange(index)){
                while(board[xCoord][index] == oppPiece){
                    board[xCoord][index] = myPiece;
                    index++;
                }
            }
    }
    public static void diagLeftUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int index = 1;
                if(GameRules.checkOutOfTheRange(xCoord -index) && GameRules.checkOutOfTheRange(yCoord - index)){
                while(board[xCoord - index][yCoord - index] == oppPiece){
                    board[xCoord - index][yCoord - index] = myPiece;
                    index++;
            }
        }
    }

    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi
     */
    public static void diagLeftDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {
        int index = 1;
        if (GameRules.checkOutOfTheRange(xCoord - index) && GameRules.checkOutOfTheRange(yCoord + index)) {
            while (board[xCoord - index][yCoord + index] == oppPiece) {
                board[xCoord - index][yCoord + index] = myPiece;
                index++;
            }
        }
    }

    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi
     */
    public static void diagRightUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
                int index =1;
                if(GameRules.checkOutOfTheRange(xCoord + index) && GameRules.checkOutOfTheRange(yCoord-index)){
                while (board[xCoord+index][yCoord-index] == oppPiece){
                    board[xCoord+index][yCoord-index] = myPiece;
                    index++;
                }
        }

    }

    /**
     *
     * @param myPiece
     * @param oppPiece
     * @param xCoord
     * @param yCoord
     * @param board
     * @author Ihab Al-Safadi
     */
    public static void diagRightDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        int index=1;
        if(GameRules.checkOutOfTheRange(xCoord +index) && GameRules.checkOutOfTheRange(yCoord + index)){
                while(board[xCoord+index][yCoord+index] == oppPiece){
                    board[xCoord+index][yCoord+index] = myPiece;
                    index ++;
            }
        }
    }
}







