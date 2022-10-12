
/**
 * Het onderdeel dat zich bezighoud met de server verbinding
 * java -jar newgameserver-*VERSION*.jar
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
    private BufferedReader scanner;
    private InputStreamReader scannerReader;


    /**
     * Establishes a connection through a socket to the gameserver.
     * @author Francois Dieleman
     */

    public Connection(){
        
        try
        {
            socket = new Socket(HOST, PORT);
            inputReader = new InputStreamReader(socket.getInputStream());
            input = new BufferedReader(inputReader);
            outputWriter = new OutputStreamWriter(socket.getOutputStream());
            output = new PrintWriter(outputWriter, true);
            scanner = new BufferedReader(new InputStreamReader(System.in));
            scannerReader = new InputStreamReader(System.in);

            int counter = 0;

        
            // tijdelijke methode om te testentry {
                
            while (true) {
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                while (input.ready()) {
                    String line = input.readLine();
                    System.out.println(line);
                    counter++;
                    if (counter == 2) {
                        String message = scanner.readLine();
                        output.println(message);

                    }
                    else if (line.contains("YOURTURN")) {
                        String message = scanner.readLine();
                        output.println(message);
                    }
                    else if (line.contains("ERR")) {
                        String message = scanner.readLine();
                        output.println(message);
                    }
                    else if (line.contains("WIN") || line.contains("LOSE") || line.contains("DRAW")) {
                        // System.exit(0);
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e);
        }
    }

    
}
