package gameFramework;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;



import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * de class die de GUI maakt
 * @version 0.3
 * @author Francois Dieleman
 */
public class Gui {

    private static JFrame frame; // maakt het frame
    private static JPanel panel; // maakt het panel

    public static Boolean isAI; // maakt een boolean die aangeeft of de speler tegen de AI speelt

    public static JButton[] JButtons; // maakt een array van JButtons

    private static JPanel board; // maakt het board

    public static String userNamePub; // maakt een string die de gebruikersnaam opslaat

    private static int width; // maakt een int die de breedte van het board opslaat
    private static int height; // maakt een int die de hoogte van het board opslaat

    private static String playername; // maakt een string aan



    public Gui() { // maakt de constructor
        create(); // roept de create methode aan

    }

    private void create() { // maakt de create methode
        frame = new JFrame("Game"); // maakt het frame
        panel = new JPanel(); // maakt het panel

        frame.setSize(600, 600); // zet de grootte van het frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // zet de close operatie op exit
        frame.setLocationRelativeTo(null); // set location of frame to center of screen
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // set look and feel of frame to look and feel of operating system
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e); // print error message to user
        }
        SwingUtilities.updateComponentTreeUI(frame); // update components of frame

        frame.add(panel); // voegt het panel toe aan het frame
        startScreen(); // roept de startScreen methode aan
        frame.setVisible(true); // maakt het frame zichtbaar
    }

    private void startScreen() { // maakt de startScreen methode
        reset(); // roept de reset methode aan
        JLabel userName = new JLabel("Gebruikersnaam"); // maakt een label met de tekst Gebruikersnaam
        userName.setBounds(10, 20, 80, 25); // zet de positie en grootte van de label
        userName.setFont(new Font("Arial", Font.PLAIN, 20)); // zet de font van de label

        JTextField userText = new JTextField(20); // maakt een textfield
        userText.setBounds(100, 20, 135, 25); // zet de positie en grootte van de textfield
        userText.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de textfield

        JButton connect = new JButton("Verbind met de server"); // maakt een button met de tekst Verbind met de server
        connect.setBounds(10, 80, 200, 25); // zet de positie en grootte van de button
        connect.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        connect.addActionListener(e -> { // voegt een actionlistener toe aan de button
            if (!Objects.equals(userText.getText(), "")) { // als de textfield niet leeg is

                if (userText.getText().contains(" ")) { // als de textfield een spatie bevat
                    displayOnScreen("Gebruikersnaam mag geen spaties bevatten"); // roept de displayOnScreen methode aan met de tekst Gebruikersnaam mag geen spaties bevatten
                } else { // als de textfield geen spatie bevat
                    try {
                        userNamePub = userText.getText(); // zet de gebruikersnaam gelijk aan de textfield
                        Connection.connect(); // roept de connect methode aan
                        Connection.login(userNamePub); // roept de login methode aan met de gebruikersnaam
                        TimeUnit.MILLISECONDS.sleep(200); // wacht 100 milliseconden
                        if (Recieve.answers.get(Recieve.answers.size() -1).equals("OK")) { // als de laatste antwoord van de server OK is
                            displayOnScreen("Verbonden met de server"); // roept de displayOnScreen methode aan met de tekst Verbonden met de server
                            playerScreen(); // roept de playerScreen methode aan
                        } else if (Recieve.answers.get(Recieve.answers.size() -1).contains("ERR duplicate name exists")) { // als de laatste antwoord van de server ERR duplicate name exists bevat
                            displayOnScreen("Deze gebruikersnaam bestaat al"); // roept de displayOnScreen methode aan met de tekst Deze gebruikersnaam bestaat al
                        }
                        else {
                            displayOnScreen("Er ging iets mis, probeer het opnieuw"); // roept de displayOnScreen methode aan met de tekst Er ging iets mis, probeer het opnieuw
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace(); // print de error naar de console
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex); // print de error naar de console
                    }
                }
            }
            else {
                displayOnScreen("Gebruikersnaam mag niet leeg zijn"); // roept de displayOnScreen methode aan met de tekst Gebruikersnaam mag niet leeg zijn
            }
        });

        panel.add(userName); // voegt de label toe aan het panel
        panel.add(userText); // voegt het textfield toe aan het panel
        panel.add(connect); // voegt de button toe aan het panel

    }

    private void playerScreen() throws InterruptedException { // maakt de playerScreen methode
        reset(); // roept de reset methode aan

        JLabel player = new JLabel("Is de speler een AI?"); // maakt een label met de tekst Is de speler een AI?
        player.setBounds(10, 20, 100, 40); // zet de positie en grootte van de label
        player.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de label

        JButton yes = new JButton("Ja"); // maakt een button met de tekst Ja
        yes.setBounds(10, 80, 100, 25); // zet de positie en grootte van de button
        yes.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        yes.addActionListener(e -> { // voegt een actionlistener toe aan de button
            isAI = true; // zet isAI op true
            try {
                pauseScreen();
            } catch (IOException e1) {
                e1.printStackTrace();
            } // roept de pauseScreen methode aan
        });
        JButton no = new JButton("Nee"); // maakt een button met de tekst Nee
        no.setBounds(120, 80, 100, 25); // zet de positie en grootte van de button
        no.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        no.addActionListener(e -> { // voegt een actionlistener toe aan de button
            isAI = false; // zet isAI op false
            try {
                pauseScreen();
            } catch (IOException e1) {
                e1.printStackTrace();
            } // roept de pauseScreen methode aan
        });




        panel.add(player); // voegt de label toe aan het panel
        panel.add(yes); // voegt de button toe aan het panel
        panel.add(no); // voegt de button toe aan het panel
    }

    private void pauseScreen() throws IOException { // maakt de pauseScreen methode
        reset(); // roept de reset methode aan

        JLabel game = new JLabel("Kies een spel:"); // maakt een label met de tekst Kies een spel:
        game.setBounds(10, 20, 100, 40); // zet de positie en grootte van de label
        game.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de label

        JButton TicTacToe = new JButton("Tic Tac Toe"); // maakt een button met de tekst Tic Tac Toe
        TicTacToe.setBounds(10, 80, 200, 25); // zet de positie en grootte van de button
        TicTacToe.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        TicTacToe.addActionListener(e -> { // voegt een actionlistener toe aan de button
            displayOnScreen("De server is er mee bezig..."); // roept de displayOnScreen methode aan met de tekst De server is er mee bezig...
            try {
                Connection.subscribe("tic-tac-toe");
            } catch (IOException e1) {
                e1.printStackTrace();
            } // stuurt subscribe tic-tac-toe naar de server
        });
        JButton Othello = new JButton("Othello"); // maakt een button met de tekst Othello
        Othello.setBounds(10, 110, 200, 25); // zet de positie en grootte van de button
        Othello.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        Othello.addActionListener(e -> {
            displayOnScreen("De server is er mee bezig..."); // roept de displayOnScreen methode aan met de tekst De server is er mee bezig...
            try {
                Connection.subscribe("reversi");
            } catch (IOException ex) {
                throw new RuntimeException(ex); // print de error naar de console
            }
        });

        JLabel player = new JLabel("Wil je toch veranderen van speler?"); // maakt een label met de tekst Is de speler een AI?
        player.setBounds(10, 20, 100, 40); // zet de positie en grootte van de label
        player.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de label

        JButton yes = new JButton("AI"); // maakt een button met de tekst Ja
        JButton no = new JButton("Human"); // maakt een button met de tekst Nee

        yes.setBounds(10, 80, 100, 25); // zet de positie en grootte van de button
        yes.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        yes.addActionListener(e -> { // voegt een actionlistener toe aan de button
            isAI = true; // zet isAI op true
            yes.setForeground(new Color(0, 255, 0)); // zet de button op enabled
            no.setForeground(new Color(0, 0, 0)); // zet de button op disabled
            panel.remove(yes); // verwijderd de button van het panel
            panel.remove(no); // verwijderd de button van het panel
            panel.add(no); // voegt de button toe aan het panel
            panel.add(yes); // voegt de button toe aan het panel

        });

        no.setBounds(120, 80, 100, 25); // zet de positie en grootte van de button
        no.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        no.addActionListener(e -> { // voegt een actionlistener toe aan de button
            isAI = false; // zet isAI op false
            yes.setForeground(new Color(0, 0, 0)); // zet de button op disabled
            no.setForeground(new Color(0, 255, 0)); // zet de button op enabled
            panel.remove(yes); // verwijderd de button van het panel
            panel.remove(no); // verwijderd de button van het panel
            panel.add(no); // voegt de button toe aan het panel
            panel.add(yes); // voegt de button toe aan het panel
        });
        if (isAI) { // als isAI true is
            yes.setForeground(new Color(0, 255, 0)); // zet de button op enabled
            no.setForeground(new Color(0, 0, 0)); // zet de button op disabled
        } else { // als isAI false is
            yes.setForeground(new Color(0, 0, 0)); // zet de button op disabled
            no.setForeground(new Color(0, 255, 0)); // zet de button op enabled
        }

        JLabel challenge = new JLabel("Challenge iemand"); // maakt een label met de tekst Wil je een challenge?
        challenge.setBounds(10, 20, 100, 40); // zet de positie en grootte van de label
        challenge.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de label

        JButton challengeButton = new JButton("Challenge"); // maakt een button met de tekst Challenge
        challengeButton.setBounds(10, 80, 200, 25); // zet de positie en grootte van de button
        challengeButton.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        challengeButton.addActionListener(e -> { // voegt een actionlistener toe aan de button
            try {
                challengeScreen();
            } catch (IOException e1) {
                e1.printStackTrace();
            } // roept de challengeScreen methode aan
        });

        JLabel command = new JLabel("Of stuur een ander command naar de server:"); // maakt een label met de tekst Of stuur een ander command naar de server:
        command.setBounds(10, 140, 300, 100); // zet de positie en grootte van de label
        command.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de label
        Border border = command.getBorder(); // maakt een border
        Border margin = new EmptyBorder(60, 10, 10, 10); // maakt een margin
        command.setBorder(new CompoundBorder(border, margin)); // zet de border en margin van de label
        
        JTextField commandText = new JTextField(20); // maakt een textfield
        commandText.setBounds(10, 180, 300, 25); // zet de positie en grootte van de textfield
        commandText.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de textfield

        JButton send = new JButton("Verstuur"); // maakt een button met de tekst Verstuur
        send.setBounds(10, 210, 200, 25); // zet de positie en grootte van de button
        send.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button

        JLabel response = new JLabel(); // maakt een label
        response.setBounds(10, 240, 100, 100); // zet de positie en grootte van de label
        response.setFont(new Font("Arial", Font.PLAIN, 12)); // zet het font van de label
        send.addActionListener(e -> { // voegt een actionlistener toe aan de button
            if (!Objects.equals(commandText.getText(), "")) { // kijkt of de textfield niet leeg is
                try {
                    Connection.send(commandText.getText()); // stuurt de tekst van de textfield naar de server
                    TimeUnit.MILLISECONDS.sleep(100); // wacht 100 milliseconden
                    String responseConn = Recieve.answers.get(Recieve.answers.size() - 1); // maakt een string met de laatste antwoord van de server

                    response.setText(responseConn); // zet de tekst van de label op de string
                    panel.remove(response); // verwijderd de label van het panel
                    panel.add(response); // voegt de label toe aan het panel

                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex); // print de error naar de console
                }
            } else {
                displayOnScreen("Vul een command in"); // roept de displayOnScreen methode aan met de tekst Vul een command in
            }
        });

        panel.add(game); // voegt de label toe aan het panel
        panel.add(TicTacToe); // voegt de button toe aan het panel
        panel.add(Othello); // voegt de button toe aan het panel
        panel.add(player); // voegt de label toe aan het panel
        panel.add(yes); // voegt de button toe aan het panel
        panel.add(no); // voegt de button toe aan het panel
        panel.add(challenge); // voegt de label toe aan het panel
        panel.add(challengeButton); // voegt de button toe aan het panel
        panel.add(command); // voegt de label toe aan het panel
        panel.add(commandText); // voegt de textfield toe aan het panel
        panel.add(send); // voegt de button toe aan het panel
        panel.add(response); // voegt de label toe aan het panel

    }
    
    public void challengeScreen() throws IOException {
        panel.removeAll(); // verwijderd alle componenten van het panel
        panel.revalidate(); // valideerd het panel
        panel.repaint(); // repainted het panel

        JLabel challenge = new JLabel("Challenge iemand"); // maakt een label met de tekst Challenge iemand
        challenge.setBounds(10, 20, 100, 40); // zet de positie en grootte van de label
        challenge.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de label

        JLabel game = new JLabel("Welk spel wil je spelen?"); // maakt een label met de tekst Welk spel wil je spelen?
        game.setBounds(50, 40, 200, 40); // zet de positie en grootte van de label
        game.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de label
        Border border = game.getBorder(); // maakt een border
        Border margin = new EmptyBorder(40, 10, 10, 10); // maakt een margin
        game.setBorder(new CompoundBorder(border, margin)); // zet de border en margin van de label

        JButton TicTacToe = new JButton("Tic Tac Toe"); // maakt een button met de tekst Tic Tac Toe
        TicTacToe.setBounds(10, 80, 200, 25); // zet de positie en grootte van de button
        TicTacToe.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        TicTacToe.addActionListener(e -> { // voegt een actionlistener toe aan de button
            displayOnScreen("De server is er mee bezig..."); // roept de displayOnScreen methode aan met de tekst De server is er mee bezig...
            try {
                Connection.challenge(playername, "tic-tac-toe");
            } catch (IOException e1) {
                e1.printStackTrace();
            } // stuurt subscribe tic-tac-toe naar de server
        });
        JButton Othello = new JButton("Othello"); // maakt een button met de tekst Othello
        Othello.setBounds(10, 110, 200, 25); // zet de positie en grootte van de button
        Othello.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de button
        Othello.addActionListener(e -> {
            displayOnScreen("De server is er mee bezig..."); // roept de displayOnScreen methode aan met de tekst De server is er mee bezig...
            try {
                Connection.challenge(playername, "reversi");
            } catch (IOException ex) {
                throw new RuntimeException(ex); // print de error naar de console
            }
        });

        String[] playerlist = Connection.getPlayers(); // maakt een array met de spelers
        JComboBox<String> players = new JComboBox<String>(playerlist); // maakt een combobox
        players.setBounds(10, 60, 200, 25); // zet de positie en grootte van de combobox
        players.setFont(new Font("Arial", Font.PLAIN, 20)); // zet het font van de combobox
        players.addActionListener(e -> { // voegt een actionlistener toe aan de combobox
            playername = (String) players.getSelectedItem(); // zet de geselecteerde speler in de variabele playername
            panel.add(game); // voegt de label toe aan het panel
            panel.add(TicTacToe); // voegt de button toe aan het panel
            panel.add(Othello); // voegt de button toe aan het panel
        });
        
        panel.add(challenge); // voegt de label toe aan het panel
        panel.add(players); // voegt de combobox toe aan het panel
    }

    public static void gameScreen(int width, int height) { // maakt de gameScreen methode
        Gui.width = width; // zet de width van de Gui op de width van de methode
        Gui.height = height; // zet de height van de Gui op de height van de methode
        board = new JPanel(); // maakt een nieuw panel
        GridLayout gridLayout = new GridLayout(Gui.height, Gui.width); // maakt een gridlayout met de breedte en hoogte van het bord
        JButtons = new JButton[Gui.height * Gui.width]; // maakt een array met de grootte van het bord
        frame.remove(panel); // verwijderd het huidige panel
        board.setLayout(gridLayout); // zet de layout van het panel op de gridlayout
        frame.add(board); // voegt het panel toe aan het frame
        for (int i = 0; i < Gui.width * Gui.height; i++) { // loopt door de array
            JButtons[i] = new JButton(); // maakt een nieuwe button
            JButtons[i].setEnabled(false); // zet de button op disabled
            JButtons[i].setText(""); // zet de tekst van de button op leeg
            JButtons[i].setFont(new Font("Arial", Font.BOLD, 50)); // zet het font van de button
            int finalI = i; // maakt een int met de waarde van i
            if (!isAI) {
                JButtons[i].addActionListener(e -> { // voegt een actionlistener toe aan de button
                    if (JButtons[finalI].isEnabled()) { // kijkt of de button enabled is
                        try {
                            System.out.println("move " + finalI); // print move + de waarde van i naar de console
                            Connection.send("move " + (finalI)); // stuurt move + de waarde van i naar de server
                        } catch (IOException ex) {
                            throw new RuntimeException(ex); // print de error naar de console
                        } finally {
                            disableAllButtons(); // roept de disableAllButtons methode aan
                        }
                    }
                });


            }
            board.add(JButtons[i]); // voegt de button toe aan het panel
            board.revalidate(); // herlaad het panel
            board.repaint(); // herlaad het panel
        }


    }

    
    public static void disableAllButtons() { // maakt de disableAllButtons methode
        for (JButton button : JButtons) { // loopt door de array
            button.setEnabled(false); // zet de button op disabled
        }
    }

    public static void gameOver() { // maakt de gameOver methode
        frame.remove(board); // verwijderd het huidige board
        frame.add(panel); // voegt het panel toe aan het frame
        frame.setTitle("Game"); //zet de titel van het frame op Tic Tac Toe
        frame.revalidate(); // herlaad het frame
        frame.repaint(); // herlaad het frame
    }


    public static void putOnTitle(String message) {
        frame.setTitle(message);
    } // zet de titel van het frame op de message

    public static void displayOnScreen(String message) {
        JOptionPane.showMessageDialog(frame, message);
    } // maakt een popup met de message

    private void reset() { // maakt de reset methode
        panel.removeAll(); // verwijderd alle componenten van het panel
        panel.revalidate(); // herlaad het panel
        panel.repaint(); // herlaad het panel
    }
    
}

