package ticTacToe;

import gameFramework.Player;

/**
 * AI for tic-tac-toe and Othello
 * @author Mart de Vries, Aaldert Kroes
 * @version 1.0
 */
public class AiForTicTacToe extends Player{

    private static final int Max_depth_1 = 8;
    // max search depth

    public AiForTicTacToe(int playernumber, char piece){
        super(playernumber, piece);
    }

    /**
     * Selects the best possible move for the chosen player
     * @author Aaldert Kroes, Mart de Vries
     * @return move position integer ranging 1-9
     */
    public static int moveSelect(TicTacToe AIBoard, char piece){
        int move = 0;
        int bestValue = Integer.MIN_VALUE;

        if (AIBoard.filledSpaces() == 0 || AIBoard.filledSpaces() == 1) {
            // if it's the first move for player 1 or player 2, use method firstMove
            move = firstMove(AIBoard);
            return move;
        }

        for (int position = 1; position < 10; position++) {
            if (AIBoard.allowMove((position))) {
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
     * @author Aaldert Kroes, Mart de Vries
     * @param AIBoard
     * @return integer
     */
    private static int counterPlayer(TicTacToe AIBoard, char piece, int depth){
        char myPiece = piece;
        char opPiece = ' ';
        if(myPiece == 'X'){
            // checks if the player is X or O and gives opponent the opposite char
            opPiece = 'O';
        } else {
            opPiece = 'X';
        }

        if(AIBoard.win(myPiece)){
            // checks if the chosen position returns a win and returns win combined with the depth the win is at
            // the earlier the win, the higher the returned value
            return 1 + depth;
        }
        if(AIBoard.win(opPiece)){
            // checks if the chosen position returns a loss and returns loss combined with the depth the loss is at
            // the earlier the loss, the lower the returned value
            return -1 - depth;
        }

        // draw if there is no win or loss
        return 0;
    }

    /**
     * Decides what the first move is based on whether the AI moves first or not
     * Based on: https://www.youtube.com/watch?v=OmC07DvEayY
     * @author Aaldert Kroes, Mart de Vries
     * @param AIBoard
     * @return position integer ranging 1-9
     */
    private static int firstMove(TicTacToe AIBoard){
        int move = 0;

        if(AIBoard.isEmpty()){
            // If AI moves first, place a piece in the top left corner.
            move = 1;
        } else if(cornerCheck(AIBoard)){
            // If opponent put a piece in the corner, place your piece in the middle
            move = 5;
        } else if(edgeCheck(AIBoard)){
            // If opponent put a piece on the edge, place your piece in the middle
            move = 5;
        } else {
            // Place a piece in the corner anyway, biggest chance of winning
            move = 1;
        }

        return move;
    }

    /**
     * Checks whether corners have been filled
     * Only used in fistMove()
     * @param AIBoard
     * @return true or false
     * @author Aaldert Kroes
     */
    private static boolean cornerCheck(TicTacToe AIBoard){
        int counter = 0;
        if(AIBoard.allowMove(1)){counter++;}
        if(AIBoard.allowMove(3)){counter++;}
        if(AIBoard.allowMove(7)){counter++;}
        if(AIBoard.allowMove(9)){counter++;}

        return counter == 3;
    }

    /**
     * Checks whether edges have been filled
     * @param AIBoard
     * @return true or false
     * @author Mart de Vries
     */
    private static boolean edgeCheck(TicTacToe AIBoard){
        int counter = 0;
        if(AIBoard.allowMove(2)){counter++;}
        if(AIBoard.allowMove(4)){counter++;}
        if(AIBoard.allowMove(6)){counter++;}
        if(AIBoard.allowMove(8)){counter++;}

        return counter == 3;
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
     * @author Mart de Vries, Aaldert Kroes
     */
    private static int minimax(TicTacToe AIBoard, int depth, char piece, boolean maximisingPlayer, int alpha, int beta) {
        char myPiece = piece;
        char opPiece = ' ';
        if(myPiece == 'X'){
            // checks if the player is X or O and gives opponent the opposite char
            opPiece = 'O';
        } else {
            opPiece = 'X';
        }

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
                if (AIBoard.allowMove((position))) {
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
                if (AIBoard.allowMove((position))) {
                    // checks every position and places a piece when a position is free, removes this piece again when current value is the lowest value
                    AIBoard.add(opPiece, position);
                    lowestValue = Math.min(lowestValue, minimax(AIBoard, depth - 1, myPiece, true, alpha, beta));
                    // lowest value between current position and the next position of the opposite player
                    AIBoard.remove(opPiece, position);
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


