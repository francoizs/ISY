package gameFramework;

//145.33.225.170
// java -jar newgameserver-1.0.jar

import java.io.*;
import java.net.Socket;

/**
* de class die de connectie legt met de server
* @version 0.3
* @author Francois Dieleman
*/
public class Connection {
    private static Connection connection;

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
     * @author Francois Dieleman
     */
    public static void connect() { // connect to the server
        connection = new Connection(); // maakt een nieuwe connectie
        connection.run(); // roept de run methode aan
    }

    

    public void run(){ // de run methode die de commando's naar de server stuurt
        // try catch voor de input
        Recieve reciever = new Recieve(); // maakt de reciever voor de input
        reciever.start(); // start de reciever

    }
    public static void login(String userName) throws IOException { // de methode die de login stuurt
        output.println("login " + userName); // stuurt de login naar de server
    }

    public static void send(String command) throws IOException { // de methode die de commando's naar de server stuurt
        output.println(command); // stuurt de commando's naar de server
    }

    /**
     * @author Francois Dieleman
     * @param piece
     * @throws IOException
     */
    public static void subscribe(String gameName) throws IOException {
        Connection.send("subscribe " + gameName); // stuur een subscribe bericht naar de server
    }

    /**
     * @author Francois Dieleman
     * @param piece
     * @throws IOException
     */
    public static void challenge(String name, String gameName) throws IOException {
        Connection.send("challenge " + name + " " + gameName); // stuur een challenge bericht naar de server
    }


    
    public static String[] getPlayers() throws IOException {
        Connection.send("get playerlist"); // stuur een getPlayers bericht naar de server
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String fullPlayers = Recieve.answers.get(Recieve.answers.size() - 1);
        String[] splitplayers = fullPlayers.split(" ");
        String[] players = new String[splitplayers.length - 2];
        for (int i = 2; i < splitplayers.length; i++) {
            if (i == 2) {
                players[i - 2] = splitplayers[i].replace("[", "").replace("\"", "").replace(",", ""); // maakt de string voor het speltype
            } else if (i == splitplayers.length - 1) {
                players[i - 2] = splitplayers[i].replace("]", "").replace("\"", ""); // maakt de string voor het speltype
            } else {
                players[i - 2] = splitplayers[i].replace("\"", "").replace(",", ""); // maakt de string voor het speltype
            }
        }
        return players;
    }

    
}