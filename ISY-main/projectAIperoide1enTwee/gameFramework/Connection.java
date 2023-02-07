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
    private ClientValuesSingleton singleton = ClientValuesSingleton.getInstance();
    private Socket socket; // maakt de socket voor de verbinding
    {
        try {
            // de poort waarop de server luistert
            int PORT = singleton.getConnectionPort();
            // het IP-adres van de server
            String HOST = singleton.getConnectionHost();
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

    public static void connect() {
        connection = new Connection();
        connection.run();
    }

    public static void subscribe(String gameName) throws IOException {
        Connection.send("subscribe " + gameName);
    }

    public static void challenge(String name, String gameName) throws IOException {
        Connection.send("challenge " + name + " " + gameName);
    }

    public static String[] getPlayers() throws IOException {
        Connection.send("get playerlist");
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
                String player = splitplayers[i].replace("[", "").replace("\"", "").replace(",", ""); // maakt de string voor het speltype
                if (player.equals(Gui.userNamePub)) {
                    players[i - 2] = player + " (you)";
                } else {
                    players[i - 2] = player;
                }
            } else if (i == splitplayers.length - 1) {
                String player = splitplayers[i].replace("]", "").replace("\"", "").replace(",", ""); // maakt de string voor het speltype
                if (player.equals(Gui.userNamePub)) {
                    players[i - 2] = player + " (you)";
                } else {
                    players[i - 2] = player;
                }
            } else {
                String player = splitplayers[i].replace("\"", "").replace(",", ""); // maakt de string voor het speltype
                if (player.equals(Gui.userNamePub)) {
                    players[i - 2] = player + " (you)";
                } else {
                    players[i - 2] = player;
                }
            }
        }
        return players;
    }
}