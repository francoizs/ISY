package gameFramework;

/**
 * Object voor de spelers van het spel
 * @author Anton Bijker
 * @version 1.0
 */
public class Player
{
    private int playernumber;
    private char piece;
    private String name;

    /**
     *
     * @param playernumber
     * @param piece
     * @author Anton Bijker, Francois Dieleman
     */
    public Player(int playernumber, char piece, String name) {
        this.playernumber = playernumber;
        this.piece = piece;
        this.name = name;
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
    public char getPiece() {
        return this.piece;
    }
    
    /**
     * @author Francois Dieleman
     * @return name
     */
    public String getName() {
        return this.name;
    }
}
