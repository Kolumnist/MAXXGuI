/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 1 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements Runnable {

    private JButton play;
    private JComboBox playerSelection;
    private Player[] players;

    public MainMenu(Player[] players) {
        setTitle("MAXXGuI");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for (Player p : players) {
            this.players = players;
        }

        // create and add JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu info = new JMenu("More Information");
        JMenu exit = new JMenu("Exit");
        menuBar.add(info);
        menuBar.add(exit);

        //create and add JMenu
        JMenuItem manual = new JMenuItem("Manual");
        JMenuItem close = new JMenuItem("Close all Windows");
        info.add(manual);
        exit.add(close);

        //set JMenuBar into JFrame
        setJMenuBar(menuBar);

        setSize(700, 700);
        setLocation(700, 300);
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
        Icon icon;
        icon = new ImageIcon(getClass().getResource("pictureXX.png"));
        picture = new JLabel(icon);
        picture.setSize(350, 350);
        add(picture);
        // picture.setLocation(50,50);

        //create a button
        play = new JButton();
        play.setText("Game Start");
        play.setSize(200, 200);
        play.setVisible(true);
        play.setBackground(Color.LIGHT_GRAY);
        play.setLocation(650, 650);
        add(play);

        //this ActionListener shows the manual
        manual.addActionListener(e -> {
            JFrame myJFrame = new JFrame();
            JPanel panel = new JPanel();

            JTextArea manual1 = new JTextArea("Hallo! Herzlich willkommen zu 'MAXX'!"
                    + "\nIn unserem Spiel können vier Spieler abwechselnd miteinander spielen. Diese Spieler sind als 'black', 'white', 'red' und 'yellow' konfiguriert."
                    + "\nAlle Spieler fangen an einem unterschiedlichen Punkt des Spielfeldes an."
                    + "\nZiel des Spieles ist es, so viele Punkte wie möglich abzustauben."
                    + "\nDie Punkte setzen sich aus den Brüchen in den insgesamt 60-62 Buttons zusammen."
                    + "\nDamit der Spieler auf einen Button springen kann, muss einfacherweise nur auf den Button geklickt werden."
                    + "\n\nABER ACHTUNG!"
                    + "\nDie Spieler können sich nur schräg über das Spielfeld bewegen."
                    + "\n Desweiteren gibt es noch einen SPECIAL MOVE, welcher den Spieler eine bestimmte Bewegung ermöglicht, die den anderen Spielern nicht vergönnt ist."
                    + "\nSo kann sich der weiße Spieler beispielsweise mithilfe des SPECIAL MOVES nach links bewegen, der rote Spieler kann sich nach oben und der gelbe Spieler nach unten bewegen."
                    + "\n\n"
                    + "\nGewinner des Spiels ist derjenige, der mehr oder gleich 42 Punkte hat!\n");
            panel.add(manual1);
            myJFrame.setSize(900, 300);
            myJFrame.add(panel);
            myJFrame.setVisible(true);
        });

        //this ActionListener close the whole program
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        play.addActionListener(e -> {
            if (playerSelection.getSelectedIndex() == 0) {
                GameBoard myBoard0 = new GameBoard(2, players);
            } else if (playerSelection.getSelectedIndex() == 1) {
                GameBoard myBoard1 = new GameBoard(3, players);
            } else if (playerSelection.getSelectedIndex() == 2) {
                GameBoard myBoard2 = new GameBoard(4, players);
            }
        });
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        play.addActionListener(e -> {
            if (playerSelection.getSelectedIndex() == 0) {
                //GameBoard myBoard0 = new GameBoard(2, players);
            } else if (playerSelection.getSelectedIndex() == 1) {
                //GameBoard myBoard1 = new GameBoard(3, players);
            } else if (playerSelection.getSelectedIndex() == 2) {
                //GameBoard myBoard2 = new GameBoard(4, players);
            }
        });
    }
}

