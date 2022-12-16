package othello;

import gameFramework.Board;
import gameFramework.Game;

public class FlipPiece {



    public static void upTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord-1 >= 0){
            if(board[xCoord][yCoord-1] == oppPiece){
                for (int i = yCoord - 1; i >= 0; i--) {
                    if (board[xCoord][i] == ' ' || board[xCoord][i] == myPiece){break;}
                    if (board[xCoord][i] != myPiece) {board[xCoord][i] = myPiece;}
                }
            }
        }

    }
    public static void leftTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        int index= 0;
        if(xCoord - 1 >= 0){
            if(board[xCoord-1][yCoord] == oppPiece){
                for (int i = xCoord - 1; i >= 0; i--) {
                    if (board[i][yCoord] == ' ' || board[i][yCoord] == myPiece){break;}
                    if (board[i][yCoord] != myPiece) {board[i][yCoord] = myPiece;}
                }
            }
        }

    }
    public static void rightTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(xCoord + 1 < 8){
            if (board[xCoord+1][yCoord] == oppPiece){
                for (int i = xCoord + 1; i < 8; i++) {
                    if (board[i][yCoord] == ' ' || board[i][yCoord] == myPiece){break;}
                    if (board[i][yCoord] != myPiece) {board[i][yCoord] = myPiece;}
                }
            }
        }
    }
    public static void downTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord+1 < 8){
            if(board[xCoord][yCoord+1] == oppPiece){
                for (int i = xCoord + 1; i < 8; i++) {
                    if (board[xCoord][i] == ' ' || board[xCoord][i] == myPiece){break;}
                    if (board[xCoord][i] != myPiece) {board[xCoord][i] = myPiece;}
                }
            }
        }
    }
    public static void diagLeftUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(xCoord-1 >= 0 && yCoord-1 >= 0){
            if(board[xCoord-1][yCoord-1] == oppPiece){
                for (int i = 1; i < Math.min(xCoord, yCoord); i++) {
                    if (board[xCoord - i][yCoord - i] == ' ' || board[xCoord - i][yCoord - i] == myPiece){break;}
                    if (board[xCoord - i][yCoord - i] != myPiece) {board[xCoord - i][yCoord - i] = myPiece;}
                }
            }
        }

    }
    public static void diagLeftDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord+1 < 8 && xCoord-1 >= 0){
            if(board[xCoord-1][yCoord+1] == oppPiece){
                for (int i = 1; i < Math.min(xCoord, 8 - yCoord); i++) {
                    if(board[xCoord-i][yCoord+i] == ' ' || board[xCoord-i][yCoord+i] == myPiece){break;}
                    if (board[xCoord-i][yCoord+i] != myPiece) {board[xCoord-i][yCoord+i] = myPiece;}
                }
            }
        }

    }
    public static void diagRightUpTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord-1 >= 0 && xCoord+1 < 8){
            if(board[xCoord+1][yCoord-1] == oppPiece){
                for (int i = 1; i < Math.min(8 - xCoord, yCoord); i++) {
                    if (board[xCoord+i][yCoord-i] == ' ' || board[xCoord+i][yCoord-i] == myPiece){break;}
                    if (board[xCoord+i][yCoord-i] != myPiece) {board[xCoord+i][yCoord-i] = myPiece;}
                }
            }
        }

    }
    public static void diagRightDownTraverse(char myPiece, char oppPiece, int xCoord, int yCoord, char[][] board){
        if(yCoord+1 < 8 && xCoord+1 < 8){
            if(board[xCoord+1][yCoord+1] == oppPiece){
                for (int i = 1; i < 8 - Math.max(xCoord, yCoord); i++) {
                    if (board[xCoord+i][yCoord+i] == ' ' || board[xCoord+i][yCoord+i] == myPiece){break;}
                    if (board[xCoord+i][yCoord+i] != myPiece) {board[xCoord+i][yCoord+i] = myPiece;}
                }
            }
        }

    }
}







