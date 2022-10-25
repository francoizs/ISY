package ticTacToe;
import gameFramework.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
* Het spel zelf
* @version 0.2
*/

public class GameRunner{

    public GameRunner(){

    }

    public int[][] move(){
        int[][] placeHolder = new int[1][1];
        return placeHolder;
    }

    /**
     * Shows the startup menu of the game and allows you to pick the neccesary settings (interface).
     * @author Anton Bijker
     */
    public static void gamestartmenu() {
        Scanner in = new Scanner(System.in);
        System.out.println("Wilt u via de server spelen of lokaal? (server/lokaal)");
        String answer = in.nextLine();
        if (answer.equals("server")) {
            Connection connection = new Connection();
            connection.run();
        }
        Player player1=null;
        Player player2=null;
        System.out.println("Welkom bij Tictactoe!");
        String isAI = " ";
        while (!isAI.equals("n") && !isAI.equals("y")){      // Voor nu alleen implementatie voor non-AI dus y is hier geen goed antwoord
            System.out.println("Is de eerste speler een AI? y/n");
            isAI= in.nextLine();}
        if (isAI.equals("n")){
            player1 = new Player(1,'X');
        }
        if (isAI.equals("y")){
            player1 = new AiForTicTacToe(1, 'X');
        }
        String isAI2="";
        while (!isAI2.equals("n") && !isAI2.equals("y")){      // Voor nu alleen implementatie voor non-AI dus y is hier geen goed antwoord
            System.out.println("Is de tweede speler een AI? y/n");
            isAI2= in.nextLine();}
        if (isAI2.equals("n")){
            player2 = new Player(2,'O');
        }
        if (isAI2.equals("y")){
            player2 = new AiForTicTacToe(2,'O');
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
        TicTacToe newboard = new TicTacToe(3,3);
        System.out.println(newboard);
        int move=0;
        int move2=0;
        Scanner in = new Scanner(System.in);
        while (!newboard.isFull()){
            if (player1 instanceof AiForTicTacToe) {
                move = AiForTicTacToe.moveSelect(newboard, player1.piece);
                newboard.add(player1.piece, move);
            } else {
                System.out.println("Speler 1 kies een positie om een steen te plaatsen, kies van 1 tot 9: ");
                move = in.nextInt();
                while (!newboard.allowMove(move)) {
                    System.out.println("plek is bezet probeer opnieuw");
                    move = in.nextInt();
                }
                newboard.add(player1.piece, move);
            }
            System.out.println(newboard);
            if (newboard.win(player1.piece)) {System.out.println("Speler 1 is de winnaar!");break;}
            if (newboard.isFull()) break;
            if (player2 instanceof AiForTicTacToe) {
                move2 = AiForTicTacToe.moveSelect(newboard, player2.piece);
                newboard.add(player2.piece, move2);
            } else {
                System.out.println("Speler 2 kies een positie om een steen te plaatsen, kies van 1 tot 9: ");
                move2 = in.nextInt();
                while (!newboard.allowMove(move2)) {
                    System.out.println("plek is bezet probeer opnieuw");
                    move2 = in.nextInt();
                }
                newboard.add(player2.piece, move2);
            }
            System.out.println(newboard);
            if (newboard.win(player2.piece)){
                System.out.println("Speler 2 is de winnaar!"); break; }
        }
        if (newboard.isFull() && !newboard.win(player1.piece) && !newboard.win(player2.piece)) System.out.println("het bord is vol, gelijkspel!");;

    }
    /**
     * Checks shows different screens based on ending of the game.
     */
    public void endOfGame(){

    }
}
