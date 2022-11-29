package gameFramework;

/**
 * Object voor de spelers van het spel
 * @author Anton Bijker
 * @version 1.0
 */
public class Player
{
    private boolean isAI;
    private int playernumber;
    private char piece;

    /**
     *
     * @param playernumber
     * @param piece
     * @author Anton Bijker
     */
    public Player(int playernumber, char piece) {
        this.playernumber = playernumber;
        this.isAI=isAI;
        this.piece=piece;
    }

    /**
     * Getter voor playernumber
     * @return playernumber
     * @author Aaldert Kroes
     */
    public int getPlayernumber(){
        return this.playernumber;
    }

    /**
     * Getter voor piece
     * @return piece
     * @author Aaldert Kroes
     */
    public char getPiece(){
        return this.piece;
    }
}
