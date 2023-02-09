package ticTacToe;
import java.io.IOException;

import javax.swing.JButton;

import gameFramework.Connection;
import gameFramework.Game;
import gameFramework.Gui;

public class TicTacToe extends Game {

    public TicTacToe(int width, int height, char piece){
        super(width, height, piece);

    }



    /**
     * Adds a move to the board.
     * @author Ihab Al-Safadi
     */


    /**
     *
     * @param position
     * @return
     * @author Ihab Al-Safadi
     */

    public boolean allowMove(int position){

        int[] check_coordinate = coordinate(position); // retrieve the coordinate
        if(getBoard()[check_coordinate[0]][check_coordinate[1]] != ' '){return false;}
        return true;
    }

    /**
     *
     * @param piecs
     * @return
     * @author Ihab Al-Safadi
     */

    public boolean win(char piecs){
        return winHorizontal(piecs) || winvertical(piecs) || winDiagonalLeft(piecs) || winDiagonalRight(piecs);
    }

    /**
     *
     * @param piecs // Dit wordt toegevoergd vanuit de game class bv X en O.
     * @return
     * @author Ihab Al-Safadi
     */
    public Boolean winvertical(char piecs){

        for(int row = 0; row < getHeight()-2; row++){
            for(int col = 0; col < getWidth(); col++){
                if(getBoard()[row][col] == piecs
                        && getBoard()[row+1][col] == piecs
                        && getBoard()[row+2][col] == piecs){ return true;}
            }
        }
        return false;
    }

    /**
     *
     * @param piecs
     * @return
     * @author Ihab Al-Safadi
     */
    public Boolean winHorizontal(char piecs){

        for(int row = 0; row < getHeight(); row++){
            for(int col = 0; col < getWidth()-2; col++){
                if(getBoard()[row][col] == piecs &&
                        getBoard()[row][col+1] == piecs &&
                        getBoard()[row][col+2] == piecs){return true;}
            }
        }
        return false;
    }

    /***
     *
     * @param piecs
     * @return
     * @author Ihab Al-Safadi
     */
    public Boolean winDiagonalLeft(char piecs){

        for(int row = 0; row < getHeight()-2; row++){
            for(int col = 0; col < getWidth()-2; col++){
                if(getBoard()[row][col] == piecs &&
                        getBoard()[row+1][col+1] == piecs &&
                        getBoard()[row+2][col+2] == piecs){return true;}
            }

        }
        return false;
    }

    /***
     * @author Ihab Al-Safadi
     * @param piecs
     * @return
     */

    public Boolean winDiagonalRight(char piecs) {

        for (int row = 0; row < getHeight() - 2; row++) {
            for (int col = 0; col < getWidth() - 2; col++) {
                if (getBoard()[row][getWidth() - 1] == piecs &&
                        getBoard()[row + 1][getWidth() - 2] == piecs &&
                        getBoard()[row + 2][getWidth() - 3] == piecs) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /***
     * @author Francois Dieleman
     * @param piece
     * @param playernumber
     */

    @Override
    public void moveAI(char piece, int playernumber) { // maakt de moveAI methode

        AiForTicTacToe ai = new AiForTicTacToe(playernumber, piece); // maakt een nieuwe AiForTicTacToe
        int move = ai.moveSelect(this, piece); // maakt een int met de waarde van de move van de AiForTicTacToe
        System.out.println(move);
        try { // probeert
            Connection.send("move " + move); // stuurt move + de waarde van move naar de server
        } catch (IOException e) { // als er een error is
            throw new RuntimeException(e); // print de error naar de console
        }
    }

    /***
     * @author Francois Dieleman
     * @param piece
     */
    @Override
    public void enableButtons(char piece) { // maakt de enableAllButtons methode
        for (JButton button : Gui.JButtons) { // loopt door de array
            if (!button.getText().equals("")) { // kijkt of de tekst van de button niet leeg is
                button.setEnabled(false); // zet de button op disabled
            } else { // als de tekst van de button leeg is
                button.setEnabled(true); // zet de button op enabled
            }
        }
    }
    
    /***
     * @author Francois Dieleman
     * @param position
     * @param piece
     */
    @Override
    public void serverAdd(int position, char piece) {
        add(piece, position);
        Gui.JButtons[position].setText(String.valueOf(piece)); // zet de tekst van de button op de waarde van piece
        Gui.JButtons[position].setEnabled(false); // zet de button op disabled
    }

    /***
     * @author Francois Dieleman
     * @param piece
     * @return oppPiece
     */
    @Override
    public char oppPiece(char piece) {
        if(piece == 'X'){return 'O';}
        return 'X';
    }
}
