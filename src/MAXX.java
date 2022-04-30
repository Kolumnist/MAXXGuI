import javax.swing.*;
import java.io.Serializable;

/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
public class MAXX implements Runnable, Serializable{

    private GUI gui;
    private GameBoard board;
    private JButton play = new JButton();
    private JComboBox playerSelection;
    JMenuItem[] menu_items = {
            new JMenuItem("Manual"), new JMenuItem("Choose Game"),
            new JMenuItem("Delete Game"), new JMenuItem("Close all Window")
    };

    public MAXX() {
        play.setText("New Game Start");
        playerSelection = new JComboBox(new String[]{"2 Spieler", "3 Spieler", "4 Spieler"});

        gui = new GUI(menu_items);
        gui.add(playerSelection);
        gui.add(play);
        gui.setVisible(true);

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
        new MAXX();
    }
}
