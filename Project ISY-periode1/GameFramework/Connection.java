
/**
 * Het onderdeel dat zich bezighoud met de server verbinding
 * java -jar newgameserver-*VERSION*.jar
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
    public static final String HOST = "145.33.225.170"; // het ip adres van de server

    static Socket socket; // maakt de socket voor de verbinding
    {
            try {
                socket = new Socket(HOST, PORT);
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
            String CLIinput; // maakt de string voor de input
            Recieve reciever = new Recieve(); // maakt de reciever voor de input
            reciever.start(); // start de reciever
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
* @version 0.1
* @author Francois Dieleman
*/
class Recieve extends Thread { // maakt de reciever voor de input
    public void run() { // de run methode die de input van de server leest
        try { // try catch voor de input
            while (true) { // een while loop die de input van de server leest
                if (Connection.input.ready()) { // als de input niet leeg is
                    System.out.println(Connection.input.readLine()); // print de input
                }
            }
        } catch (IOException e) { // catch voor de input
            System.out.println("Error: " + e);
        }
    }
}