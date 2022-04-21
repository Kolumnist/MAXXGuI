/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    private Field[][] boardFields = new Field[8][8];
    private double boardSum;
    private static int programCount;
    private int playerCount = 0;

    public GameBoard(int pPlayerNumber, Player[] pPlayer) {
        playerCount = pPlayerNumber;
        setSize(700, 700);
        setVisible(true);
        setTitle("MaXX" + (programCount++));

        // create and add JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu info_JMenu = new JMenu("More Information");
        JMenu exit_JMenu = new JMenu("Exit");
        menuBar.add(info_JMenu);
        menuBar.add(exit_JMenu);

        //create and add JMenu
        JMenuItem manual_JMenuItem = new JMenuItem("Manual");
        JMenuItem close_JMenuItem = new JMenuItem("Close all Window");
        info_JMenu.add(manual_JMenuItem);
        exit_JMenu.add(close_JMenuItem);

        //set JMenuBar into JFrame
        setJMenuBar(menuBar);

        //Container container = getContentPane();
        setLayout(new GridLayout(8, 8));

        //this ActionListener shows the manual_JMenuItem
        manual_JMenuItem.addActionListener(e -> {
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

        //this ActionListener close_JMenuItem the whole program
        close_JMenuItem.addActionListener(e -> {
                    System.exit(0);
                }
        );

        //do-while-loop to create a field with a sum of 84
        do {
            //this nested for-loop creates fields and puts them into "boardFields"
            for (int g = 0; g < 8; g++) {
                for (int h = 0; h < 8; h++) {
                    Field field = new Field(g, h);
                    boardFields[g][h] = field;

                    boardSum += field.fieldValue.doubleValue();
                }
            }
        } while (boardSum == 84);

        //creating the fields for the players
        Field white;
        Field black;
        Field red;
        Field yellow;

        //setting 2-4 player on the board and giving the players their field
        switch (pPlayerNumber) {
            case 2:
                white = new Field(pPlayer[0].getX_pos(), pPlayer[0].getY_pos(), pPlayer[0]);
                black = new Field(pPlayer[1].getX_pos(), pPlayer[1].getY_pos(), pPlayer[1]);
                pPlayer[0].players_field = white;
                pPlayer[1].players_field = black;
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                break;
            case 3:
                white = new Field(pPlayer[0].getX_pos(), pPlayer[0].getY_pos(), pPlayer[0]);
                black = new Field(pPlayer[1].getX_pos(), pPlayer[1].getY_pos(), pPlayer[1]);
                red = new Field(pPlayer[2].getX_pos(), pPlayer[2].getY_pos(), pPlayer[2]);
                pPlayer[0].players_field = white;
                pPlayer[1].players_field = black;
                pPlayer[2].players_field = red;
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[5][2] = red;
                break;
            case 4:
                white = new Field(pPlayer[0].getX_pos(), pPlayer[0].getY_pos(), pPlayer[0]);
                black = new Field(pPlayer[1].getX_pos(), pPlayer[1].getY_pos(), pPlayer[1]);
                red = new Field(pPlayer[2].getX_pos(), pPlayer[2].getY_pos(), pPlayer[2]);
                yellow = new Field(pPlayer[3].getX_pos(), pPlayer[3].getY_pos(), pPlayer[3]);
                pPlayer[0].players_field = white;
                pPlayer[1].players_field = black;
                pPlayer[2].players_field = red;
                pPlayer[3].players_field = yellow;
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[5][2] = red;
                boardFields[2][5] = yellow;
                break;
        }

        //this nested for-loop adds every "field" of "boardFields" to the frame
        for (int t = 0; t < 8; t++) {
            for (int z = 0; z < 8; z++) {
                add(boardFields[t][z]);
                boardFields[t][z].addMouseListener(new MAXX(playerCount, players));
            }
        }
    }
}
