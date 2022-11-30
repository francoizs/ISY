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
    public boolean allowMoveOthello(int position) {
        int[] coordinateMove = coordinate(position);
        boolean freeSpace = getBoard()[coordinateMove[0]][coordinateMove[1]] != ' ';    // filled space
        boolean inBounds = position > 0 && position < 65;                               // in bounds
        // check for 8 adjacent tiles if filled
        // check for if it captures at least one opposing tile

        return freeSpace && inBounds; // add additional booleans
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
