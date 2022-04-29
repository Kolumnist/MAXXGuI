/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class GameBoard extends JFrame implements Serializable {
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
    public Field white, black, red, yellow;

    public GameBoard(int pPlayerNumber, JMenuItem[] pJMenuItem) {
        players = new Player[]{new Player(2, 2, 'W'), new Player(5, 5, 'B'), new Player(5, 2, 'R'), new Player(2, 5, 'Y')};
        playerCount = pPlayerNumber;

        //GridLayout for the Gameboard
        JPanel board_JPanel = new JPanel(new GridLayout(8, 8));

        //creating a little console with JTextFields and JLabels to show the current player and it's moves
        JPanel terminal_JPanel = new JPanel(new GridLayout(2, 2));
        terminal_JPanel.add(currentPlayer_JLabel);
        terminal_JPanel.add(playerMoves_JLabel);
        terminal_JPanel.add(currentPlayer_JTextField);
        terminal_JPanel.add(playerMoves_JTextField);
        add(terminal_JPanel, BorderLayout.SOUTH);

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

        new GUI("MAXX" + (programCount++), board_JPanel, terminal_JPanel, pJMenuItem);
        pJMenuItem[1].addActionListener(e -> {
            GameSettings myGame = new GameSettings(this);
        });
        console();
    }

    //region MousAdapter
    MouseAdapter mouseAdapter = new MouseAdapter() {
        public void mouseReleased(MouseEvent e) {
            if (players[selected].player_value.intValue() == (84 / playerCount)) {
                win();
            }

            selected = players[selected].move(e, selected, playerCount);
            console(); //Updating the console
        }
    };
    //endregion

    //checks for the winner unfortunately it doesn't stop the game yet it just tells who won!
    public void win() {
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        JTextArea win = new JTextArea("Herzlichen Glückwunsch der " + players[selected].toString()
                + "  Spieler hat mit " + players[selected].player_value.doubleValue() + " Punkten gewonnen!\n");
        win.setSize(500, 200);
        win.setFont(new Font("Serif", Font.PLAIN, 14));
        panel.add(win);
        f.setSize(500, 200);
        f.add(panel);
        f.setVisible(true);
        f.setTitle("MAXX Win" + (programCount - 1));
        f.setLayout(new FlowLayout());
        System.out.println("Win happened!");
    }

    //Method for the output at the bottem of the frame like a console
    public void console() {
        String specialMove = "";
        //determines the special move of the player
        switch (selected) {
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
        currentPlayer_JTextField.setText("Spieler: " + players[selected] + " ist am zug.");
    }
}
