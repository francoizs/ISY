
package othello;
/**
 * @author Ihab AL-Safadi, Aaldert Kroes, Mart de Vries, Francois Dieleman
 */

import gameFramework.Gui;
import gameFramework.*;
import java.io.IOException;
import java.util.ArrayList;

public class Othello extends Game {

    public Othello(int width, int height, char piece) {
        super(width, height, piece);
        startPositions();
        convertToJButtons();
    }



    /**
     * Hier wordt de eerste 4 pieces in het middel van de board gezetten
     */
    public void startPositions() {
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
        //Retrieve the coordinates.
        Coordinates.XAndYCoordinates(position);
        boolean freeSpace = getBoard()[Coordinates.getX()][Coordinates.getY()] == ' ';    // filled space
        boolean inBounds = position >= 0 && position < 64;                                // in bounds
        boolean surround = adjacentTiles(position, piece);                                // surrounding tiles opponent
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

        //Retrieve the coordinates.
        Coordinates.XAndYCoordinates(position);

        // right
        boolean right = GameRules.rightTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        // left
        boolean left = GameRules.leftTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        // up
        boolean up = GameRules.upTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        // down
        boolean down = GameRules.downTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        // diagLeftUp
        boolean diagLeftUp = GameRules.diagLeftUpTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        //diagLeftDown
        boolean diagLeftDown = GameRules.diagLeftDownTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        //diagRightUp
        boolean diagRightUp = GameRules.diagRightUpTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        //diagRightDown
        boolean diagRightDown = GameRules.diagRightDownTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),getBoard());

        return right || left || up || down || diagLeftUp || diagLeftDown || diagRightUp || diagRightDown;
    }




    /**
     * flipPiece Method flips the pieces, checks first all directions when one direction return true then it calls flip method of the relevante direction.
     * @author Ihab Al-Safadi
     * @param position
     * @param piece
     */
    public void flipPiece(int position, char piece){

        //Retrieve the coordinates.
        Coordinates.XAndYCoordinates(position);

        // Check if right true then flip
        boolean right = GameRules.rightTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        if(right){FlipPiece.rightTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());}

        // left
        boolean left = GameRules.leftTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());
        if(left){ FlipPiece.leftTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard()); }

        // up
        boolean up = GameRules.upTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());
        if(up){ FlipPiece.upTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());  }

        // down
        boolean down = GameRules.downTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());
        if(down){ FlipPiece.downTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());}

        // left-diagonal up
        boolean diagLeftUp = GameRules.diagLeftUpTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());
        if(diagLeftUp){FlipPiece.diagLeftUpTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());}

        // left-diagonal down
        boolean diagLeftDown = GameRules.diagLeftDownTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());
        if(diagLeftDown){FlipPiece.diagLeftDownTraverse(piece, oppPiece(piece),Coordinates.getX(), Coordinates.getY(), getBoard());}

        // right-diagonal up
        boolean diagRightUp = GameRules.diagRightUpTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());
        if(diagRightUp){FlipPiece.diagRightUpTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());}

        // right-diagonal down
        boolean diagRightDown = GameRules.diagRightDownTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(), getBoard());
        if(diagRightDown){FlipPiece.diagRightDownTraverse(piece, oppPiece(piece), Coordinates.getX(), Coordinates.getY(),  getBoard());}
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

    /**
     * Counts the amount of points gathered from
     * all the tiles with char piece on it
     * @param AiBoard
     * @param piece
     * @return total amount of points gathered
     * @author Mart de Vries
     */
    public int TileCounter(Othello AiBoard, char piece){
        Board tilepoints = TilePoints(new Board(8, 8));
        int counter = 0;
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if (AiBoard.getBoard()[row][col] == piece) {
                    counter += tilepoints.getBoard()[row][col];
                }
            }
        }
        return counter;
    }

    /**
     * Assigns the dedicated number to every tile on the board
     * @param board
     * @return board with points per tile
     * @author Mart de Vries
     */
    public Board TilePoints(Board board){
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                if ((row == 0 || row == 7) && (col == 0 || col == 7)) {board.getBoard()[row][col] = 5;}
                else if (((row == 0 || row == 7) && (col > 1 && col < 6)) || ((col == 0 || col == 7) && (row > 1 && row < 6))) {board.getBoard()[row][col] = 4;}
                else if (((row == 1 || row == 6) && (col > 1 && col < 6)) || ((col == 1 || col == 6) && (row > 1 && row < 6))) {board.getBoard()[row][col] = 2;}
                else if (row > 1 && row < 6) {board.getBoard()[row][col] = 3;}
                else {board.getBoard()[row][col] = 1;}
            }
        }
        return board;
    }

    public int MovesCounter(Othello AiBoard, char piece) {
        int possiblemoves = 0;
        for (int position = 0; position < 64; position++) {
            if (AiBoard.allowMoveOthello(position, piece)) {
                possiblemoves++;
            }
        }
        return possiblemoves;
    }

    /**
     * @author Francois Dieleman
     * @param piece
     */
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

    /**
     * @author Francois Dieleman
     * @param piece
     * @return allowedMoves
     */
    public ArrayList<Integer> allowedMoves(char piece) {
        ArrayList<Integer> allowedMoves = new ArrayList<Integer>();
        for (int i = 0; i < Game.width * Game.height; i++) {
            if (allowMoveOthello(i, piece)) {
                allowedMoves.add(i);
            }
        }
        return allowedMoves;
    }

    /**
     * @author Francois Dieleman
     * @param piece
     */
    @Override
    public void enableButtons(char piece) { // enables the buttons
        ArrayList<Integer> allowedMoves = allowedMoves(piece);
        for (int i = 0; i < allowedMoves.size(); i++) { // loops through the JButtons
            Gui.JButtons[allowedMoves.get(i)].setEnabled(true); // enables the JButtons
        }
    }
    
    /**
     * @author Francois Dieleman
     * @param piece
     * @param position
     */
    @Override
    public void moveAI(char piece, int playernumber) {
        AiOthello ai = new AiOthello(playernumber, piece); // maakt een AiForOthello object\
        int move = ai.AiMove(this, piece, 25); // roept de moveselectOthello methode aan van AiForOthello
        try { // probeert
            Connection.send("move " + move); // stuurt move + de waarde van move naar de server
        } catch (IOException e) { // als er een error is
            throw new RuntimeException(e); // print de error naar de console
        }
    }

    /**
     * @author Francois Dieleman
     * @param position
     * @param piece
     */
    @Override
    public void serverAdd(int position, char piece) { // maakt de serverAdd method
        add(piece, position); // roept de add methode aan van Othello
        flipPiece(position, piece); // roept de flipPiece methode aan van Othello
        convertToJButtons(); // roept de convertToJButtons methode aan van Othello
    }

    /**
     * @author Francois Dieleman
     * @param piece
     * @return oppPiece
     */
    @Override
    public char oppPiece(char piece){
        if(piece == '•'){return '◦';}
        return '•';
    }
}
