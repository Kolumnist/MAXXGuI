/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    private Field[][] boardFields;
    private static int programCount;
    private int playerCount = 0;

    public GameBoard(int pPlayerNumber, Player[] pPlayer) {
        setSize(500, 500);
        setVisible(true);
        setTitle("MaXX" + (programCount++));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create and add JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu info_JMenu = new JMenu("More Information");
        JMenu exit_JMenu = new JMenu("Exit");
        menuBar.add(info_JMenu);
        menuBar.add(exit_JMenu);

        //create and add JMenu
        JMenuItem manual_JMenuItem = new JMenuItem("Manual");
        JMenuItem close_JMenuItem = new JMenuItem("Close Window");
        info_JMenu.add(manual_JMenuItem);
        exit_JMenu.add(close_JMenuItem);

        JPanel board_JPanel = new JPanel();
        board_JPanel.setLayout(new GridLayout(8, 8));

        Container container = getContentPane();
        container.setLayout(new GridLayout(2, 1));
        container.add(menuBar);
        container.add(board_JPanel);

        //this ActionListener shows the manual_JMenuItem
        manual_JMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame myJFrame = new JFrame();
                JPanel panel = new JPanel();

                JTextArea manual = new JTextArea("Hallo! Herzlich willkommen zu 'MAXX'!"
                        + "\nIn unserem Spiel können vier Spieler abwechselnd miteinander spielen. Diese Spieler sind als 'black', 'white', 'red' und 'yellow' konfiguriert."
                        + "\nAlle Spieler haben einen Spielstein, welchen sie auf dem Spielfeld hin und her bewegen können."
                        + "\n\nAber Achtung!!"
                        + "\nDer Spielstein kann nur in 4 bestimmte Richtungen bewegt werden. Nämlich nach: 'NW' 'NO' 'SO' und 'SW'."
                        + "\nZudem hat jeder Spieler noch einen special move, welcher dem Schwarzen Spieler erlaubt seinen Spielstein nach rechts zu bewegen, "
                        + "\ndem weißen Spieler ist es erlaubt, seinen nach links zu bewegen, der rote Spieler kann seinen nach oben und der gelbe Spieler seinen nach unten bewegen."
                        + "\nDieser move wird unter 'SPECIAL' abgerufen!"
                        + "\nGewinner des Spiels ist derjenige, der mehr oder gleich 42 Punkte hat!\n");
                panel.add(manual);
                myJFrame.setSize(900, 300);
                myJFrame.add(panel);
                myJFrame.setVisible(true);
            }
        });

        //this ActionListener close_JMenuItem the whole program
        close_JMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                if (((j == 2 && k == 2) || (j == 2 && k == 5) || (j == 5 && k == 2) || (j == 5 && k == 5)) && (playerCount < pPlayerNumber)) {
                    boardFields[j][k] = new Field(j, k, pPlayer[playerCount]);
                    playerCount++;
                } else {
                    boardFields[j][k] = new Field(j, k);
                    add(boardFields[j][k]);
                }
                boardFields[j][k].addMouseListener(new MAXX());
            }
        }
    }
}
