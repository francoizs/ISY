package gameFramework;

/**
 * @author Ihab Al-Safadi
 */
public class Coordinates  extends Game {
    private static int[] coordinates = new int[1];


    public static int[] XAndYCoordinates(int position){
        coordinates= coordinate(position);
        return coordinates;
    }


    public static int getX(){
        return coordinates[0];
    }

    public static int getY(){
        return coordinates[1];

    }

    @Override
    public void enableButtons(char piece) {

    }

    @Override
    public void moveAI(char piece, int playernumber) {

    }

    @Override
    public void serverAdd(int position, char piece) {

    }

    @Override
    public char oppPiece(char piece) {
        return 0;
    }
}
