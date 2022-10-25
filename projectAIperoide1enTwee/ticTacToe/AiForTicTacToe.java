package ticTacToe;

import gameFramework.Player;
import gameFramework.Board;

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

        public static int moveSelect(TicTacToe AIBoard, char piece){
            /**
             * If a move can win, gives board position to prevent or win
             * Else chooses random legal position
             * @author Aaldert Kroes, Mart de Vries
             * @return move position
             */
            boolean check = false;
            int move = 0;
            int[] values = counterPlayer(AIBoard, piece);

            for (int i = 0; i < 9; i++) {
                if(values[i] == 1 || values[i] == -1){
                    move = i+1;
                    check = true;
                }
            }

            while(!check){
                move = (int)(Math.random() * 8)+1;
                if(AIBoard.allowMove(move)){
                    check = true;
                }
            }
            return move;
        }

        /**
         * Creates an int array with 1s, 0s and -1s based on win, lose or nothing happening
         * @param AIBoard
         * @return 1, 0, -1 based on wincondition.
         */
        public static int[] counterPlayer(TicTacToe AIBoard, char piece){
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
    }


