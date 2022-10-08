public class Player extends Game {

    private boolean isAI;
    private int playernumber;
    private char piece;

    public Player(int playernumber, boolean isAI, char piece) {
        this.playernumber = playernumber;
        this.isAI=isAI;
        this.piece=piece;
    }


}
