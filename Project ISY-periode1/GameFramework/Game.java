
import java.util.Scanner;
/**
* Het spel zelf
* @version 0.2
*/

public class Game extends Main{
    public Game(){

    }

    public int[][] move(){
        int[][] placeHolder = new int[1][1];
        return placeHolder;
    }

    /**
     * Shows the startup menu of the game and allows you to pick the neccesary settings (interface).
     * @author Anton Bijker
     */
    public static void gamestartmenu(){
        Player player1=null;
        Player player2=null;
        Scanner in = new Scanner(System.in);
        System.out.println("Welkom bij Tictactoe!");
        String isAI = " ";
        while (!isAI.equals("n")){      // Voor nu alleen implementatie voor non-AI dus y is hier geen goed antwoord
            System.out.println("Is de eerste speler een AI? y/n");
            isAI= in.nextLine();}
        if (isAI.equals("n")){
            player1 = new Player(1,false,'X');
        }
        if (isAI.equals("y")){
            player1 = new Player(1,true,'X');
        }
        String isAI2="";
        while (!isAI2.equals("n")){      // Voor nu alleen implementatie voor non-AI dus y is hier geen goed antwoord
            System.out.println("Is de tweede speler een AI? y/n");
            isAI2= in.nextLine();}
        if (isAI2.equals("n")){
            player2 = new Player(2,false,'O');
        }
        if (isAI2.equals("y")){
            player2 = new Player(2,true,'O');
        }
        System.out.println("De setup is klaar, uw spel zal nu starten");
        movemenu(player1,player2);

    }

    /**
     * speelt het spel en vraagt moves aan van spelers  (mist nog wel een manier om te weten wanneer het spel over is)
     * @author Anton Bijker
     * @param player1 de 2 spelers die het spelen
     * @param player2 de 2 spelers die het spelen     *
     */

    public static void movemenu(Player player1, Player player2){
        Board newboard = new Board(3,3);
        System.out.println(newboard);
        int move=0;
        int move2=0;
        Scanner in = new Scanner(System.in);
        //while (!Board.win)
            System.out.println("Speler 1 kies een positie om een steen te plaatsen, kies van 1 tot 9: ");
            move = in.nextInt();
            newboard.add(player1.piece,move);
            System.out.println(newboard);
            System.out.println("Speler 2 kies een positie om een steen te plaatsen, kies van 1 tot 9: ");
            move2 = in.nextInt();
            newboard.add(player2.piece,move2);
            System.out.println(newboard);

    }
    /**
     * Checks shows different screens based on ending of the game.
     */
    public void endOfGame(){

    }
}
