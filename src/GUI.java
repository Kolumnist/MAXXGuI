/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class GUI extends JFrame {
    private String[] comboBoxList = {"2 Spieler", "3 Spieler", "4 Spieler"};
    //Constructor for the MainMenu
    public GUI(){
        setTitle("MAXXGuI");
        setSize(700,700);
        setVisible(true);

        setLayout(new FlowLayout());
        setJMenuBar(createMenuBar());

        //mabey change
        add(new Label("Wie viele Spieler"));
        add(new JComboBox(comboBoxList));

    }

    //Constructor for the Gameboard
    public GUI(String pTitle){
        setTitle(pTitle);
        setSize(700, 700);
        setVisible(true);

        setLayout(new BorderLayout());
    }

    public JMenuBar createMenuBar(){
        JMenuBar menuBar_JMenuBar = new JMenuBar();
        JMenu[] menus_JMenu = {new JMenu("More Information"), new JMenu("Game"), new JMenu("Exit")};
        JMenuItem[] items_JMenuItem = {new JMenuItem("Manual"), new JMenuItem("Save"), new JMenuItem("Choose Game"), new JMenuItem("Delete Game"), new JMenuItem("Close all Window")};

        for (int i = 0; i <= menus_JMenu.length; i++){
            menuBar_JMenuBar.add(menus_JMenu[i]);
        }

        for (int i = 0; i <= items_JMenuItem.length; i++){
            items_JMenuItem[i].addActionListener();
            menus_JMenu[(i<1)?0:(i<4)?1:2].add(items_JMenuItem[i]);
        }

        return  menuBar_JMenuBar;
    }
}
