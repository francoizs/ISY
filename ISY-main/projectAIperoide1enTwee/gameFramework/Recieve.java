package gameFramework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import othello.Othello;
import ticTacToe.TicTacToe;

/**
* de class die de alles leest van de server
* @version 0.4
* @author Francois Dieleman
*/
class Recieve extends Thread { // maakt de reciever voor de input
    private char piece; // maakt het stukje voor de speler
    private String playerToMove; // maakt de string voor de speler die aan de beurt is
    Player player; // maakt de speler
    private Game game; // maakt het spel
    public static ArrayList<String> answers = new ArrayList<>();
    public void run() { // de run methode die de input van de server leest
        try { // try catch voor de input
            while (true) { // een while loop die de input van de server leest

                if (Connection.input.ready()) { // als de input niet leeg is
                    String input = Connection.input.readLine(); // maakt de string voor de input
                    keepTrack(input); // geeft de input door aan de keepTrack methode
                    System.out.println(input); // print de input

                    if (input.contains("SVR GAME MATCH")) { // als de input een match is
                        String[] parsed = input.split(" "); // split de input
                        match(parsed);
                    }
                    if (input.contains("SVR GAME YOURTURN")) { // als de input je beurt is
                        yourTurn();
                    }
                    if (input.contains("SVR GAME MOVE")) { // als de input een move is
                        String[] parsed = input.split(" "); // split de input
                        move(parsed);
                    }
                    if (input.contains("DRAW") || input.contains("WIN") || input.contains("LOSS")) { // als de input een winnaar of gelijkspel is
                        gameOver(input);
                    }
                    if (input.contains("SVR GAME CHALLENGE")) { // als de input een challenge is
                        String[] parsed = input.split(" "); // split de input
                        challenge(parsed);
                    }
                }
            }
        } catch (IOException | InterruptedException e) { // catch voor de input
            System.out.println("Error: " + e);
        }
    }

    private void keepTrack(String answer) { // de methode die de input opslaat
        answers.add(answer); // voegt de input toe aan de arraylist
    }
    
    private void match(String[] parsedInput) {
        String gametype = parsedInput[6].replace("\"", "").replace(",", ""); // maakt de string voor het speltype
        playerToMove = parsedInput[4].replace("\"", "").replace(",", ""); // maakt de string voor de speler die aan de beurt is
        if (gametype.equals("Tic-tac-toe")) { // als het speltype tic-tac-toe is
            if (Objects.equals(playerToMove, Gui.userNamePub)) { // als de speler aan de beurt is
                player = new Player(1, 'X', Gui.userNamePub); // zet het stukje op X
            } else { // als de tegenstander aan de beurt is
                player = new Player(2, 'O', Gui.userNamePub); // zet het stukje op O
            }
            game = new TicTacToe(3, 3, piece); // maakt het spel
            Gui.putOnTitle("Tic Tac Toe - " + playerToMove + " is aan de beurt"); // zet de titel op de eerste speler
        } else if (gametype.equals("Reversi")) {
            if (Objects.equals(playerToMove, Gui.userNamePub)) {
                player = new Player(1, '◦', Gui.userNamePub); // zet het stukje op ◦
            } else {
                player = new Player(2, '•', Gui.userNamePub); // zet het stukje op •
            }
            game = new Othello(8, 8, piece);
            Gui.putOnTitle("Othello - " + playerToMove + " is aan de beurt"); // zet de titel op de eerste speler
        }
    }
    
    private void yourTurn() throws InterruptedException { // de methode die de beurt van de speler regelt
        Gui.putOnTitle("U bent aan de beurt"); 
        if (!Gui.isAI) { // als de tegenstander geen AI is
            game.enableButtons(player.getPiece()); // zet de knoppen aan
        }
        else if (Gui.isAI) { // als de tegenstander een AI is
            Gui.disableAllButtons(); // zet de knoppen uit
            TimeUnit.MILLISECONDS.sleep(100); // wacht 100 milliseconden
            game.moveAI(player.getPiece(), player.getPlayernumber()); // laat de AI een zet doen
        }
    }
    
    private void move(String[] parsedInput) {
        int move = Integer.parseInt(parsedInput[6].replace("\"", "").replace(",", "")); // maakt de int voor de move
        String serverPlayer = parsedInput[4].replace("\"", "").replace(",", ""); // maakt de string voor de speler
        if (serverPlayer.equals(player.getName())) { // als de speler de move heeft gedaan
            Gui.putOnTitle("Tegenstander is aan de beurt"); 
            game.serverAdd(move, player.getPiece());
        } else { // als de tegenstander de move heeft gedaan
            game.serverAdd(move, game.oppPiece(player.getPiece()));
        }

    }
    
    public void gameOver(String input) throws IOException {
        Board.movesCounter = 0; // zet de movesCounter op 0
        Gui.putOnTitle("Game over");

        Gui.displayOnScreen(input); // zet de game over
        Gui.gameOver(); // pauzeer het scherm
    }

    public void challenge(String[] parsedInput){
        String challenger = parsedInput[4].replace("\"", "").replace(",", ""); // maakt de string voor de challenger
        String gameType = parsedInput[8].replace("\"", "").replace("}", ""); // maakt de string voor het speltype
        String challengeNumber = parsedInput[6].replace("\"", "").replace(",", ""); // maakt de string voor de challengenumber
        Gui.challengeRecieve(challenger, gameType, challengeNumber);
    }
}