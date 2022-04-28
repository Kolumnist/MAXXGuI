/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class MainMenu extends JFrame implements Runnable, Serializable {

    private JButton play;
    private JComboBox playerSelection;
    private String gameName, gameDelete;
    private GameBoard gb;

    public MainMenu() {
        setTitle("MAXXGuI");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create and add JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu info = new JMenu("More Information");
        JMenu game = new JMenu("Game");
        JMenu exit = new JMenu("Exit");
        menuBar.add(info);
        menuBar.add(game);
        menuBar.add(exit);

        JMenu goTo = new JMenu("GoTo");
        JMenu delete = new JMenu("delete");

        //create and add JMenu
        JMenuItem manual = new JMenuItem("Manual");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem name = new JMenuItem(gameName);
        JMenuItem away = new JMenuItem(gameDelete);
        JMenuItem close = new JMenuItem("Close all Window");
        info.add(manual);
        game.add(save);
        game.add(goTo);
        game.add(delete);
        exit.add(close);
        delete.add(away);
        goTo.add(name);

        //set JMenuBar into JFrame
        setJMenuBar(menuBar);

        setSize(700, 700);
        setVisible(true);


        // Array for the player selection
        String comboBoxListe[] = {"2 Spieler", "3 Spieler", "4 Spieler"};

        //create an Label so people will know what they can select with the JComboBox
        JLabel question = new JLabel("Wie viele Spieler?");
        add(question);

        //create JComboBox with all the players
        playerSelection = new JComboBox(comboBoxListe);
        add(playerSelection);
        setVisible(true);

        //create a picture
        JLabel picture;
        Icon icon = new ImageIcon(new ImageIcon(getClass().getResource("MAXXGuI.png")).getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT));
        picture = new JLabel(icon);
        picture.setIcon(icon);
        add(picture);
        setVisible(true);
        // picture.setLocation(50,50);

        //create a button
        play = new JButton();
        play.setText("Game Start");
        play.setPreferredSize(new Dimension(100, 30));
        play.setVisible(true);
        play.setBackground(Color.LIGHT_GRAY);
        add(play);
        setVisible(true);

        //this ActionListener shows the manual
        manual.addActionListener(e -> {
            JFrame myJFrame = new JFrame();
            JPanel panel = new JPanel();

            JTextArea manual1 = new JTextArea("Hallo! Herzlich willkommen zu 'MAXX'!"
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
                    + "\nGewinner des Spiels ist derjenige, der mehr oder gleich 42 Punkte hat!\n");
            panel.add(manual1);
            myJFrame.setSize(900, 300);
            myJFrame.add(panel);
            myJFrame.setVisible(true);
        });
        
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Savegame myGame = new Savegame();
            }
        });

        //this ActionListener close the whole program
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Thread thread = new Thread(this);
        thread.start();

    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/icon.png")));
    }


    @Override
    public void run() {
        play.addActionListener(e -> {
            if (playerSelection.getSelectedIndex() == 0) {
                gb = new GameBoard(2);
            } else if (playerSelection.getSelectedIndex() == 1) {
                gb = new GameBoard(3);
            } else if (playerSelection.getSelectedIndex() == 2) {
                gb = new GameBoard(4);
            }
        });

    }
}

