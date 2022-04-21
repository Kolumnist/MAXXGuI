import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    // GUI für Gameboard
    public GUI(JButton[] JButton, int rows, int cols, String title) {
        setTitle(title);
        setSize(500, 500);
        setLayout(new GridLayout(rows, cols));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}