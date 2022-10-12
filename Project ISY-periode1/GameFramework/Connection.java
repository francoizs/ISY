
/**
 * Het onderdeel dat zich bezighoud met de server verbinding
 * java -jar newgameserver-*VERSION*.jar
 */
import java.net.*;
import java.io.*;
public class Connection extends Game {

    public static final int PORT = 7789;
    public static final String HOST = "145.33.225.170";

    static Socket socket;
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
    static BufferedReader input;
    {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static InputStreamReader inputReader;
    {
        try {
            inputReader = new InputStreamReader(socket.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    static PrintWriter output;
    {
            try {
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    static OutputStreamWriter outputWriter;
    {
            try {
                outputWriter = new OutputStreamWriter(socket.getOutputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    static BufferedReader stdIn;
    {
        stdIn = new BufferedReader(new InputStreamReader(System.in));
    }
    static InputStreamReader stdInReader;
    {
        stdInReader = new InputStreamReader(System.in);
    }
    /**
     * Establishes a connection through a socket to the gameserver.
     * @author Francois Dieleman
     */

    public Connection(){
        
        try
        {
            String CLIinput;
            Recieve reciever = new Recieve();
            reciever.start();
            while (true) {
                if ((CLIinput = stdIn.readLine()) != null) {
                    output.println(CLIinput);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }

    
}


class Recieve extends Thread {
    public void run() {
        try {
            while (true) {
                if (Connection.input.ready()) {
                    System.out.println(Connection.input.readLine());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}