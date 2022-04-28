/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;


public class GUI extends JFrame implements Serializable {
    //Constructor for the MainMenu
    public GUI(JMenuItem[] pJMenuItem){
        setTitle("MAXXGuI");
        setSize(700,700);
        setVisible(true);

        setLayout(new FlowLayout());
        setJMenuBar(createMenuBar(pJMenuItem));
    }

    //Constructor for the Gameboard
    public GUI(String pTitle){
        setTitle(pTitle);
        setSize(700, 700);
        setVisible(true);

        setLayout(new BorderLayout());
        add(new JPanel(new GridLayout(8, 8)), BorderLayout.CENTER);
    }

    public JMenuBar createMenuBar(JMenuItem[] pJMenuItem){
        JMenuBar menuBar_JMenuBar = new JMenuBar();
        JMenu[] menus_JMenu = {new JMenu("More Information"), new JMenu("Game"), new JMenu("Exit")};

        for (int i = 0; i < menus_JMenu.length; i++){
            menuBar_JMenuBar.add(menus_JMenu[i]);
        }

        for (int i = 0; i < pJMenuItem.length; i++){
            menus_JMenu[(i<1)?0:(i<4)?1:2].add(pJMenuItem[i]);
        }

        return  menuBar_JMenuBar;
    }
}
