package othello;

/**
 * Static methods for traversing in all 8 directions.
 * Can be used for allowMoveOthello() and flipPiece()
 * @author Aaldert Kroes, Ihab Al-Safadi
 * @version 0.1
 */

public class GameRules {

    public static boolean leftTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {

        if(xCoord - 1 >= 0){
            if(board[xCoord-1][yCoord] == oppPiece){
                for (int i = xCoord - 1; i >= 0; i--) {
                    if (board[i][yCoord] == ' '){break;}
                    if (board[i][yCoord] == myPiece) {return true;}
                }
            }
        }
        return false;
    }

    public static boolean rightTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board) {

        if(xCoord + 1 < 8){
            if (board[xCoord+1][yCoord] == oppPiece){
                for (int i = xCoord + 1; i < 8; i++) {
                    if (board[i][yCoord] == ' '){break;}
                    if (board[i][yCoord] == myPiece) {return true;}
                }
            }
        }
        return false;
    }
    public static boolean upTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord-1 >= 0){
            if(board[xCoord][yCoord-1] == oppPiece){
                for (int i = yCoord - 1; i >= 0; i--) {
                    if (board[xCoord][i] == ' '){break;}
                    if (board[xCoord][i] == myPiece) {return true;}
                }
            }
        }
        return false;
    }
    public static boolean downTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord+1 < 8){
            if(board[xCoord][yCoord+1] == oppPiece){
                for (int i = xCoord + 1; i < 8; i++) {
                    if (board[xCoord][i] == ' '){break;}
                    if (board[xCoord][i] == myPiece) {return true;}
                }
            }
        }
        return false;
    }
    public static boolean diagLeftUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(xCoord-1 >= 0 && yCoord-1 >= 0){
            if(board[xCoord-1][yCoord-1] == oppPiece){
                for (int i = 1; i < Math.min(xCoord, yCoord); i++) {
                    if (board[xCoord - i][yCoord - i] == ' '){break;}
                    if (board[xCoord - i][yCoord - i] == myPiece) {return true;}
                }
            }
        }
        return false;
    }
    public static boolean diagLeftDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord+1 < 8 && xCoord-1 >= 0){
            if(board[xCoord-1][yCoord+1] == oppPiece){
                for (int i = 1; i < Math.min(xCoord, 8 - yCoord); i++) {
                    if(board[xCoord-i][yCoord+i] == ' '){break;}
                    if (board[xCoord-i][yCoord+i] == myPiece) {return true;}
                }
            }
        }
        return false;
    }
    public static boolean diagRightUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord-1 >= 0 && xCoord+1 < 8){
            if(board[xCoord+1][yCoord-1] == oppPiece){
                for (int i = 1; i < Math.min(8 - xCoord, yCoord); i++) {
                    if (board[xCoord+i][yCoord-i] == ' '){break;}
                    if (board[xCoord+i][yCoord-i] == myPiece) {return true;}
                }
            }
        }
        return false;
    }
    public static boolean diagRightDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord+1 < 8 && xCoord+1 < 8){
            if(board[xCoord+1][yCoord+1] == oppPiece){
                for (int i = 1; i < 8 - Math.max(xCoord, yCoord); i++) {
                    if (board[xCoord+i][yCoord+i] == ' '){break;}
                    if (board[xCoord+i][yCoord+i] == myPiece) {return true;}
                }
            }
        }
        return false;
    }
}
