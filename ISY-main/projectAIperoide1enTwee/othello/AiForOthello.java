package othello;

import gameFramework.Player;

public class AiForOthello extends Player {

    private final int Max_depth_1 = 8;

    /**
     * @param playernumber
     * @param piece
     * @author Mart de Vries
     */
    public AiForOthello(int playernumber, char piece) {
        super(playernumber, piece);
    }

    /**
     * Selects the best possible move for the chosen player
     * @author Mart de Vries
     * @return move position integer ranging 1-64
     */
    public int moveselectOthello(Othello AIBoard, char piece) {
        int move = 0;
        int bestValue = Integer.MIN_VALUE;

        for (int position = 1; position < 65; position++) {
            if (AIBoard.allowMoveOthello(position, piece)) {
                // checks every position and places a piece when a position is free, removes this piece again after minimax is done
                AIBoard.add(piece, position);
                int positionValue = minimax(AIBoard, Max_depth_1, piece, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                AIBoard.remove(piece, position);
                if (positionValue > bestValue) {
                    // checks if the value for the new position is better than the old best value, sets the current position to move when true
                    bestValue = positionValue;
                    move = position;
                }
            }
        }
        return move;
    }

    /**
     * Returns an integer, high integers mean an early win, low integers mean an early loss
     * @author Mart de Vries
     * @param AIBoard
     * @return integer
     */
    private int counterPlayer(Othello AIBoard, char piece, int depth) {
        char myPiece = piece;
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}

        if(AIBoard.winOthello(myPiece)){
            // checks if the chosen position returns a win and returns win combined with the depth the win is at
            // the earlier the win, the higher the returned value
            return 1 + depth;
        }
        if(AIBoard.winOthello(oppPiece)){
            // checks if the chosen position returns a loss and returns loss combined with the depth the loss is at
            // the earlier the loss, the lower the returned value
            return -1 - depth;
        }

        return 0;
    }

    /**
     * Uses the algorithm minimax to determine the value for a certain available position
     * @param AIBoard
     * @param depth the search depth
     * @param piece
     * @param maximisingPlayer maximising or minimising player
     * @param alpha the best option for the maximising player
     * @param beta the best option for the minimising player
     * @return Highest or Lowest value for a certain position
     * @author Mart de Vries
     */
    private int minimax(Othello AIBoard, int depth, char piece, boolean maximisingPlayer, int alpha, int beta) {
        char myPiece = piece;
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}

        int positionValue = counterPlayer(AIBoard, piece, depth);
        // checks for win/loss/draw for current position using counterplayer

        if (depth == 0 || Math.abs(positionValue) > 0 || AIBoard.isFull()) {
            // returns the value for the current position when max depth is reached, a win or loss is reached or when the board is full
            return positionValue;
        }

        if(maximisingPlayer) {
            // when it's the turn of the current player
            int highestValue = Integer.MIN_VALUE;
            // sets the value to the lowest possible value
            for (int position = 1; position < 10; position++) {
                if (AIBoard.allowMoveOthello(position, piece)) {
                    // checks every position and places a piece when a position is free, removes this piece again when current value is the highest value
                    AIBoard.add(myPiece, position);
                    highestValue = Math.max(highestValue, minimax(AIBoard, depth - 1, myPiece, false, alpha, beta));
                    // highest value between current position and the next position of the opposite player
                    AIBoard.remove(myPiece, position);
                    alpha = Math.max(alpha, highestValue);
                    // checks if highest value is higher than last highest value and returns the highest value when last highest value is higher than lowest
                    if(alpha >= beta) {
                        return highestValue;
                    }
                }
            }
            return highestValue;
        } else {
            // when it's the turn of the opponent
            int lowestValue = Integer.MAX_VALUE;
            // sets the value to the highest possible value
            for (int position = 1; position < 10; position++) {
                if (AIBoard.allowMoveOthello(position, piece)) {
                    // checks every position and places a piece when a position is free, removes this piece again when current value is the lowest value
                    AIBoard.add(oppPiece, position);
                    lowestValue = Math.min(lowestValue, minimax(AIBoard, depth - 1, myPiece, true, alpha, beta));
                    // lowest value between current position and the next position of the opposite player
                    AIBoard.remove(oppPiece, position);
                    beta = Math.min(beta, lowestValue);
                    // checks if lowest value is lower than last lowest value and returns the lowest value when last highest value is higher than lowest
                    if(alpha >= beta) {
                        return lowestValue;
                    }
                }
            }
            return lowestValue;
        }
    }
}
