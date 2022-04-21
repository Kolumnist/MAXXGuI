import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("MAXXGuI");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create and add JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu info = new JMenu("More Information");
        JMenu exit = new JMenu("Exit");
        menuBar.add(info);
        menuBar.add(exit);

        //create and add JMenu
        JMenuItem manual = new JMenuItem("Manual");
        JMenuItem close = new JMenuItem("Close Window");
        info.add(manual);
        exit.add(close);

        //set JMenuBar into JFrame
        setJMenuBar(menuBar);

        setSize(700, 700);
        setLocation(700, 300);
        setVisible(true);


        // Array for the player selection
        String comboBoxListe[] = {"2 Spieler", "3 Spieler", "4 Spieler"};

        //create an Label so people will know what they can select with the JComboBox
        JLabel question = new JLabel("Wie viele Spieler?");
        add(question);

        //create JComboBox with all the players
        JComboBox playerSelection = new JComboBox(comboBoxListe);
        add(playerSelection);
        setVisible(true);

        //create a picture
        JLabel picture;
        Icon icon;
        icon = new ImageIcon(getClass().getResource("pictureXX.png"));
        picture = new JLabel(icon);
        picture.setSize(350, 350);
        add(picture);
        // picture.setLocation(50,50);

        //create a button
        JButton play = new JButton();
        play.setText("Game Start");
        play.setSize(200, 200);
        play.setVisible(true);
        play.setBackground(Color.LIGHT_GRAY);
        play.setLocation(650, 650);
        add(play);

        //this ActionListener shows the manual
        manual.addActionListener(new ActionListener() {
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

        //this ActionListener close the whole program
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new MainMenu();

        //Game Start (button) -> soll überprüfen, was in der Combo ausgewählt wird und ruft dann den Konstruktor des Gameboards auf (-> dort wird die Anzahl der Spieler die man benötigt übergeben
        //thread erstellen-> damit mehrere Fenster unabhängig voneinander geöffnet werden können
    }
}

