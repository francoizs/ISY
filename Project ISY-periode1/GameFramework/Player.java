/**
 * Object voor de spelers van het spel
 * @author Anton Bijker
 * @version 1.0
 */
public class Player extends Game
{
    boolean isAI;
    int playernumber;
    char piece;

    /**
     * Constructor voor het player object
     * @param playernumber nummer van speler om spelers apart te kunnen houden van elkaar
     * @param isAI geeft aan of de speler een AI is inplaats van een mens
     * @param piece het spel stuk behorende bij de speler
     * @author Anton Bijker
     */
    public Player(int playernumber, boolean isAI, char piece) {
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
