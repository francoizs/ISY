package othello;

import gameFramework.Game;

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
    // negeer dit, ben aan het kutten geweest en het lukt niet :P
    private boolean adjacentTiles(int position, char piece){
        char myPiece = piece;
        char oppPiece = ' ';
        if(piece == 'b'){oppPiece = 'w';} else {oppPiece = 'b';}
        int[] coordsMove = coordinate(position);
        char[][] board = getBoard();
        int[][] adjacentCoords = new int[8][2];

        // general
        if(coordsMove[0] >= 0 && coordsMove[0] <= 7 && coordsMove[1] >= 0 && coordsMove[1] >= 7){
            adjacentCoords[0][0] = coordsMove[0]-1; // top left
            adjacentCoords[0][1] = coordsMove[1]-1;
            adjacentCoords[1][0] = coordsMove[0]-1; // left middle
            adjacentCoords[1][1] = coordsMove[0];
            adjacentCoords[2][0] = coordsMove[0]-1; // bottom left
            adjacentCoords[2][1] = coordsMove[0]+1;
            adjacentCoords[3][0] = coordsMove[0]+1; // top right
            adjacentCoords[3][1] = coordsMove[0]-1;
            adjacentCoords[4][0] = coordsMove[0]+1; // right middle
            adjacentCoords[4][1] = coordsMove[0];
            adjacentCoords[5][0] = coordsMove[0]+1; // bottom right
            adjacentCoords[5][1] = coordsMove[0]+1;
            adjacentCoords[6][0] = coordsMove[0];   // top middle
            adjacentCoords[6][1] = coordsMove[0]-1;
            adjacentCoords[7][0] = coordsMove[0];   // bottom middle
            adjacentCoords[7][1] = coordsMove[0]+1;
        }

        // top-left corner
        if(coordsMove[0] == 0 && coordsMove[1] == 0){
            adjacentCoords[4][0] = coordsMove[0]+1; // right middle
            adjacentCoords[4][1] = coordsMove[0];
            adjacentCoords[5][0] = coordsMove[0]+1; // bottom right
            adjacentCoords[5][1] = coordsMove[0]+1;
            adjacentCoords[7][0] = coordsMove[0];   // bottom middle
            adjacentCoords[7][1] = coordsMove[0]+1;
            for (int j = 3; j < adjacentCoords.length; j++) {
                
            }
        }

        // top-right corner
        if(coordsMove[0] == 7 && coordsMove[1] == 0){

        }
        // bottom-left corner
        if(coordsMove[0] == 0 && coordsMove[1] == 7){

        }
        // bottom-right corner
        if(coordsMove[0] == 7 && coordsMove[1] == 7){

        }

        // final loop
        for (int i = 0; i < adjacentCoords.length; i++) {
            if(adjacentCoords[i][0]!= 10 && board[i][0] == oppPiece){
                return true;
            }
        }
        return false;
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
