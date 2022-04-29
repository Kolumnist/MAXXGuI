/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class GameBoard implements Serializable {
    private Field[][] boardFields = new Field[8][8];
    private Player[] players = {new Player('W'), new Player('B'), new Player('R'), new Player('Y')};
    private double boardSum;
    private static int programCount;
    private int playerCount = 0;
    private int selected = 0;
    private JTextField currentPlayer_JTextField = new JTextField();
    private JTextField playerMoves_JTextField = new JTextField();

    private Field white, black, red, yellow;
    private Field[] playerFields = {new Field(2, 2, players[0]), new Field(5, 5, players[1]), new Field(2, 5, players[2]), new Field(5, 2, players[3])};

    /* Es gab nen Bug der nur mit dem hier: gefixt werden konnte*/
    JMenuItem[] menu_items = {
            new JMenuItem("Manual"), new JMenuItem("Save"),
            new JMenuItem("Choose Game"), new JMenuItem("Delete Game"),
            new JMenuItem("Close all Window")
    };

    public GameBoard(int pPlayerNumber) {
        playerCount = pPlayerNumber;

        //GridLayout for the Gameboard
        JPanel board_JPanel = new JPanel(new GridLayout(8, 8));
        GUI gui = new GUI("MAXX" + (programCount++), board_JPanel, menu_items);

        //creating a little console with JTextFields and JLabels to show the current player and it's moves
        gui.terminal_JPanel.add(currentPlayer_JTextField);
        gui.terminal_JPanel.add(playerMoves_JTextField);

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
        } while (boardSum < 84d);

        //setting 2-4 player on the board and giving the players their field
        switch (pPlayerNumber) {
            case 2:
                players[0].player_field = playerFields[0];
                players[1].player_field = playerFields[1];

                boardFields[2][2] = playerFields[0];
                boardFields[5][5] = playerFields[1];
                break;
            case 3:
                players[0].player_field = playerFields[0];
                players[1].player_field = playerFields[1];
                players[2].player_field = playerFields[2];

                boardFields[2][2] = playerFields[0];
                boardFields[5][5] = playerFields[1];
                boardFields[2][5] = playerFields[2];
                break;
            case 4:
                players[0].player_field = playerFields[0];
                players[1].player_field = playerFields[1];
                players[2].player_field = playerFields[2];
                players[3].player_field = playerFields[3];

                boardFields[2][2] = playerFields[0];
                boardFields[5][5] = playerFields[1];
                boardFields[2][5] = playerFields[2];
                boardFields[5][2] = playerFields[3];
                break;
        }

        //this nested for-loop adds every "field" of "boardFields" to the frame
        for (int t = 0; t < 8; t++) {
            for (int z = 0; z < 8; z++) {
                board_JPanel.add(boardFields[t][z]);
                boardFields[t][z].addMouseListener(mouseAdapter);
            }
        }

        menu_items[1].addActionListener(e -> {
            GameSettings myGame = new GameSettings(this);
        });
        console();
    }

    //region MousAdapter
    MouseAdapter mouseAdapter = new MouseAdapter() {
        public void mouseReleased(MouseEvent e) {
            if (players[selected].player_value.intValue() >= (84 / playerCount)) {
                win();
            }
            selected = players[selected].move((Field)e.getComponent(), selected, playerCount);
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