import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
public class MAXX implements Serializable, Runnable {

    private GUI gui;
    private GameBoard board;
    private Player[] players;

    private JButton play;
    private JComboBox playerSelection;
    JMenuItem[] menu_items = {
            new JMenuItem("Manual"), new JMenuItem("Save"),
            new JMenuItem("Choose Game"), new JMenuItem("Delete Game"),
            new JMenuItem("Close all Window")
    };

    public MAXX() {

        ActionListener manual_frame = e -> {
            if(((JMenuItem) e.getSource()) == menu_items[0])
            {
                JFrame myJFrame = new JFrame();
                JPanel panel = new JPanel();
                panel.add(new JTextArea(
                        "Hallo! Herzlich willkommen zu 'MAXX'!"
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
                                + "\nGewinner des Spiels ist derjenige, der mehr oder gleich 42 Punkte hat!\n"
                ));
                myJFrame.setSize(900, 300);
                myJFrame.add(panel);
                myJFrame.setVisible(true);
            }
            else if(((JMenuItem) e.getSource()) == menu_items[1])
            {
                System.exit(0);
            }
        };

        /*this ActionListener shows the manual
        manual.addActionListener(e -> {
            JFrame myJFrame = new JFrame();
            JPanel panel = new JPanel();
            panel.add(new JTextArea(
                    "Hallo! Herzlich willkommen zu 'MAXX'!"
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
                    + "\nGewinner des Spiels ist derjenige, der mehr oder gleich 42 Punkte hat!\n"
            ));
            myJFrame.setSize(900, 300);
            myJFrame.add(panel);
            myJFrame.setVisible(true);
        });

        //this ActionListener close the whole program
        close.addActionListener(e -> System.exit(0));
        */

        gui = new GUI(manual_frame);
        playerSelection = new JComboBox(new String[]{"2 Spieler", "3 Spieler", "4 Spieler"});
        gui.add(playerSelection);
        gui.add(play);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        play.addActionListener(e -> {
            switch(playerSelection.getSelectedIndex()+2)
            {
                case 2: board = new GameBoard(2); break;
                case 3: board = new GameBoard(3); break;
                case 4: board = new GameBoard(4); break;
                default: System.err.println("What the fck just happened? How did you do that, this shouldn't be possible!");
            } });
    }

    public static void main(String[] args)
    {
        MAXX x = new MAXX();
    }
}
