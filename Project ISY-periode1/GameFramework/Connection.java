/**
 * Het onderdeel dat zich bezighoud met de server verbinding
 */
import java.net.*;
import java.io.*;
public class Connection extends Game {

    private static final int PORT = 7789;
    private static final String HOST = "localhost";
    private static Socket socket;
    private BufferedReader input;
    private InputStreamReader inputReader;
    private PrintWriter output;
    private OutputStreamWriter outputWriter;


    /**
     * Establishes a connection through a socket to the gameserver.
     * @author Francois Dieleman
     */

    public Connection(){
        
        try
        {
            socket = new Socket(HOST, PORT);

        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        // tijdelijke methode om te testen
        while (true) {
            String recieved = receive();
            System.out.println(recieved);
            if (recieved.contains("New Game Server")) {
                send("login *onze groep*");
            }
            if (recieved.contains("WIN") || recieved.contains("LOSS") || recieved.contains("DRAW")) {
                break;
            }
            
        }
        

        // close the connection
        try
        {
            input.close();
            inputReader.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    /**
     * Sends a message to the server.
     * @author Francois Dieleman
     */
    public void send(String message) {
        try {
            outputWriter = new OutputStreamWriter(socket.getOutputStream());
            output = new PrintWriter(outputWriter, true);
            output.println(message);

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Expects an answer from the server.
     * @author Francois Dieleman
     */
    public String receive() {
        String message = "";
        try {
            inputReader = new InputStreamReader(socket.getInputStream());
            input = new BufferedReader(inputReader);
            message = input.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }   
}
