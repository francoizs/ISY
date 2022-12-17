package gameFramework;

//145.33.225.170
// java -jar newgameserver-1.0.jar

import java.io.*;
import java.net.Socket;

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
                String HOST = "game.bier.dev";
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