package gameFramework;

/**
 * @author Ihab Al-Safadi
 */

public class Game extends Board{
    public Game(int size) {
        super(size);
    }

    public Game(int width, int height) {
        super(width, height);
    }

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
     * @param teken
     * @param position
     * @author Ihab Al-Safadi
     */
    public void add(char teken, int position){
        /**
         * voeg een zet toe aan het baord
         */
        int[] coordinates = coordinate(position);
        if(getBoard()[coordinates[0]][coordinates[1]] ==' '){getBoard()[coordinates[0]][coordinates[1]] = teken;}
        else {System.out.println("Deze plek is niet beschikbaar");}
    }

    /**
     * Removes a move from the board.
     */
    public void remove(char teken, int position){
        int[] coordinates = coordinate(position);
        if(getBoard()[coordinates[0]][coordinates[1]] !=' '){getBoard()[coordinates[0]][coordinates[1]] = ' ';}
        else {System.out.println("Deze plek is niet beschikbaar");}
    }
}
