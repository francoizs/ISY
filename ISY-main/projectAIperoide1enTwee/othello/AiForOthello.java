package othello;

import gameFramework.Player;

import java.util.ArrayList;

public class AiForOthello extends Player {

    private final int Max_depth_1 = 4;

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
        ArrayList<Integer> validmoves = new ArrayList<>();
        final char[][] previousboard = new char[8][8];
        for (int row=0; row < 8; row++){
            for (int col = 0; col < 8; col++) {
                previousboard[row][col] = AIBoard.getBoard()[row][col];
            }
        }
        ArrayList<Integer> flippedmoveselect;
        int move = 0;
        int bestValue = Integer.MIN_VALUE;
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}

        if(AIBoard.filledSpaces() == 4) {
            return 19;
        }

        for (int position = 0; position < 64; position++) {
            if (AIBoard.allowMoveOthello(position, piece)) {
                // checks every position and places a piece when a position is free, removes this piece again after minimax is done
                AiAdd(AIBoard, position, piece);
                flippedmoveselect = CheckFlipped(AIBoard.getBoard(), position, previousboard);
                int positionValue = minimax(AIBoard, Max_depth_1, oppPiece, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                AiRemove(AIBoard, position, piece, flippedmoveselect);
                System.out.println("Value: " + positionValue + " positie: " + position);
                validmoves.add(position);
                if (positionValue > bestValue) {
                    // checks if the value for the new position is better than the old best value, sets the current position to move when true
                    bestValue = positionValue;
                    move = position;
                }
                if (bestValue == Integer.MIN_VALUE) {
                    move = validmoves.get(0);
                }
            }
        }
        System.out.println("Ai move: " + move);
        return move;
    }

    /**
     * Adds a move to the board
     * @author Mart de Vries
     * @param AIBoard
     * @param position
     * @param piece
     */
    public void AiAdd(Othello AIBoard, int position, char piece) {
        AIBoard.add(piece, position);
        AIBoard.flipPiece(position, piece);
    }

    /**
     * Flips all the discs that have previously been flipped
     * @author Mart de Vries
     * @param flipped
     * @param piece
     * @param AIBoard
     */
    public void ReverseFlip(ArrayList<Integer> flipped, char piece, Othello AIBoard) {
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}
        for (int flippedpiece : flipped) {
            AIBoard.remove(piece, flippedpiece);
            AIBoard.add(oppPiece, flippedpiece);
        }
    }

    /**
     * Checks which discs have been flipped
     * @author Mart de Vries
     * @param board
     * @param position
     * @param previousboard
     * @return returns all the flipped discs in an ArrayList
     */
    public ArrayList<Integer> CheckFlipped(char[][] board, int position, char[][] previousboard) {
        int counter = -1;
        ArrayList<Integer> flipped = new ArrayList<>();
        for (int row=0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                counter++;
                if (previousboard[row][col] != board[row][col] && position != counter) {
                    flipped.add(counter);
                }
            }
        }
        return flipped;
    }

    /**
     * Removes a move from the board
     * @author Mart de Vries
     * @param AIBoard
     * @param position
     * @param piece
     * @param flipped
     */
    public void AiRemove(Othello AIBoard, int position, char piece, ArrayList<Integer> flipped) {
        AIBoard.remove(piece, position);
        ReverseFlip(flipped, piece, AIBoard);
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
        final char[][] previousboard = new char[8][8];
        for (int row=0; row < 8; row++){
            for (int col = 0; col < 8; col++) {
                previousboard[row][col] = AIBoard.getBoard()[row][col];
            }
        }
        ArrayList<Integer> flippedminimax;
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}

        int positionValue = AIBoard.pieceCounter(piece);
        // checks for win/loss/draw for current position using counterplayer

        if (depth == 0 || AIBoard.isFull()) {
            // returns the value for the current position when max depth is reached, a win or loss is reached or when the board is full
            return positionValue;
        }

        if(maximisingPlayer) {
            // when it's the turn of the current player
            int highestValue = Integer.MIN_VALUE;
            // sets the value to the lowest possible value
            for (int position = 0; position < 64; position++) {
                if (AIBoard.allowMoveOthello(position, piece)) {
                    // checks every position and places a piece when a position is free, removes this piece again when current value is the highest value
                    AiAdd(AIBoard, position, piece);
                    flippedminimax = CheckFlipped(AIBoard.getBoard(), position, previousboard);
//                    System.out.println(position + "max");
                    highestValue = Math.max(highestValue, minimax(AIBoard, depth - 1, oppPiece, false, alpha, beta));
                    AiRemove(AIBoard, position, piece, flippedminimax);
                    // highest value between current position and the next position of the opposite player
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
            for (int position = 0; position < 64; position++) {
                if (AIBoard.allowMoveOthello(position, piece)) {
                    // checks every position and places a piece when a position is free, removes this piece again when current value is the lowest value
                    AiAdd(AIBoard, position, piece);
                    flippedminimax = CheckFlipped(AIBoard.getBoard(), position, previousboard);
//                    System.out.println(position + "min");
                    lowestValue = Math.min(lowestValue, minimax(AIBoard, depth - 1, oppPiece, true, alpha, beta));
                    AiRemove(AIBoard, position, piece, flippedminimax);
                    // lowest value between current position and the next position of the opposite player
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
