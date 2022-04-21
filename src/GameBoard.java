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
    private Field[][] boardFields = new Field[8][8];
    private static int programCount;
    private int playerCount = 0;

    public GameBoard(int pPlayerNumber, Player[] pPlayer) {
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

        //this nested for-loop creates fields and puts them into "boardFields"
        for (int g = 0; g < 8; g++){
            for (int h = 0; h < 8; h++){
                Field field = new Field(g,h);
                boardFields[g][h] = field;
            }
        }

        //creating the fields for the players
        Field white = new Field(2,2,pPlayer[0]);
        Field black = new Field(5,5,pPlayer[1]);
        Field red = new Field(2,5,pPlayer[2]);
        Field yellow = new Field(2,5,pPlayer[3]);
        //giving the playerfield to the player
        pPlayer[0].players_field = white;
        pPlayer[1].players_field = black;
        pPlayer[2].players_field = red;
        pPlayer[3].players_field = yellow;

        //setting 2-4 player on the board
        switch (pPlayerNumber){
            case 2:
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                break;
            case 3:
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[2][5] = red;
                break;
            case 4:
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[2][5] = red;
                boardFields[5][2] = yellow;
                break;
        }

        //this nested for-loop adds every "field" of "boardFields" to the frame
        for (int t = 0; t < 8; t++){
            for(int z = 0; z < 8; z++){
                add(boardFields[t][z]);
                boardFields[t][z].addMouseListener(new MAXX());
            }
        }
    }
}
