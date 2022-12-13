package gameFramework;

//145.33.225.170
// java -jar newgameserver-1.0.jar

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import othello.Othello;
import ticTacToe.TicTacToe;

/**
* de class die de connectie legt met de server
* @version 0.2
* @author Francois Dieleman
*/
public class Connection {

    private Socket socket; // maakt de socket voor de verbinding
    {
            try {
                // de poort waarop de server luistert
                int PORT = 7789;
                // het IP-adres van de server
                String HOST = "145.33.225.170";
                socket = new Socket(HOST, PORT);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    public static BufferedReader input; // maakt de lezer voor inputReader
    {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static PrintWriter output; // maakt de schrijver voor de commando's naar de server

    {
        try {
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * geeft de commando's aan de server
     * @author Francois Dieleman
     */

    public void run(){ // de run methode die de commando's naar de server stuurt
        // try catch voor de input
        Recieve reciever = new Recieve(); // maakt de reciever voor de input
        reciever.start(); // start de reciever

    }
    public static void login(String userName) throws IOException {
        output.println("login " + userName);
    }
    public static void send(String command) throws IOException {
        output.println(command);
    }
    
}

/**
* de class die de alles leest van de server
* @version 0.3
* @author Francois Dieleman
*/
class Recieve extends Thread { // maakt de reciever voor de input

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
                        String gametype = parsed[6].replace("\"", "").replace(",", ""); // maakt de string voor het speltype
                        playerToMove = parsed[4].replace("\"", "").replace(",", ""); // maakt de string voor de speler die aan de beurt is
                        opponentName = parsed[8].replace("\"", "").replace("}", ""); // maakt de string voor de tegenstander
                        if (gametype.equals("Tic-tac-toe")) { // als het speltype tic-tac-toe is
                            game = new TicTacToe(3, 3); // maakt het spel
                            Gui.putOnTitle("Tic Tac Toe - " + playerToMove + " is aan de beurt"); // zet de titel op de eerste speler
                        }
                        else if (gametype.equals("Reversi")) {
                            game = new Othello(8, 8);
                            Gui.putOnTitle("Othello - " + playerToMove + " is aan de beurt"); // zet de titel op de eerste speler
                        }
                    }
                    if (input.contains("SVR GAME YOURTURN")) { // als de input je beurt is
                        if (Game.gameName.equals("TicTacToe")) {
                            game.enableButtons('X'); // zet alle knoppen aan
                            if (Gui.isAI) { // als de tegenstander een AI is
                                TimeUnit.MILLISECONDS.sleep(100); // wacht 1000 milliseconden
                                if (playerToMove.equals(Gui.userNamePub)) { // als de speler aan de beurt is
                                    game.moveAI('X'); // laat de AI een zet doen
                                } else { // als de tegenstander aan de beurt is
                                    game.moveAI('O'); // laat de AI een zet doen
                                }
                            }
                        }
                        else if (Game.gameName.equals("Othello")) {
                            if (playerToMove.equals(Gui.userNamePub)) { // als de speler aan de beurt is
                                game.enableButtons('◦'); // zet de knoppen aan

                            } else { // als de tegenstander aan de beurt is
                                game.enableButtons('•'); // zet de knoppen aan
                            }
                            if (Gui.isAI) { // als de tegenstander een AI is
                                TimeUnit.MILLISECONDS.sleep(100); // wacht 1000 milliseconden
                                // if (playerToMove.equals(Gui.userNamePub)) { // als de speler aan de beurt is
                                //     Othello.moveAI('X'); // laat de AI een zet doen
                                // } else { // als de tegenstander aan de beurt is
                                //     Othello.moveAI('O'); // laat de AI een zet doen
                                // }
                            }
                        }
                    }
                    if (input.contains("SVR GAME MOVE")) { // als de input een move is
                        int moves = Board.movesCounter++; // maakt de int voor de moves
                        String[] parsed = input.split(" "); // split de input
                        int move = Integer.parseInt(parsed[6].replace("\"", "").replace(",", "")); // maakt de int voor de move
                        secondPlayer = playerToMove; // maakt de string voor de eerste speler

                        if (Objects.equals(firstPlayer, Gui.userNamePub)) { // als de eerste speler de gebruiker is
                            firstPlayer = opponentName; // maakt de string voor de tweede speler
                        } else { // als de eerste speler de tegenstander is
                            firstPlayer = Gui.userNamePub; // maakt de string voor de tweede speler
                        }
                        if (moves % 2 == 0) { // als de moves even zijn
                            if (Game.gameName.equals("TicTacToe")) {

                                Gui.putOnTitle(firstPlayer + " is aan de beurt"); // zet de titel op de eerste speler
                                game.serverAdd(move, 'X'); // zet de X op het bord
                            }
                            else if (Game.gameName.equals("Othello")) {
                                game.serverAdd(move, '◦'); // zet de • op het bord
                            }
                            

                        } else { // oneven
                            if (Game.gameName.equals("TicTacToe")) {
                                Gui.putOnTitle(secondPlayer + " is aan de beurt"); // zet de titel op de tweede speler
                                game.serverAdd(move, 'O'); // zet de O op het bord
                            }
                            else if (Game.gameName.equals("Othello")) {
                                game.serverAdd(move, '•'); // zet de ○ op het bord
                            }
                        }

                    }
                    if (input.contains("DRAW") || input.contains("WIN") || input.contains("LOSS")) { // als de input een winnaar of gelijkspel is
                        Board.movesCounter = 0; // zet de movesCounter op 0
                        Gui.putOnTitle("Tic Tac Toe - Game over");

                        Gui.displayOnScreen(input); // zet de game over
                        Gui.gameOver(); // pauzeer het scherm

                    }
                }
            }
        } catch (IOException e) { // catch voor de input
            System.out.println("Error: " + e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void keepTrack(String answer) { // de methode die de input opslaat
        answers.add(answer); // voegt de input toe aan de arraylist
    }
}