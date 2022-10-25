/**
 * Het onderdeel dat zich bezighoud met de server verbinding
 * java -jar newgameserver-*VERSION*.jar
 * 145.33.225.170
 */
import java.net.*;
import java.io.*;
/**
* de class die de connectie legt met de server
* @version 0.2
* @author Francois Dieleman
*/
public class Connection extends Game {

    public static final int PORT = 7789; // de poort waarop de server luistert
    public static final String HOST = "localhost"; // het IP-adres van de server

    public static String userName;

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
        try
        { // try catch voor de input
            System.out.println("Welkom! Voer je naam in:"); // vraagt de naam van de speler
            userName = stdIn.readLine(); // leest de naam van de speler
            String CLIinput; // maakt de string voor de input
            Recieve reciever = new Recieve(); // maakt de reciever voor de input
            reciever.start(); // start de reciever
            output.println("login " + userName); // stuurt de naam naar de server

            while (true) { // een while loop die de commando's naar de server stuurt
                if ((CLIinput = stdIn.readLine()) != null) { // als de input niet leeg is
                    output.println(CLIinput); // stuur de input naar de server
                }
            }
        }
        catch (IOException e) // catch voor de input
        {
            System.out.println("IO error in client thread");
        }
    }


}

/**
* de class die de alles leest van de server
* @version 0.3
* @author Francois Dieleman
*/
class Recieve extends Thread { // maakt de reciever voor de input
    public void run() { // de run methode die de input van de server leest
        try { // try catch voor de input
            Board board = null; // maakt het bord
            while (true) { // een while loop die de input van de server leest

                if (Connection.input.ready()) { // als de input niet leeg is
                    String input = Connection.input.readLine(); // maakt de string voor de input
                    System.out.println(input); // print de input

                    if (input.contains("SVR GAME MATCH")) { // als de input een match is
                        String[] parsed = input.split(" "); // split de input
                        String gametype = parsed[6].replace("\"", "").replace(",", ""); // maakt de string voor het speltype
                        if (gametype.equals("Tic-tac-toe")) { // als het speltype tic-tac-toe is
                            board = new Board(3, 3); // maakt het bord
                        }
                    }

                    if (input.contains("SVR GAME MOVE")) { // als de input een move is
                        String[] parsed = input.split(" "); // split de input
                        String player = parsed[4].replace("\"", "").replace(",", ""); // maakt de string voor de speler
                        int move = Integer.parseInt(parsed[6].replace("\"", "").replace(",", "")); // maakt de int voor de move
                        assert board != null; // zorgt dat het bord niet leeg is
                        if (player.equals(Connection.userName)) { // als de speler de speler is
                            board.add('X', move); // voegt de X toe aan het bord
                        } else { // als de speler de tegenstander is
                            board.add('O', move); // voegt de O toe aan het bord
                        }
                        System.out.println(board); // print het bord
                    }
                }
            }
        } catch (IOException e) { // catch voor de input
            System.out.println("Error: " + e);
        }
    }
}
