package gameFramework;
/**
 * Het onderdeel dat zich bezighoud met de server verbinding
 * java -jar newgameserver-1.0.jar
 * 145.33.225.170
 */

import ticTacToe.GameRunner;
import ticTacToe.TicTacToe;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

/**
* de class die de connectie legt met de server
* @version 0.2
* @author Francois Dieleman
*/
public class Connection extends GameRunner {

    public static final int PORT = 7789; // de poort waarop de server luistert
    public static final String HOST = "game.bier.dev"; // het IP-adres van de server

    static Socket socket; // maakt de socket voor de verbinding
    {
            try {
                socket = new Socket(HOST, PORT);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    static BufferedReader input; // maakt de lezer voor inputReader
    {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static InputStreamReader inputReader; // maakt de lezer die de data van de server krijgt
    {
        try {
            inputReader = new InputStreamReader(socket.getInputStream()); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static PrintWriter output; // maakt de schrijver voor de commando's naar de server
    {
            try {
                output = new PrintWriter(socket.getOutputStream(), true); 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    static OutputStreamWriter outputWriter; // maakt de verbinding tussen de commando's naar de server
    {
            try {
                outputWriter = new OutputStreamWriter(socket.getOutputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    static BufferedReader stdIn; // maakt de lezer de stdInReader
    {
        stdIn = new BufferedReader(new InputStreamReader(System.in));
    }
    static InputStreamReader stdInReader; // maakt de lezer voor de CLI
    {
        stdInReader = new InputStreamReader(System.in);
    }

    /**
     * geeft de commando's aan de server
     * @author Francois Dieleman
     */

    public void run(){ // de run methode die de commando's naar de server stuurt
        // try catch voor de input
        String CLIinput; // maakt de string voor de input
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

    String playerToMove;
    String firstPlayer;
    String secondPlayer;

    String opponentName;
    static ArrayList<String> answers = new ArrayList<>();
    public void run() { // de run methode die de input van de server leest
        try { // try catch voor de input
            TicTacToe board = null; // maakt het bord
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
                            Gui.gameScreen(3, 3);
                        }
                        Gui.putOnTitle("Tic Tac Toe - " + playerToMove + " is aan de beurt"); // zet de titel op de eerste speler
                    }
                    if (input.contains("SVR GAME YOURTURN")) {
                        Gui.enableAllButtons();
                    }
                    if (input.contains("SVR GAME MOVE")) { // als de input een move is
                        int moves = Board.movesCounter++; // maakt de int voor de moves
                        String[] parsed = input.split(" "); // split de input
                        String player = parsed[4].replace("\"", "").replace(",", ""); // maakt de string voor de speler
                        int move = Integer.parseInt(parsed[6].replace("\"", "").replace(",", "")); // maakt de int voor de move
                        firstPlayer = playerToMove; // maakt de string voor de eerste speler

                        if (Objects.equals(firstPlayer, Gui.userNamePub)) {
                            secondPlayer = opponentName;
                        } else {
                            secondPlayer = Gui.userNamePub;
                        }
                        if (moves % 2 == 1) { // als de moves even zijn
                            Gui.putOnTitle("Tic Tac Toe - " + firstPlayer + " is aan de beurt"); // zet de titel op de tweede speler
                            Gui.serverAdd(move, 'X'); // zet de X op het bord
                        } else { // als de speler O is
                            Gui.putOnTitle("Tic Tac Toe - " + secondPlayer + " is aan de beurt");
                            Gui.serverAdd(move, 'O'); // zet de O op het bord
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
        }
    }
    public static void keepTrack(String answer) {

        answers.add(answer);
    }
}