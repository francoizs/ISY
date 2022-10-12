/**
 * Testruimte voor het gehele ISY-project (onvolledig)
 * @author Aaldert Kroes
 * @version 1.0
 */
/*
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class Gametest {

    @Test
    @DisplayName("Maken van een nieuw bord.")
    public void testMakeBoard(){
        Board board = new Board(3,3);

        assertEquals(3, board.getWidth());
        assertEquals(3, board.getHeight());
    }

    @Test
    @DisplayName("Test de coordinaat controle in Board.java")
    public void testCoordinate(){
        Board board = new Board(3,3);
        // Eerste array test
        int[] expectedCase1 = {1,1};
        boolean boolCase1 = Arrays.equals(expectedCase1, board.coordinate(5));
        assertEquals(true, boolCase1);
        //assertEquals(expectedCase1, board.coordinate(5));     <- Geeft fout: vergelijkt 2 references, geen arrays

        // Tweede array test
        int[] expectedCase2 = {2,2};
        boolean boolCase2 = Arrays.equals(expectedCase2, board.coordinate(9));
        assertEquals(true, boolCase2);


    }

    @Test
    @DisplayName("Testen van unieke spelers")
    public void testPlayer(){
        Player player1 = new Player(1, true, 'X');
        Player player2 = new Player(2, false, 'O');

        // Speler 1
        assertEquals(1, player1.getPlayernumber());
        assertEquals('X', player1.getPiece());
        //assertEquals(2, player1.getPlayernumber());   <- Geeft fout: juist
        //assertEquals('O', player1.getPiece());        <- Geeft fout: juist

        // Speler 2
        assertEquals(2, player2.getPlayernumber());
        assertEquals('O', player2.getPiece());
        //assertEquals(1, player2.getPlayernumber());   <- Geeft fout: juist
        //assertEquals('X', player2.getPiece());        <- Geeft fout: juist
    }
}
*/