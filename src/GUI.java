/**
 * @author Michel Jouaux, Collin Hoss, Lara Mangi
 * @Matrikelnummer: 212455 [mjouaux], 212848 [choss], 212467 [lmangi]
 * @version 2 21.04.2022
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GUI extends JFrame implements Serializable {

    private GameBoard board;
    private String manualText = "";
    private String inputText = null;
    private BufferedReader bufferedReader;
    public JLabel currentPlayer_JLabel = new JLabel("Current Player:");
    public JLabel playerMoves_JLabel = new JLabel("Moves of current player:");
    public JTextField currentPlayer_JTextField = new JTextField();
    public JTextField playerMoves_JTextField = new JTextField();
    public JPanel board_JPanel;
    public JMenuBar menuBar_JMenuBar = new JMenuBar();
    public JMenu[] menus_JMenu = {new JMenu("More Information"), new JMenu("Game"), new JMenu("Exit")};

    //Constructor for the MainMenu
    public GUI(JMenuItem[] pJMenuItem) {
        setTitle("MAXXGuI");
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        setJMenuBar(createMenuBar(pJMenuItem));

        //create a picture
        JLabel picture;
        Icon icon = new ImageIcon(new ImageIcon(getClass().getResource("MAXXGuI.png")).getImage().getScaledInstance(550, 550, Image.SCALE_DEFAULT));
        picture = new JLabel(icon);
        picture.setIcon(icon);
        add(picture);

        //this ActionListener shows the manual
        pJMenuItem[0].addActionListener(e -> {
            JFrame myJFrame = new JFrame();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/manual")));
                do {
                    if(inputText != null) manualText = manualText + "\n" + inputText;
                    inputText = bufferedReader.readLine();
                } while (inputText != null);
                myJFrame.add(new JTextArea(manualText));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            myJFrame.setSize(1000, 300);
            myJFrame.setLocation(250, 100);
            myJFrame.setVisible(true);
        });

        //this ActionListener close the whole program
        pJMenuItem[3].addActionListener(e -> System.exit(0));
    }

    //Constructor for the Gameboard
    public GUI(String pTitle, JMenuItem[] pJMenuItem) {
        setTitle(pTitle);
        setSize(700, 700);
        setJMenuBar(createMenuBar(pJMenuItem));

        setLayout(new BorderLayout());

        //GridLayout for the Gameboard
        board_JPanel = new JPanel(new GridLayout(8, 8));
        add(board_JPanel, BorderLayout.CENTER);

        pJMenuItem[4].addActionListener(e -> System.exit(0));

        //creating a little console with JTextFields and JLabels to show the current player and it's moves
        JPanel terminal_JPanel = new JPanel(new GridLayout(2, 2));
        terminal_JPanel.add(currentPlayer_JLabel);
        terminal_JPanel.add(playerMoves_JLabel);
        terminal_JPanel.add(currentPlayer_JTextField);
        terminal_JPanel.add(playerMoves_JTextField);
        add(terminal_JPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JMenuBar createMenuBar(JMenuItem[] pJMenuItem) {

        for (int i = 0; i < menus_JMenu.length; i++) {
            menuBar_JMenuBar.add(menus_JMenu[i]);
        }

        for (int i = 0; i < pJMenuItem.length; i++) {
            menus_JMenu[(i < 1) ? 0 : (i == (pJMenuItem.length - 1)) ? 2 : 1].add(pJMenuItem[i]);
        }

        return menuBar_JMenuBar;
    }
}
