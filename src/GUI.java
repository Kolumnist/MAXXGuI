/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public class GUI extends JFrame implements Serializable {

    private GameBoard board;

    private String manual_text = "Hallo! Herzlich willkommen zu 'MAXX'!"
            + "\nIn unserem Spiel können vier Spieler abwechselnd miteinander spielen. Diese Spieler sind als 'black', 'white', 'red' und 'yellow' konfiguriert."
            + "\nAlle Spieler fangen an einem unterschiedlichen Punkt des Spielfeldes an."
            + "\nZiel des Spieles ist es, 21-42 Punkte zu ergattern."
            + "\nDie Punkte setzen sich aus den Brüchen in den insgesamt 60-62 Buttons zusammen."
            + "\nDamit der Spieler auf einen Button springen kann, muss einfacherweise nur auf den Button geklickt werden."
            + "\n\nABER ACHTUNG!"
            + "\nDie Spieler können sich nur schräg über das Spielfeld bewegen."
            + "\n Desweiteren gibt es noch einen SPECIAL MOVE, welcher den Spieler eine bestimmte Bewegung ermöglicht, die den anderen Spielern nicht vergönnt ist."
            + "\nSo kann sich der weiße Spieler nach unten, der rote Spieler nach rechts, der gelbe Spieler nach links und der schwarze Spieler nach oben bewegen."
            + "\n\n"
            + "\nGewinner des Spiels ist derjenige, der mehr oder gleich 42 Punkte hat!\n";
    //File erstellen für Text mit for-schleife dann ausgeben in dem neuen Fenster!

    public JLabel currentPlayer_JLabel = new JLabel("Current Player:");
    public JLabel playerMoves_JLabel = new JLabel("Moves of current player:");
    public JTextField currentPlayer_JTextField = new JTextField();
    public JTextField playerMoves_JTextField = new JTextField();
    public JPanel board_JPanel;
    public JMenuBar menuBar_JMenuBar = new JMenuBar();
    public JMenu[] menus_JMenu = {new JMenu("More Information"), new JMenu("Game"), new JMenu("Exit")};

    //Constructor for the MainMenu
    public GUI(JMenuItem[] pJMenuItem) {
        setTitle("MAXXGuI");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setJMenuBar(createMenuBar(pJMenuItem));

        //create a picture
        JLabel picture;
        Icon icon = new ImageIcon(new ImageIcon(getClass().getResource("MAXXGuI.png")).getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT));
        picture = new JLabel(icon);
        picture.setIcon(icon);
        add(picture);

        //this ActionListener shows the manual
        pJMenuItem[0].addActionListener(e -> {
            JFrame myJFrame = new JFrame();
            myJFrame.add(new JTextArea(manual_text));
            myJFrame.setSize(1000, 300);
            myJFrame.setLocation(250, 100);
            myJFrame.setVisible(true);
        });

        // ### ActionListener für "Choose Game"
        pJMenuItem[1].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            myGame.loadGame(board);
        });

        //this ActionListener close the whole program
        pJMenuItem[3].addActionListener(e -> System.exit(0));
    }

    //Constructor for the Gameboard
    public GUI(String pTitle, JMenuItem[] pJMenuItem) {
        setTitle(pTitle);
        setSize(700, 700);
        setJMenuBar(createMenuBar(pJMenuItem));

        setLayout(new BorderLayout());

        //GridLayout for the Gameboard
        board_JPanel = new JPanel(new GridLayout(8, 8));
        add(board_JPanel, BorderLayout.CENTER);

        //creating a little console with JTextFields and JLabels to show the current player and it's moves
        JPanel terminal_JPanel = new JPanel(new GridLayout(2, 2));
        terminal_JPanel.add(currentPlayer_JLabel);
        terminal_JPanel.add(playerMoves_JLabel);
        terminal_JPanel.add(currentPlayer_JTextField);
        terminal_JPanel.add(playerMoves_JTextField);
        add(terminal_JPanel, BorderLayout.SOUTH);
        setVisible(true);

        pJMenuItem[1].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            try {
                myGame.saveGame(board);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        pJMenuItem[2].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            myGame.loadGame(board);
        });
    }

    private JMenuBar createMenuBar(JMenuItem[] pJMenuItem) {

        for (int i = 0; i < menus_JMenu.length; i++) {
            menuBar_JMenuBar.add(menus_JMenu[i]);
        }

        for (int i = 0; i < pJMenuItem.length; i++) {
            menus_JMenu[(i < 1) ? 0 : (i == pJMenuItem.length) ? 2 : 1].add(pJMenuItem[i]);
        }

        return menuBar_JMenuBar;
    }
}
