package ticTacToe;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.text.Position;

import gameFramework.Connection;
import gameFramework.Game;
import gameFramework.Gui;

public class TicTacToe extends Game {

    public TicTacToe(int width, int height, char piece){
        super(width, height, piece);

    }



    /**
     * Adds a move to the board.
     * @authur Ihab Al-Safadi
     */

    public int[] coordinate(int position){
        /**
         * // Deze methode neemt de position die de speler wil doen, en geeft de coordinates op het board terug.
         * Vervolgens  worden de coordinates gestuurd naar de add methode om een zet te doen op het board
         */
        int counter =  0;
        int[] coordinate= {0,0};
        for (int row=0; row< getHeight(); row++){
            for (int col = 0; col< getWidth(); col++){
                counter ++;
                if (counter == position){coordinate[0] =row; coordinate[1] = col;break;}
            }
        }

        return coordinate;
    }
    /**
     *
     * @param position
     * @return
     * @authur Ihab Al-Safadi
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
     * @authur Ihab Al-Safadi
     */

    public boolean win(char piecs){
        return winHorizontal(piecs) || winvertical(piecs) || winDiagonalLeft(piecs) || winDiagonalRight(piecs);
    }

    /**
     *
     * @param piecs // Dit wordt toegevoergd vanuit de game class bv X en O.
     * @return
     * @authur Ihab Al-Safadi
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
     * @authur Ihab Al-Safadi
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
     * @authur Ihab Al-Safadi
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
     * @authur Ihab Al-Safadi
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
    
    @Override
    public void moveAI(char piece, int playernumber) { // maakt de moveAI methode

        TicTacToe convertedboard = convertTicTacToeBoard(Game.width, Game.height); // maakt een nieuw bord met de breedte en hoogte van het bord
        int move = AiForTicTacToe.moveSelect(convertedboard, piece) - 1; // maakt een int met de waarde van de move van de AiForTicTacToe
        try { // probeert
            Connection.send("move " + move); // stuurt move + de waarde van move naar de server
        } catch (IOException e) { // als er een error is
            throw new RuntimeException(e); // print de error naar de console
        }
    }

    private static TicTacToe convertTicTacToeBoard(int width, int height) { // maakt de convertBoard methode
        TicTacToe board = new TicTacToe(width, height, piece); // maakt een nieuw bord met de breedte en hoogte van het bord
        for (int i = 0; i < width * height; i++) { // loopt door het bord
            if (Gui.JButtons[i].getText().equals("X")) { // kijkt of de tekst van de button X is
                board.add('X', i + 1); // voegt X toe aan het bord op de positie van i + 1
            } else if (Gui.JButtons[i].getText().equals("O")) { // kijkt of de tekst van de button O is
                board.add('O', i + 1); // voegt O toe aan het bord op de positie van i + 1
            }

        }
        return board; // geeft het bord terug
    }

    @Override
    public void enableButtons(char piece) { // maakt de enableAllButtons methode
        for (JButton button : Gui.JButtons) { // loopt door de array
            if (button.getText().equals("")) { // kijkt of de tekst van de button leeg is
                button.setEnabled(true); // zet de button op enabled
            }
        }
    }
    
    @Override
    public void serverAdd(int position, char piece) {
        Gui.JButtons[position].setText(String.valueOf(piece)); // zet de tekst van de button op de waarde van piece
        Gui.JButtons[position].setEnabled(false); // zet de button op disabled
    }

    @Override
    public char oppPiece(char piece) {
        char oppPiece = ' '; // maakt een char met de waarde van een spatie
        if (piece == 'X') { // kijkt of de waarde van piece X is
            oppPiece = 'O'; // zet de waarde van oppPiece op O
        } else if (piece == 'O') { // kijkt of de waarde van piece O is
            oppPiece = 'X'; // zet de waarde van oppPiece op X
        }
        return oppPiece; // geeft de waarde van oppPiece terug
    }
}
