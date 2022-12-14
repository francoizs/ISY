package othello;

import gameFramework.Game;
import gameFramework.Gui;

import java.io.Console;

/**
 * @author Ihab Al-Safadi
 */
public class Othello extends Game {

    public Othello(int size) {
        super(size);
        startPositions();
    }

    public Othello(int width, int height) {
        super(width, height, "Othello");
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
     * @param position
     * @return boolean if move is allowed
     * @author Aaldert Kroes
     */
    public boolean allowMoveOthello(int position, char piece) {
        int[] coordsMove = coordinate(position);
        boolean freeSpace = getBoard()[coordsMove[0]][coordsMove[1]] == ' ';    // filled space
        boolean inBounds = position > 0 && position < 65;                       // in bounds
        boolean surround = adjacentTiles(position, piece);                      // surrounding tiles opponent

        return freeSpace && inBounds && surround;
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
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}
        int[] coordsMove = coordinate(position);
        char[][] board = getBoard();

        boolean right = GameRules.rightTraverse(piece, coordsMove[0], coordsMove[1], board);                    // right
        boolean left = GameRules.leftTraverse(piece, coordsMove[0], coordsMove[1], board);                      // left
        boolean up = GameRules.upTraverse(piece, coordsMove[0], coordsMove[1], board);                          // up
        boolean down = GameRules.downTraverse(piece, coordsMove[0], coordsMove[1], board);                      // down

        boolean diagLeftUp = GameRules.diagLeftUpTraverse(piece, coordsMove[0], coordsMove[1], board);          // diagonal left up
        boolean diagLeftDown = GameRules.diagLeftDownTraverse(piece, coordsMove[0], coordsMove[1], board);      // diagonal left down
        boolean diagRightUp = GameRules.diagRightUpTraverse(piece, coordsMove[0], coordsMove[1], board);        // diagonal right up
        boolean diagRightDown = GameRules.diagRightDownTraverse(piece, coordsMove[0], coordsMove[1], board);    // diagonal right down

        return right || left || up || down || diagLeftUp || diagLeftDown || diagRightUp || diagRightDown;
    }
    
    /**
     * @author Ihab Al-Safadi, Aaldert Kroes
     * @param position
     * @param piece
     */
    public void flipPiece(int position, char piece){
        char myPiece = piece;
        char oppPiece = ' ';
        if(piece == '•'){oppPiece = '◦';} else {oppPiece = '•';}
        int[] coordsMove = coordinate(position);
        char[][] board = getBoard();
        boolean flipCheck = false;

        // right
        if(coordsMove[0]+1 < 8) {
            if (board[coordsMove[0] + 1][coordsMove[1]] == oppPiece) {
                for (int i = coordsMove[0] + 1; i < 8; i++) {
                    if (board[i][coordsMove[1]] == ' '){break;}
                    if (board[i][coordsMove[1]] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = coordsMove[0] + 1; i < 8; i++) {
                        if (board[i][coordsMove[1]] == oppPiece) {board[i][coordsMove[1]] = piece;}
                        if (board[i][coordsMove[1]] == myPiece) {break;}
                    }
                    flipCheck = false;
                }
            }
        }
        // left
        if(coordsMove[0]-1 >= 0) {
            if (board[coordsMove[0] - 1][coordsMove[1]] == oppPiece) {
                for (int i = coordsMove[0] - 1; i >= 0; i--) {
                    if (board[i][coordsMove[1]] == ' '){break;}
                    if (board[i][coordsMove[1]] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = coordsMove[0] - 1; i >= 0; i--) {
                        if (board[i][coordsMove[1]] == oppPiece) {board[i][coordsMove[1]] = piece;}
                        if (board[i][coordsMove[1]] == myPiece) {break;}
                    }
                    flipCheck = false;
                }
            }
        }
        // up
        if(coordsMove[1]-1 >= 0) {
            if (board[coordsMove[0]][coordsMove[1] - 1] == oppPiece) {
                for (int i = coordsMove[1] - 1; i >= 0; i--) {
                    if (board[coordsMove[0]][i] == ' '){break;}
                    if (board[coordsMove[0]][i] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = coordsMove[1] - 1; i >= 0; i--) {
                        if (board[coordsMove[0]][i] == oppPiece) {board[coordsMove[0]][i] = piece;}
                        if (board[coordsMove[0]][i] == myPiece) {break;}
                    }
                    flipCheck = false;
                }
            }
        }
        // down
        if(coordsMove[1]+1 < 8) {
            if (board[coordsMove[0]][coordsMove[1] + 1] == oppPiece) {
                for (int i = coordsMove[1] + 1; i < 8; i++) {
                    if (board[coordsMove[0]][i] == ' '){break;}
                    if (board[coordsMove[0]][i] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = coordsMove[1] + 1; i < 8; i++) {
                        if (board[coordsMove[0]][i] == oppPiece) {board[coordsMove[0]][i] = piece;}
                        if (board[coordsMove[0]][i] == myPiece) {break;}
                    }
                    flipCheck = false;
                }
            }
        }
        // left-diagonal up
        if(coordsMove[0]-1 >= 0 && coordsMove[1]-1 >= 0) {
            if (board[coordsMove[0] - 1][coordsMove[1] - 1] == oppPiece) {
                for (int i = 1; i < Math.min(coordsMove[0], coordsMove[1]); i++) {
                    if (board[coordsMove[0] - i][coordsMove[1] - i] == ' '){break;}
                    if (board[coordsMove[0] - i][coordsMove[1] - i] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = 0; i < Math.min(coordsMove[0], coordsMove[1]); i++) {
                        if (board[coordsMove[0] - i][coordsMove[1] - i] == oppPiece) {board[coordsMove[0] - i][coordsMove[1] - i] = piece;}
                        if (board[coordsMove[0] - i][coordsMove[1] - i] == myPiece) {break;}
                    }
                    flipCheck = false;
                }
            }
        }
        // left-diagonal down
        if(coordsMove[1]+1 < 8 && coordsMove[0]-1 >= 0) {
            if (board[coordsMove[0] - 1][coordsMove[1] + 1] == oppPiece) {
                for (int i = 1; i < Math.min(coordsMove[0], 8 - coordsMove[1]); i++) {
                    if(board[coordsMove[0] - i][coordsMove[1] + i] == ' '){break;}
                    if (board[coordsMove[0] - i][coordsMove[1] + i] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = 0; i < Math.min(coordsMove[0], 8 - coordsMove[1]); i++) {
                        if (board[coordsMove[0] - i][coordsMove[1] + i] == oppPiece) {board[coordsMove[0] - i][coordsMove[1] + i] = piece;}
                        if (board[coordsMove[0] - i][coordsMove[1] + i] == myPiece) {break;}
                    }
                    flipCheck = false;
                }
            }
        }
        // right-diagonal up
        if(coordsMove[1]-1 >= 0 && coordsMove[0]+1 < 8) {
            if (board[coordsMove[0] + 1][coordsMove[1] - 1] == oppPiece) {
                for (int i = 1; i < Math.min(8 - coordsMove[0], coordsMove[1]); i++) {
                    if (board[coordsMove[0] + i][coordsMove[1] - i] == ' '){break;}
                    if (board[coordsMove[0] + i][coordsMove[1] - i] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = 0; i < Math.min(8 - coordsMove[0], coordsMove[1]); i++) {
                        if (board[coordsMove[0] + i][coordsMove[1] - i] == oppPiece) {board[coordsMove[0] + i][coordsMove[1] - i] = piece;}
                        if (board[coordsMove[0] + i][coordsMove[1] - i] == myPiece) {break;}
                    }
                    flipCheck = false;
                }
            }
        }
        // right-diagonal down
        if(coordsMove[1]+1 < 8 && coordsMove[0]+1 < 8) {
            if (board[coordsMove[0] + 1][coordsMove[1] + 1] == oppPiece) {
                for (int i = 1; i < 8 - Math.max(coordsMove[0], coordsMove[1]); i++) {
                    if (board[coordsMove[0] + i][coordsMove[1] + i] == ' '){break;}
                    if (board[coordsMove[0] + i][coordsMove[1] + i] == myPiece) {flipCheck = true; break;}
                }
                if (flipCheck){
                    for (int i = 0; i < 8 - Math.max(coordsMove[0], coordsMove[1]); i++) {
                        if (board[coordsMove[0] + i][coordsMove[1] + i] == oppPiece) {board[coordsMove[0] + i][coordsMove[1] + i] = piece;}
                        if (board[coordsMove[0] + i][coordsMove[1] + i] == myPiece){break;}
                    }
                }
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
                if (getBoard()[row][col] == piece) {
                    pieceCount++;
                } else {
                    oppPieceCount++;
                }
            }
        }
        return pieceCount > oppPieceCount;
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
    public void moveAI(char piece) { 

    }
    @Override
    public void serverAdd(int position, char piece) { // maakt de serverAdd method
        add(piece, position); // roept de add methode aan van Othello
        flipPiece(position, piece); // roept de flipPiece methode aan van Othello
        convertToJButtons(); // roept de convertToJButtons methode aan van Othello
    }
}