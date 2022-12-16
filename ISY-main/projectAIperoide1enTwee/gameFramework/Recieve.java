package gameFramework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import othello.Othello;
import ticTacToe.TicTacToe;

/**
* de class die de alles leest van de server
* @version 0.3
* @author Francois Dieleman
*/
class Recieve extends Thread { // maakt de reciever voor de input
    private char piece; // maakt het stukje voor de speler
    private String playerToMove; // maakt de string voor de speler die aan de beurt is
    private String firstPlayer; // maakt de string voor de eerste speler
    private String secondPlayer; // maakt de string voor de tweede speler
    private Game game; // maakt het spel

    private String opponentName; // maakt de string voor de tegenstander
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
        opponentName = parsedInput[8].replace("\"", "").replace("}", ""); // maakt de string voor de tegenstander
        if (gametype.equals("Tic-tac-toe")) { // als het speltype tic-tac-toe is
            piece = 'X'; // zet het stukje op X
            game = new TicTacToe(3, 3, piece); // maakt het spel
            Gui.putOnTitle("Tic Tac Toe - " + playerToMove + " is aan de beurt"); // zet de titel op de eerste speler
        } else if (gametype.equals("Reversi")) {
            piece = '◦';
            game = new Othello(8, 8, piece);
            Gui.putOnTitle("Othello - " + playerToMove + " is aan de beurt"); // zet de titel op de eerste speler
        }
    }
    
    private void yourTurn() throws InterruptedException {
            if (playerToMove.equals(Gui.userNamePub)) { // als de speler aan de beurt is
                game.enableButtons(piece); // zet de knoppen aan

            } else { // als de tegenstander aan de beurt is
                game.enableButtons(game.oppPiece(piece)); // zet de knoppen aan
            }
            if (Gui.isAI) { // als de tegenstander een AI is
                TimeUnit.MILLISECONDS.sleep(100); // wacht 1000 milliseconden
                if (playerToMove.equals(Gui.userNamePub)) { // als de speler aan de beurt is
                    game.moveAI(piece, 1); // laat de AI een zet doen
                } else { // als de tegenstander aan de beurt is
                    game.moveAI(game.oppPiece(piece), 2); // laat de AI een zet doen
                }
            }
    }
    
    private void move(String[] parsedInput) {
        int moves = Board.movesCounter++; // maakt de int voor de moves
        int move = Integer.parseInt(parsedInput[6].replace("\"", "").replace(",", "")); // maakt de int voor de move
        secondPlayer = playerToMove; // maakt de string voor de eerste speler

        if (Objects.equals(firstPlayer, Gui.userNamePub)) { // als de eerste speler de gebruiker is
            firstPlayer = opponentName; // maakt de string voor de tweede speler
        } else { // als de eerste speler de tegenstander is
            firstPlayer = Gui.userNamePub; // maakt de string voor de tweede speler
        }
        if (moves % 2 == 0) { // als de moves even zijn
                Gui.putOnTitle(firstPlayer + " is aan de beurt"); // zet de titel op de eerste speler
                game.serverAdd(move, piece); // zet de X op het bord

        } else { // oneven
                Gui.putOnTitle(secondPlayer + " is aan de beurt"); // zet de titel op de tweede speler
                game.serverAdd(move, game.oppPiece(piece)); // zet de O op het bord
        }
    }
    
    public void gameOver(String input) {
        Board.movesCounter = 0; // zet de movesCounter op 0
        Gui.putOnTitle("Tic Tac Toe - Game over");

        Gui.displayOnScreen(input); // zet de game over
        Gui.gameOver(); // pauzeer het scherm
    }
}