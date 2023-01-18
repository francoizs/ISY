package othello;

import java.util.ArrayList;
import java.util.Random;

public class OpponentAi extends AiOthello{

    public OpponentAi (int playernumber, char piece){super(playernumber, piece);}

    public int moveselectOthello10(Othello AIBoard, char piece){
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

        if(AIBoard.filledSpaces() % 17 == 3){
            for(int position = 0; position < 64; position++){
                if (AIBoard.allowMoveOthello(position, piece)) validmoves.add(position);
            }
            Random random = new Random();
            move = random.nextInt(validmoves.size());
        } else {
            for (int position = 0; position < 64; position++) {
                if (AIBoard.allowMoveOthello(position, piece)) {
                    // checks every position and places a piece when a position is free, removes this piece again after minimax is done
                    AiAdd(AIBoard, position, piece);
                    flippedmoveselect = CheckFlipped(AIBoard.getBoard(), position, previousboard);
                    int positionValue = minimax(AIBoard, getMax_depth(), oppPiece, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
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
        }
        System.out.println("Ai move: " + move);
        return move;
    }

    private int minimax(Othello AIBoard, int depth, char piece, boolean maximisingPlayer, int alpha, int beta) {
        int positionValue = 0;
        final char[][] previousboard = new char[8][8];
        for (int row=0; row < 8; row++){
            for (int col = 0; col < 8; col++) {
                previousboard[row][col] = AIBoard.getBoard()[row][col];
            }
        }
        ArrayList<Integer> flippedminimax;
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}

        if (piece == getPiece()) {
            positionValue = AIBoard.MovesCounter(AIBoard, piece);
        } else {
            positionValue = -AIBoard.MovesCounter(AIBoard, piece);
        }

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