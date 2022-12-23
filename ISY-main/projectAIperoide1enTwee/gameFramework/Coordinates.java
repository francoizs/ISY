package gameFramework;

import static gameFramework.Game.height;
import static gameFramework.Game.width;

/**
 * @author Ihab Al-Safadi
 */
public class Coordinates {
    private static int[] coordinates = new int[2];

    /**
     *
     * Deze methode neemt de position die de speler wil doen, en geeft de coordinates op het board terug.
     * Vervolgens worden de coordinates gestuurd naar de add methode om een zet te doen op het board
     * @param position
     * @return
     * @author Ihab AL-Safadi
     */
    public static void XAndYCoordinates(int position){
        int counter =  -1;
        for (int row=0; row< height; row++){
            for (int col = 0; col< width; col++){
                counter ++;
                if (counter == position){coordinates[0] =row; coordinates[1] = col; break;}
            }
        }
    }



    public static int getX(){
        return coordinates[0];
    }

    public static int getY(){
        return coordinates[1];

    }
}
