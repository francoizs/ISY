/**
 * Object voor de spelers van het spel
 * @author Anton Bijker
 * @version 1.0
 */
public class Player
{
    boolean isAI;
    int playernumber;
    char piece;

    /**
     * Constructor voor het player object
     * @param playernumber nummer van speler om spelers apart te kunnen houden van elkaar
     * @param piece het spel stuk behorende bij de speler
     * @author Anton Bijker
     */
    public Player(int playernumber, char piece) {
        this.playernumber = playernumber;
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
