/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;

public class GameBoard implements Serializable {

    private Field[][] boardFields = new Field[8][8];
    private Player[] players;
    private GUI gameBoardGUI;
    private MouseAdapter mouseAdapter;
    private double boardSum;
    private static int programCount;
    private int playerCount = 0;
    private int selected = 0;
    private Field white, black, red, yellow;

    JMenuItem[] menu_items = {
            new JMenuItem("Manual"), new JMenuItem("Save"),
            new JMenuItem("Choose Game"),
            new JMenuItem("Close all Window")
    };

    public GameBoard(int pPlayerNumber) {
        gameBoardGUI = new GUI("MAXX" + (programCount++), menu_items);
        players = new Player[]{new Player('W'), new Player('B'), new Player('R'), new Player('Y')};
        playerCount = pPlayerNumber;
        mouseAdapter = new MouseListener();

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
        } while (boardSum < 84d && boardSum > 88d);

        //setting 2-4 player on the board and giving the players their field
        switch (pPlayerNumber) {
            case 2:
                white = new Field(2, 2, players[0]);
                black = new Field(5, 5, players[1]);
                boardFields[2][2] = white;
                boardFields[5][5] = black;

                players[0].player_field = white; players[1].player_field = black;
                break;
            case 3:
                white = new Field(2, 2, players[0]);
                black = new Field(5, 5, players[1]);
                red = new Field(2, 5, players[2]);
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[5][2] = red;

                players[0].player_field = white; players[1].player_field = black; players[2].player_field = red;
                break;
            case 4:
                white = new Field(2, 2, players[0]);
                black = new Field(5, 5, players[1]);
                red = new Field(2, 5, players[2]);
                yellow = new Field(5, 2, players[3]);
                boardFields[2][2] = white;
                boardFields[5][5] = black;
                boardFields[5][2] = red;
                boardFields[2][5] = yellow;

                players[0].player_field = white; players[1].player_field = black; players[2].player_field = red; players[3].player_field = yellow;
                break;
        }

        //this nested for-loop adds every "field" of "boardFields" to the frame and adds the listener
        for (int t = 0; t < 8; t++) {
            for (int z = 0; z < 8; z++) {
                gameBoardGUI.board_JPanel.add(boardFields[t][z]);
                boardFields[t][z].addMouseListener(mouseAdapter);
            }
        }

        menu_items[1].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            try {
                myGame.saveGame(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        menu_items[2].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            myGame.loadGame(this);
        });
        console();
    }

    //"GameBoard" constructor when you load the game
    public GameBoard(GameBoard pBoard){
        gameBoardGUI = null;
        gameBoardGUI = new GUI("MAXX" + (programCount++), menu_items);

        players = pBoard.players;
        playerCount = pBoard.playerCount;
        boardFields = pBoard.boardFields;
        boardSum = pBoard.boardSum;
        selected = pBoard.selected;

        switch (playerCount){
            case 2:
                white = pBoard.white;
                black = pBoard.black;
                break;
            case 3:
                white = pBoard.white;
                black = pBoard.black;
                red = pBoard.red;
                break;
            case 4:
                white = pBoard.white;
                black = pBoard.black;
                red = pBoard.red;
                yellow = pBoard.yellow;
                break;
        }

        //this nested for-loop adds every "field" of "boardFields" to the frame and adds the listener
        for (int t = 0; t < 8; t++) {
            for (int z = 0; z < 8; z++) {
                gameBoardGUI.board_JPanel.add(boardFields[t][z]);
            }
        }
        menu_items[1].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            try {
                myGame.saveGame(this);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        menu_items[2].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            myGame.loadGame(this);
        });
        console();
    }

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

    //method for the output at the bottom of the frame like a console
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
        gameBoardGUI.playerMoves_JTextField.setText("Special: " + specialMove + " | Basics: \nNW, \nNE, \nSW, \nSE");
        gameBoardGUI.currentPlayer_JTextField.setText("Spieler: " + players[selected] + " ist am zug.");
    }

    //inner MouseListener class to make the listener serializable
    class MouseListener extends MouseAdapter implements Serializable{
        public void mouseReleased(MouseEvent e){
            //checks if one player has enough points to win the game
            if (players[selected].player_value.intValue() >= (84 / playerCount)) {
                win();
            }
            if(players[selected].move((Field)e.getComponent()))
            {
                switch(playerCount)//checks if, depending on the playerCount, the selected value is at its max or not
                {
                    case 2: selected = ((selected+1) % 2)-1; break;
                    case 3: selected = ((selected+1) % 3)-1; break;
                    case 4: selected = ((selected+1) % 4)-1; break;
                    default: selected = -1; break;
                }
                selected++;
                console(); //Updating the console
            }
        }
    }
}