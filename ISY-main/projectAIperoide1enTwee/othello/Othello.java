package othello;

import gameFramework.Game;
import java.lang.Math;

/**
 * @author Ihab Al-Safadi
 */
public class Othello extends Game {

    public Othello(int size) {
        super(size);
    }

    public Othello(int width, int height) {
        super(width, height);
    }

    /**
     * Checks whether the move is allowed with the Othello rules, meaning:
     * Space must be empty, this position will enclose at least one opposing piece and the position isn't out of bounds.
     * @param position
     * @return boolean if move is allowed
     * @author Aaldert Kroes
     */
    public boolean allowMoveOthello(int position, char piece) {
        int[] coordsMove = coordinate(position);
        boolean freeSpace = getBoard()[coordsMove[0]][coordsMove[1]] != ' ';            // filled space
        boolean inBounds = position > 0 && position < 65;                               // in bounds
        boolean surround = adjacentTiles(position, piece);                              // surrounding tiles opponent
        // check for if it captures at least one opposing tile

        return freeSpace && inBounds && surround; // add additional booleans
    }

    /**
     * Returns a boolean based on whether any opponent's tiles surround the position
     * @param position
     * @param piece
     * @return true if any adjacent tiles are an opponent's tile
     * @author Aaldert Kroes
     */
    private boolean adjacentTiles(int position, char piece){
        char myPiece = piece;
        char oppPiece = ' ';
        if(piece == 'b'){oppPiece = 'w';} else {oppPiece = 'b';}
        int[] coordsMove = coordinate(position);
        char[][] board = getBoard();
        boolean legalMove = false;

        // right
        if(coordsMove[0]+1 < 8 && board[coordsMove[0]+1][coordsMove[1]] == oppPiece){
            for (int i = coordsMove[0]+1; i < 8; i++) {
                if(board[i][coordsMove[1]] == myPiece){legalMove = true; break;}
            }
        }
        // left
        if(coordsMove[0]-1 >= 0 && board[coordsMove[0]-1][coordsMove[1]] == oppPiece){
            for (int i = coordsMove[0]-1; i >=0; i--) {
                if(board[i][coordsMove[1]] == myPiece){legalMove = true; break;}
            }
        }
        // up
        if(coordsMove[1]-1 >= 0 && board[coordsMove[0]][coordsMove[1]-1] == oppPiece){
            for (int i = coordsMove[1]-1; i >= 0; i--) {
                if(board[coordsMove[0]][i] == myPiece){ legalMove = true; break;}
            }
        }
        // down
        if(coordsMove[1]+1 < 8 && board[coordsMove[0]][coordsMove[1]+1] == oppPiece){
            for (int i = coordsMove[1]+1; i < 8; i++) {
                if(board[coordsMove[0]][i] == myPiece){legalMove = true; break;}
            }
        }
        // left-diagonal up
        if(coordsMove[1]-1 >= 0 && coordsMove[0]-1 >= 0 && board[coordsMove[0]-1][coordsMove[1]-1] == oppPiece){
            for (int i = 1; i < Math.min(coordsMove[0], coordsMove[1]); i++) {
                if(board[coordsMove[0]-i][coordsMove[1]-i] == myPiece){legalMove = true; break;}
            }
        }
        // left-diagonal down
        if(coordsMove[1]-1 < 8 && coordsMove[0]+1 < 8 && board[coordsMove[0]-1][coordsMove[1]+1] == oppPiece){
            for (int i = 1; i < Math.min(coordsMove[0], 8-coordsMove[1]); i++) {
                if(board[coordsMove[0]-i][coordsMove[1]+i] == myPiece){legalMove = true; break;}
            }
        }
        // right-diagonal up
        if(coordsMove[1]+1 < 8 && coordsMove[0]-1 < 8 && board[coordsMove[0]+1][coordsMove[1]-1] == oppPiece){
            for (int i = 1; i < Math.min(8-coordsMove[0], coordsMove[1]); i++) {
                if(board[coordsMove[0]+i][coordsMove[1]-i] == myPiece){legalMove = true; break;}
            }
        }
        // right-diagonal down
        if(coordsMove[1]+1 < 8 && coordsMove[0]+1 < 8 && board[coordsMove[0]+1][coordsMove[1]+1] == oppPiece){
            for (int i = 1; i < 8-Math.max(coordsMove[0], coordsMove[1]); i++) {
                if(board[coordsMove[0]+i][coordsMove[1]+i] == myPiece){legalMove = true; break;}
            }
        }
        return legalMove;
    }
    
    /**
     * @author Ihab Al-Safadi
     * @param position
     * @param piece
     */

    public void flipPiece(int position, char piece){
        char myPiece = piece;
        char oppPiece = ' ';
        if(piece == 'b'){oppPiece = 'w';} else {oppPiece = 'b';}
        int[] coordsMove = coordinate(position);
        char[][] board = getBoard();
        boolean legalMove = false;

        // right
        if(coordsMove[0]+1 < 8 && board[coordsMove[0]+1][coordsMove[1]] == oppPiece){
            for (int i = coordsMove[0]+1; i < 8; i++) {
                if(board[i][coordsMove[1]] == oppPiece){board[i][coordsMove[1]] = piece;}
                if(board[i][coordsMove[1]] == myPiece){break;}
            }
        }
        // left
        if(coordsMove[0]-1 >= 0 && board[coordsMove[0]-1][coordsMove[1]] == oppPiece){
            for (int i = coordsMove[0]-1; i >=0; i--) {
                if(board[i][coordsMove[1]] == oppPiece){board[i][coordsMove[1]] = piece;}
                if(board[i][coordsMove[1]] == myPiece){break;}
            }
        }
        // up
        if(coordsMove[1]-1 >= 0 && board[coordsMove[0]][coordsMove[1]-1] == oppPiece){
            for (int i = coordsMove[1]-1; i >= 0; i--) {
                if(board[coordsMove[0]][i] == oppPiece){ board[coordsMove[0]][i] = piece;}
                if(board[coordsMove[0]][i]== myPiece){break;}
            }
        }
        // down
        if(coordsMove[1]+1 < 8 && board[coordsMove[0]][coordsMove[1]+1] == oppPiece){
            for (int i = coordsMove[1]+1; i < 8; i++) {
                if(board[coordsMove[0]][i] == oppPiece){board[coordsMove[0]][i] = piece;}
                if(board[coordsMove[0]][i]== myPiece){break;}
            }
        }
        // left-diagonal up
        if(coordsMove[1]-1 >= 0 && coordsMove[0]-1 >= 0 && board[coordsMove[0]-1][coordsMove[1]-1] == oppPiece){
            for (int i = 1; i < Math.min(coordsMove[0], coordsMove[1]); i++) {
                if(board[coordsMove[0]-i][coordsMove[1]-i] == oppPiece){board[coordsMove[0]-i][coordsMove[1]-i] = piece;}
                if(board[coordsMove[0]-i][coordsMove[1]-i]== myPiece){break;}
            }
        }
        // left-diagonal down
        if(coordsMove[1]-1 < 8 && coordsMove[0]+1 < 8 && board[coordsMove[0]-1][coordsMove[1]+1] == oppPiece){
            for (int i = 1; i < Math.min(coordsMove[0], 8-coordsMove[1]); i++) {
                if(board[coordsMove[0]-i][coordsMove[1]+i] == oppPiece){board[coordsMove[0]-i][coordsMove[1]+i] = piece;}
                if(board[coordsMove[0]-i][coordsMove[1]+i]== myPiece){break;}
            }
        }
        // right-diagonal up
        if(coordsMove[1]+1 < 8 && coordsMove[0]-1 < 8 && board[coordsMove[0]+1][coordsMove[1]-1] == oppPiece){
            for (int i = 1; i < Math.min(8-coordsMove[0], coordsMove[1]); i++) {
                if(board[coordsMove[0]+i][coordsMove[1]-i] == oppPiece){board[coordsMove[0]+i][coordsMove[1]-i] = piece;}
                if(board[coordsMove[0]+i][coordsMove[1]-i] == myPiece){break;}
            }
        }
        // right-diagonal down
        if(coordsMove[1]+1 < 8 && coordsMove[0]+1 < 8 && board[coordsMove[0]+1][coordsMove[1]+1] == oppPiece){
            for (int i = 1; i < 8-Math.max(coordsMove[0], coordsMove[1]); i++) {
                if(board[coordsMove[0]+i][coordsMove[1]+i] == oppPiece){board[coordsMove[0]+i][coordsMove[1]+i] = piece;}
                if(board[coordsMove[0]+i][coordsMove[1]+i]== myPiece){break;}
            }
        }


    }

    /**
     * Checks whether player or opponent wins
     * @param piece
     * @return true for player, false for opponent
     * @author Aaldert Kroes
     */
    public boolean winOthello(char piece) {
        int pieceCount = 0;
        int oppPieceCount = 0;
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if(getBoard()[row][col] == piece){pieceCount++;} else {oppPieceCount++;}
            }
        }
        return pieceCount > oppPieceCount;
    }
}
