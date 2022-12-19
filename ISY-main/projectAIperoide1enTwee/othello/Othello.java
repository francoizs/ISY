
package othello;

import gameFramework.Gui;
import ticTacToe.AiForTicTacToe;
import gameFramework.*;


import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Ihab Al-Safadi
 */
public class Othello extends Game {

    public Othello(int size) {
        super(size);
        startPositions();
    }

    public Othello(int width, int height, char piece) {
        super(width, height, piece);
        startPositions();
        convertToJButtons();
    }

    private void startPositions() {
        getBoard()[3][3] = '•';
        getBoard()[3][4] = '◦';
        getBoard()[4][3] = '◦';
        getBoard()[4][4] = '•';
    }

    /**
     * Checks whether the move is allowed with the Othello rules, meaning:
     * Space must be empty, this position will enclose at least one opposing piece and the position isn't out of bounds.
     *
     * @param position
     * @return boolean if move is allowed
     * @author Aaldert Kroes
     */
    public boolean allowMoveOthello(int position, char piece) {
        int[] coordsMove = coordinate(position);
        boolean freeSpace = getBoard()[coordsMove[0]][coordsMove[1]] == ' ';    // filled space
        boolean inBounds = position >= 0 && position < 64;                       // in bounds
        boolean surround = adjacentTiles(position, piece);                      // surrounding tiles opponent

        return freeSpace && inBounds && surround;
    }

    /**
     * Returns a boolean based on whether any opponent's tiles surround the position
     *
     * @param position
     * @param piece
     * @return true if any adjacent tiles are an opponent's tile
     * @author Aaldert Kroes
     */
    private boolean adjacentTiles(int position, char piece) {
        char myPiece = piece;
        char oppPiece = oppPiece(piece);

        int[] coordsMove = coordinate(position);
        char[][] board = getBoard();
        int[] myAllowPositions = new int[2];

        boolean right = GameRules.rightTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);  // right

        boolean left = GameRules.leftTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);    // left

        boolean up = GameRules.upTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);        // up

        boolean down = GameRules.downTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);    // down

        boolean diagLeftUp = GameRules.diagLeftUpTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);    // diagLeftUp

        boolean diagLeftDown = GameRules.diagLeftDownTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);

        boolean diagRightUp = GameRules.diagRightUpTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);

        boolean diagRightDown = GameRules.diagRightDownTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);    // diagonal right down

        return right || left || up || down || diagLeftUp || diagLeftDown || diagRightUp || diagRightDown;
    }

    /**
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @param position
     * @param piece
     */
    public void flipPiece(int position, char piece){
        char myPiece = piece;
        char oppPiece = oppPiece(piece);
        int[] coordsMove = coordinate(position);
        char[][] board = getBoard();

        // right
        boolean right = GameRules.rightTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(right){ FlipPiece.rightTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);}
        // left
        boolean left = GameRules.leftTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(left){ FlipPiece.leftTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board); }
        // up
        boolean up = GameRules.upTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(up){ FlipPiece.upTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);  }
        // down
        boolean down = GameRules.downTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(down){ FlipPiece.downTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);}
        // left-diagonal up
        boolean diagLeftUp = GameRules.diagLeftUpTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(diagLeftUp){FlipPiece.diagLeftUpTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);}

        // left-diagonal down
        boolean diagLeftDown = GameRules.diagLeftDownTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(diagLeftDown){FlipPiece.diagLeftDownTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);}
        // right-diagonal up
        boolean diagRightUp = GameRules.diagRightUpTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(diagRightUp){FlipPiece.diagRightUpTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);}
        // right-diagonal down
        boolean diagRightDown = GameRules.diagRightDownTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);
        if(diagRightDown){FlipPiece.diagRightDownTraverse(piece, oppPiece, coordsMove[0], coordsMove[1], board);}
    }

    /**
     * Checks whether player or opponent wins
     * @param piece
     * @return true for player, false for opponent
     * @author Aaldert Kroes
     */
    public boolean winOthello(char piece) {
        int pieceCount = pieceCounter(piece);
        int oppPieceCount = pieceCounter(oppPiece(piece));
        return pieceCount > oppPieceCount;
    }

    /**
     * Kijkt naar hoeveel stenen je pakt.
     * Wordt gebruikt in minimax algoritme.
     * @param piece
     * @return aantal van je eigen stenen
     * @author Aaldert Kroes
     */
    public int pieceCounter(char piece){
        int pieceCount = 0;
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if(getBoard()[row][col] == piece){
                    pieceCount++;
                }
            }
        }
        return pieceCount;
    }

    public void convertToJButtons() { // converts the board to JButtons
        int counter = 0; // counter for the JButtons
        for (int i = 0; i < Game.height; i++) { // loops through the board
            for (int j = 0; j < Game.width; j++) { // loops through the board
                if ((getBoard()[i][j] != ' ')) { // checks if the board is not empty
                    Gui.JButtons[counter].setText(String.valueOf(getBoard()[i][j])); // sets the text of the JButtons to the board
                }
                counter++; // adds 1 to the counter
            }
        }
    }

    @Override
    public void enableButtons(char piece) { // enables the buttons
        for (int i = 0; i < Game.width * Game.height; i++) { // loops through the JButtons
            boolean allow = allowMoveOthello(i, piece); // checks if the move is allowed
            if (allow) { // checks if the move is allowed
                Gui.JButtons[i].setEnabled(true); // enables the button
            } else { // if the move is not allowed
                Gui.JButtons[i].setEnabled(false); // disables the button
            }
        }
    }

    @Override
    public void moveAI(char piece, int playernumber) {
        AiForOthello ai = new AiForOthello(playernumber, piece); // maakt een AiForOthello object\
        int move = ai.moveselectOthello(this, piece); // roept de moveselectOthello methode aan van AiForOthello
        try { // probeert
            Connection.send("move " + move); // stuurt move + de waarde van move naar de server
        } catch (IOException e) { // als er een error is
            throw new RuntimeException(e); // print de error naar de console
        }
    }

    @Override
    public void serverAdd(int position, char piece) { // maakt de serverAdd method
        add(piece, position); // roept de add methode aan van Othello
        flipPiece(position, piece); // roept de flipPiece methode aan van Othello
        convertToJButtons(); // roept de convertToJButtons methode aan van Othello
    }

    @Override
    public char oppPiece(char piece){
        if(piece == '•'){return '◦';}
        return '•';
    }
}
