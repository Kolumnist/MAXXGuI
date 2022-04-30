/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
import javax.swing.*;
import java.io.Serializable;

public class MAXX implements Serializable{

    private GUI gui;
    private GameBoard board;
    private JButton play = new JButton();//The Play Button which starts a new game(the gameboard)
    private JComboBox playerSelection; //Here the Player can select how many players play the game

    JMenuItem[] menu_items = {
            new JMenuItem("Manual"), new JMenuItem("Choose Game"), new JMenuItem("Close all Window")
    };

    /*Creates a Main Menu with a game start Button, a picture,
      a combobox to choose from 2 to 4 players and a MenuBar*/
    public MAXX() {
        play.setText("New Game Start");
        playerSelection = new JComboBox(new String[]{"2 Spieler", "3 Spieler", "4 Spieler"});

        gui = new GUI(menu_items);
        gui.add(playerSelection);
        gui.add(play);
        gui.setVisible(true);

        /*this ActionListener is for "Choose Game" and is therefore able to load a game*/
        menu_items[1].addActionListener(e -> {
            GameSettings myGame = new GameSettings();
            myGame.loadGame(board);
        });

        /* ActionListener for the JButton play which starts a game with the selected playerNumber*/
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
        new MAXX();
    }
}
