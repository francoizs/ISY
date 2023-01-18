package gameFramework;

import othello.AiOthello;
import othello.Othello;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Project intelligente Systemen
 * @version 0.2
 * @author Ihab Al-Safadi, Anton Bijker, Aaldert Kroes, Mart de Vries (voeg je eigen naam toe als je dit leest)
 */
public class Main{


    public static void main(String[] args) throws IOException {

//        Gui gui = new Gui();
        int potjes = 0;
        while (potjes < 10) {
            Othello othello = new Othello(8, 8, '•');
            othello.startPositions();
            Player player1 = new Player(1, '◦', "AI");
            Player player2 = new Player(2, '•', "AI");

            potjes ++;
            while (!othello.isFull()) {

                //Player1
                AiOthello ai = new AiOthello(player1.getPlayernumber(), player1.getPiece());
                int move1 = ai.AiMove(othello, player1.getPiece(), 1);
                if (move1 != 0) {
                    while (!othello.allowMoveOthello(move1, (player1.getPiece()))) {

                        move1 = ai.AiMove(othello, player1.getPiece(), 1);
                    }
                }
                othello.add(player1.getPiece(), move1);
                othello.flipPiece(move1, player1.getPiece());
                System.out.println(othello.toString());

                // player2
                AiOthello ai2 = new AiOthello(player2.getPlayernumber(), player2.getPiece());
                int move2 = ai2.AiMove(othello, player2.getPiece(), 1);
                if (move2 != 0) {
                    while (!othello.allowMoveOthello(move2, player2.getPiece())) {

                        move2 = ai2.AiMove(othello, player2.getPiece(), 1);
                    }
                }
                othello.add(player2.getPiece(), move2);
                othello.flipPiece(move2, player2.getPiece());
                System.out.println(othello.toString());
            }

            Path fileName = Path.of("/ResultsAi/result.txt"); //path van het bestand waarin de data wordt opgeslagen.

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.toFile(), true));
            writer.append("\n");
            String ply1 = String.valueOf("Player: " + player1.getPlayernumber() + " Score is: " + othello.pieceCounter(player1.getPiece()));
            String ply2 = String.valueOf("Player: " + player2.getPlayernumber() + " Score is: " + othello.pieceCounter(player2.getPiece()));
            String result = ply1 + " || " + ply2 + " || nr_potje: " + potjes;
            if (potjes == 1){writer.append("\n");writer.append("Begin test");writer.append("\n");}
//            if (potjes == 1){writer.append("\n");writer.append("Begin");writer.append("\n");}
//            if (potjes == 50){writer.append("\n");writer.append("Mid");writer.append("\n");}
//            if (potjes == 80){writer.append("\n");writer.append("Einde");writer.append("\n");}
            if (potjes == 100){writer.append("\n");writer.append("Einde test");writer.append("\n");}
            else {writer.append(result);}
            writer.close();
        }
    }
}
