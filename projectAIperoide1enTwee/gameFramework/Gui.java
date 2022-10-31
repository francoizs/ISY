package gameFramework;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Gui {

    private static JFrame frame;
    private static JPanel panel;

    private static Boolean isAI;

    private static JButton[] JButtons;

    private static JPanel board;

    public static String userNamePub;



    public Gui() {
        create();

    }

    public static void create() {
        frame = new JFrame("Tic Tac Toe");
        panel = new JPanel();

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // set location of frame to center of screen
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // set look and feel of frame to look and feel of operating system
        } catch (Exception e) {
            System.out.println("Error setting native LAF: " + e); // print error message to user
        }
        SwingUtilities.updateComponentTreeUI(frame); // update components of frame

        frame.add(panel);
        startScreen();
        frame.setVisible(true);
    }

    public static void startScreen() {
        reset();
        JLabel userName = new JLabel("Gebruikersnaam");
        userName.setBounds(10, 20, 80, 25);
        userName.setFont(new Font("Arial", Font.PLAIN, 20));
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 135, 25);
        userText.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton connect = new JButton("Verbind met de server");
        connect.setBounds(10, 80, 200, 25);
        connect.setFont(new Font("Arial", Font.PLAIN, 20));
        connect.addActionListener(e -> {
            if (!Objects.equals(userText.getText(), "")) {

                if (userText.getText().contains(" ")) {
                    displayOnScreen("Gebruikersnaam mag geen spaties bevatten");
                } else {
                    try {
                        userNamePub = userText.getText();
                        Connection connection = new Connection();
                        connection.run();
                        Connection.login(userNamePub);
                        TimeUnit.MILLISECONDS.sleep(100);
                        if (Recieve.answers.get(Recieve.answers.size() -1).equals("OK")) {
                            displayOnScreen("Verbonden met de server");
                            playerScreen();
                        } else if (Recieve.answers.get(Recieve.answers.size() -1).contains("ERR duplicate name exists")) {
                            displayOnScreen("Deze gebruikersnaam bestaat al");
                        }
                        else {
                            displayOnScreen("Er ging iets mis, probeer het opnieuw");
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(frame, "Vul een gebruikersnaam in");
            }
        });

        panel.add(userName);
        panel.add(userText);
        panel.add(connect);

    }

    public static void playerScreen() throws InterruptedException {
        reset();

        JLabel player = new JLabel("Is de speler een AI?");
        player.setBounds(10, 20, 100, 40);
        player.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton yes = new JButton("Ja");
        yes.setBounds(10, 80, 100, 25);
        yes.setFont(new Font("Arial", Font.PLAIN, 20));
        yes.addActionListener(e -> {
            isAI = true;
            pauseScreen();
        });
        JButton no = new JButton("Nee");
        no.setBounds(120, 80, 100, 25);
        no.setFont(new Font("Arial", Font.PLAIN, 20));
        no.addActionListener(e -> {
            isAI = false;
            pauseScreen();
        });




        panel.add(player);
        panel.add(yes);
        panel.add(no);
    }

    public static void pauseScreen() {
        reset();
        JLabel game = new JLabel("Kies een spel:");
        game.setBounds(10, 20, 100, 40);
        game.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton TicTacToe = new JButton("Tic Tac Toe");
        TicTacToe.setBounds(10, 80, 200, 25);
        TicTacToe.setFont(new Font("Arial", Font.PLAIN, 20));
        TicTacToe.addActionListener(e -> {
            displayOnScreen("De server is er mee bezig...");
            try {
                Connection.send("subscribe tic-tac-toe");

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton Othello = new JButton("Othello");
        Othello.setBounds(10, 110, 200, 25);
        Othello.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel command = new JLabel("Of stuur een ander command naar de server:");
        command.setBounds(10, 140, 300, 100);
        command.setFont(new Font("Arial", Font.PLAIN, 20));
        Border border = command.getBorder();
        Border margin = new EmptyBorder(100,10,10,10);
        command.setBorder(new CompoundBorder(border, margin));
        JTextField commandText = new JTextField(20);
        commandText.setBounds(10, 180, 300, 25);
        commandText.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton send = new JButton("Verstuur");
        send.setBounds(10, 210, 200, 25);
        send.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel response = new JLabel();
        response.setBounds(10, 240, 100, 100);
        response.setFont(new Font("Arial", Font.PLAIN, 12));
        send.addActionListener(e -> {
            if (!Objects.equals(commandText.getText(), "")) {
                try {
                    Connection.send(commandText.getText());
                    TimeUnit.MILLISECONDS.sleep(100);
                    String responseConn = Recieve.answers.get(Recieve.answers.size() - 1);

                    response.setText(responseConn);


                    panel.revalidate();
                    panel.repaint();

                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                displayOnScreen("Vul een command in");
            }
        });

        panel.add(game);
        panel.add(TicTacToe);
        panel.add(Othello);
        panel.add(command);
        panel.add(commandText);
        panel.add(send);
        panel.add(response);

    }

    public static void gameScreen(int width, int height) {
        board = new JPanel();
        GridLayout gridLayout = new GridLayout(height, width);
        JButtons = new JButton[height * width];
        frame.remove(panel);
        board.setLayout(gridLayout);
        frame.add(board);
        for (int i = 0; i < width * height; i++) {
            JButtons[i] = new JButton();
            JButtons[i].setEnabled(false);
            JButtons[i].setText("");
            JButtons[i].setFont(new Font("Arial", Font.BOLD, 50));
            int finalI = i;
            JButtons[i].addActionListener(e -> {
                if (JButtons[finalI].isEnabled()) {
                    try {
                        Connection.send("move " + finalI);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        disableAllButtons();
                    }
                }
            });
            board.add(JButtons[i]);
            board.revalidate();
            board.repaint();
        }

    }

    public static void enableAllButtons() {
        for (JButton button : JButtons) {
            if (button.getText().equals("")) {
                button.setEnabled(true);
            }
        }
    }
    public static void disableAllButtons() {
        for (JButton button : JButtons) {
            button.setEnabled(false);
        }
    }

    public static void gameOver() {
        frame.remove(board);
        frame.add(panel);
        frame.setTitle("Tic Tac Toe");
        frame.revalidate();
        frame.repaint();
    }

    public static void serverAdd(int position, char piece) {
        JButtons[position].setText(String.valueOf(piece));
        JButtons[position].setEnabled(false);
    }


    public static void putOnTitle(String message) {
        frame.setTitle(message);
    }

    public static void displayOnScreen(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public static void reset() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }
    public static void repaint() {
        panel.revalidate();
        panel.repaint();
    }
}

