import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements ActionListener {

    public Button(String value)
    {
        this.setBackground(Color.RED);
        this.setName(value);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
