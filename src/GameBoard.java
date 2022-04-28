/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JFrame {
    private Field[][] boardFields = new Field[8][8];
    private Player[] players;
    private double boardSum;
    private static int programCount;
    private int playerCount = 0;
    private int selected = 0;
    private JTextField currentPlayer_JTextField = new JTextField();
    private JTextField playerMoves_JTextField = new JTextField();
    private JLabel currentPlayer_JLabel = new JLabel("Current Player:");
    private JLabel playerMoves_JLabel = new JLabel("Moves of current player:");
    private String gameName, gameDelete;

    public GameBoard(int pPlayerNumber) {

        players = new Player[] {new Player(2, 2, 'W'), new Player(5, 5, 'B'),  new Player(5, 2, 'R'), new Player(2, 5, 'Y')};

        playerCount = pPlayerNumber;
        setSize(700, 700);
        setVisible(true);
        setTitle("MAXX" + (programCount++));

        // create and add JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu info_JMenu = new JMenu("More Information");
        JMenu game_JMenu = new JMenu("Game");
        JMenu exit_JMenu = new JMenu("Exit");
        menuBar.add(info_JMenu);
        menuBar.add(game_JMenu);
        menuBar.add(exit_JMenu);

        JMenu goTo_JMenu = new JMenu("GoTo");
        JMenu delete_JMenu = new JMenu("delete");

        //create and add JMenu
        JMenuItem manual_JMenuItem = new JMenuItem("Manual");
        JMenuItem close_JMenuItem = new JMenuItem("Close all Window");
        JMenuItem save_JMenuItem = new JMenuItem("Save");
        JMenuItem name_JMenuItem = new JMenuItem(gameName);
        JMenuItem away_JMenuItem = new JMenuItem(gameDelete);
        info_JMenu.add(manual_JMenuItem);
        game_JMenu.add(save_JMenuItem);
        game_JMenu.add(goTo_JMenu);
        game_JMenu.add(delete_JMenu);
        exit_JMenu.add(close_JMenuItem);
        delete_JMenu.add(away_JMenuItem);
        goTo_JMenu.add(name_JMenuItem);
        //set JMenuBar into JFrame
        setJMenuBar(menuBar);

        //layout of the frame
        setLayout(new BorderLayout());
        //GridLayout for the gameboard
        JPanel board_JPanel = new JPanel(new GridLayout(8,8));
        add(board_JPanel, BorderLayout.CENTER);

        //creating a little console with JTextFields and JLabels to show the current player and it's moves
        JPanel terminal_JPanel = new JPanel(new GridLayout(2,2));
        terminal_JPanel.add(currentPlayer_JLabel);
        terminal_JPanel.add(playerMoves_JLabel);
        terminal_JPanel.add(currentPlayer_JTextField);
        terminal_JPanel.add(playerMoves_JTextField);
        add(terminal_JPanel, BorderLayout.SOUTH);

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
                    Field field = new Field(h, g);
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
                white = new Field(players[0].getX_pos(), players[0].getY_pos(), players[0]);
                black = new Field(players[1].getX_pos(), players[1].getY_pos(), players[1]);
                players[0].players_field = white;
                players[1].players_field = black;
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                break;
            case 3:
                white = new Field(players[0].getX_pos(), players[0].getY_pos(), players[0]);
                black = new Field(players[1].getX_pos(), players[1].getY_pos(), players[1]);
                red = new Field(players[2].getX_pos(), players[2].getY_pos(), players[2]);
                players[0].players_field = white;
                players[1].players_field = black;
                players[2].players_field = red;
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[2][5] = red;
                break;
            case 4:
                white = new Field(players[0].getX_pos(), players[0].getY_pos(), players[0]);
                black = new Field(players[1].getX_pos(), players[1].getY_pos(), players[1]);
                red = new Field(players[2].getX_pos(), players[2].getY_pos(), players[2]);
                yellow = new Field(players[3].getX_pos(), players[3].getY_pos(), players[3]);
                players[0].players_field = white;
                players[1].players_field = black;
                players[2].players_field = red;
                players[3].players_field = yellow;
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[2][5] = red;
                boardFields[5][2] = yellow;
                break;
        }

        //this nested for-loop adds every "field" of "boardFields" to the frame
        for (int t = 0; t < 8; t++) {
            for (int z = 0; z < 8; z++) {
                board_JPanel.add(boardFields[t][z]);
                boardFields[t][z].addMouseListener(mouseAdapter);
            }
        }
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        public void mouseReleased(MouseEvent e) {
            if (players[selected].player_value.intValue() == 84 / playerCount) {
                win();
            }

            //NORTH WEST MOVE
            if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1
                    && ((Field) e.getComponent()).freeField)
            {
                players[selected].northWest();
                players[selected].player_value = players[selected].player_value.add(((Field) e.getComponent()).fieldValue);//adds the fieldvalue to the playervalue
                selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);//player gets moved to the next field and the next player gets selected
            }
            //SOUTH WEST MOVE
            else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1
                    && ((Field) e.getComponent()).freeField)
            {
                players[selected].southWest();
                players[selected].player_value = players[selected].player_value.add(((Field) e.getComponent()).fieldValue);//adds the fieldvalue to the playervalue
                selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);//player gets moved to the next field and the next player gets selected
            }
            //SOUTH EAST MOVE
            else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1
                    && ((Field) e.getComponent()).freeField)
            {
                players[selected].southEast();
                players[selected].player_value = players[selected].player_value.add(((Field) e.getComponent()).fieldValue);//adds the fieldvalue to the playervalue
                selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);//player gets moved to the next field and the next player gets selected
            }
            //NORTH EAST MOVE
            else if (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1
                    && ((Field) e.getComponent()).freeField)
            {
                players[selected].northEast();
                players[selected].player_value = players[selected].player_value.add(((Field) e.getComponent()).fieldValue);//adds the fieldvalue to the playervalue
                selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);//player gets moved to the next field and the next player gets selected
            }
            //SPECIAL MOVE
            else if (( (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() + 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() && selected == 2)/*third player*/
                    || (players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() - 1 && players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() && selected == 3)/*fourth player*/
                    || (players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() + 1 && players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() && selected == 0)/*first player*/
                    || (players[selected].getY_pos() == ((Field) e.getComponent()).getPositionY() - 1 && players[selected].getX_pos() == ((Field) e.getComponent()).getPositionX() && selected == 1))/*second player*/
                    && ((Field) e.getComponent()).freeField)
            {
                players[selected].special();
                players[selected].player_value = players[selected].player_value.add(((Field) e.getComponent()).fieldValue);//adds the fieldvalue to the playervalue
                selected = players[selected].onPlayerMoves(players[selected].players_field, (Field) e.getComponent(), selected, playerCount);//player gets moved to the next field and the next player gets selected
            }
            else//When the player gives something that he cant do
                System.out.println("Das darf deine Figur nicht!");

            console(); //Updating the console
        }
    };

    //checks for the winner unfortunately it doesn't stop the game yet it just tells who won!
    public void win(){
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        JTextArea win = new JTextArea("Herzlichen Glückwunsch der " + players[selected].toString()
                + "  Spieler hat mit " + players[selected].player_value.doubleValue() + " Punkten gewonnen!\n");
        win.setSize(500,200);
        win.setFont(new Font("Serif",Font.PLAIN,14));
        panel.add(win);
        f.setSize(500, 200);
        f.add(panel);
        f.setVisible(true);
        f.setTitle("MAXX Win" + (programCount-1));
        f.setLayout(new FlowLayout());
        System.out.println("Win happened!");
    }

    //Method for the output at the bottem of the frame like a console
    public void console(){
        String specialMove = "";
        //determines the special move of the player
        switch (selected){
            case 0:
                specialMove = "Up";
                break;
            case 1:
                specialMove = "Down";
                break;
            case 2:
                specialMove = "Left";
                break;
            case 3:
                specialMove = "Right";
                break;
        }
        playerMoves_JTextField.setText("Northwest \nNortheast, \nSouthwest, \nSoutheast, \n Special: " + specialMove);
        currentPlayer_JTextField.setText("Spieler: "+players[selected]+" ist am zug.");
    }
}
