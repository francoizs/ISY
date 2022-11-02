package ticTacToe;

import gameFramework.Player;
import gameFramework.Board;
import javax.swing.*;

import java.util.ArrayList;

/**
 * AI for tic-tac-toe and Othello
 * @author Mart de Vries
 * @version 1.0
 */
public class AiForTicTacToe extends Player{

    public AiForTicTacToe(int playernumber, char piece){
        super(playernumber, piece);
    }

    public int moveSelect(TicTacToe AIBoard, char piece){
        /**
         * First move will be placed in the corner, if opponent moves first its placed in the center.
         * If a move can win, gives board position to prevent or win
         * Else chooses random legal position
         * @author Aaldert Kroes, Mart de Vries
         * @return move position integer ranging 1-9
         */
        boolean check = false;
        int move = 0;
        int[] values = counterPlayer(AIBoard, piece);

        move = firstMove(AIBoard);
        // Does random legal move if firstMove() happened and no winning move is present.
        if(!AIBoard.allowMove(move)){
            while(!check){
                move = (int)(Math.random() * 8)+1;
                if(AIBoard.allowMove(move)){
                    check = true;
                }
            }
        }

        // If a winning move is present, will always place that move.
        // Else one of the options to prevent the opponent from winning will be used.
        for (int i = 0; i < 9; i++) {
            if(values[i] == 1){
                move = i+1;
                check = true;
                break;
            }
            if(values[i] == -1){
                move = i+1;
                check = true;
            }
        }
        return move;
    }

    /**
     * Creates an int array with 1s, 0s and -1s based on win, lose or nothing happening
     * @author Aaldert Kroes, Mart de Vries
     * @param AIBoard
     * @return 1, 0, -1 based on wincondition.
     */
    private int[] counterPlayer(TicTacToe AIBoard, char piece){
        int[] results = new int[9];
        char myPiece = piece;
        char opPiece = ' ';
        if(myPiece == 'X'){opPiece = 'O';} else {opPiece = 'X';}

        for (int i = 1; i < 10; i++) {
            if(AIBoard.allowMove((i))){
                // Add own piece and check if win
                AIBoard.add(myPiece, i);
                if(AIBoard.win(myPiece)){results[i-1] = 1;}
                AIBoard.remove(myPiece, i);
                // Add opponent piece and check if win
                AIBoard.add(opPiece, i);
                if(AIBoard.win(opPiece)){results[i-1] = -1;}
                AIBoard.remove(opPiece, i);
            }
        }
        return results;
    }

    /**
     * Decides what the first move is based on whether the AI moves first or not
     * Based on: https://www.youtube.com/watch?v=OmC07DvEayY
     * @author Aaldert Kroes
     * @param AIBoard
     * @return position integer ranging 1-9
     */
    private int firstMove(TicTacToe AIBoard){
        int move = 0;

        if(AIBoard.isEmpty()){
            // If AI moves first, place a piece in the top left corner.
            move = 1;
        } else if(cornerCheck(AIBoard)){
            // If opponent put a piece in the corner, place your piece in the middle
            move = 5;
        } else {
            // Place a piece in the corner anyway, biggest chance of winning
            move = 1;
        }

        return move;
    }

    /**
     * Checks whether corneres have been filled
     * Only used in fistMove()
     * @param AIBoard
     * @return true or false
     */
    private boolean cornerCheck(TicTacToe AIBoard){
        int counter = 0;
        if(AIBoard.allowMove(1)){counter++;}
        if(AIBoard.allowMove(3)){counter++;}
        if(AIBoard.allowMove(7)){counter++;}
        if(AIBoard.allowMove(9)){counter++;}

        return counter == 3;
    }

    private boolean guiBoardToTicTacToeBoard(JPanel board){
        return true;
    }
}


